import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.*;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Set;

import static javax.security.auth.Subject.doAs;
import static javax.security.auth.Subject.getSubject;

/**
 * Adapted from: https://stackoverflow.com/questions/21629132/httpclient-set-credentials-for-kerberos-authentication
 */
public class HttpClientKerberosDoAS {

    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "src/main/resources/login.conf");
        String url = String.format("http://%s:%d", Configuration.MARKLOGIC_HOST, Configuration.APPSERVER_PORT);
        HttpClientKerberosDoAS kcd = new HttpClientKerberosDoAS();
        kcd.test(url);
    }

    private void test(final String url) {
        try {
            LoginContext lc = new LoginContext("KrbLogin", new KerberosCallBackHandler(null, null));
            lc.login();
            PrivilegedAction sendAction = () -> {
                try {
                    Set<Principal> principals = getSubject(AccessController.getContext()).getPrincipals();
                    for (Principal next : principals) {
                        LOG.debug("Principal: " + next.getName());
                    }
                    call(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            };
            doAs(lc.getSubject(), sendAction);
        } catch (LoginException le) {
            LOG.error("Exception Caught: ", le);
        }
    }

    private void call(String url) throws IOException {
        try (CloseableHttpClient httpclient = getHttpClient()) {
            HttpUriRequest request = new HttpGet(url);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            LOG.debug(String.format("Status Code: %s", response.getStatusLine()));
            if (entity != null) {
                LOG.debug(String.format("Response Body: %s", EntityUtils.toString(entity)));
            }
        }
    }

    private CloseableHttpClient getHttpClient() {
        Credentials use_jaas_creds = new Credentials() {
            public String getPassword() {
                return null;
            }

            public Principal getUserPrincipal() {
                return null;
            }
        };

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(null, -1, null), use_jaas_creds);
        Registry<AuthSchemeProvider> authSchemeRegistry = RegistryBuilder.<AuthSchemeProvider>create().register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory(true)).build();
        return HttpClientBuilder.create().setDefaultAuthSchemeRegistry(authSchemeRegistry).setDefaultCredentialsProvider(credsProvider).build();
    }

    class KerberosCallBackHandler implements CallbackHandler {

        private final String user;
        private final String password;

        private KerberosCallBackHandler(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
            for (Callback callback : callbacks) {
                if (callback instanceof NameCallback) {
                    NameCallback nc = (NameCallback) callback;
                    nc.setName(user);
                } else if (callback instanceof PasswordCallback) {
                    PasswordCallback pc = (PasswordCallback) callback;
                    pc.setPassword(password.toCharArray());
                } else {
                    throw new UnsupportedCallbackException(callback, "Unknown Callback");
                }
            }
        }
    }
}
