import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.kerberos.client.KerberosRestTemplate;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ConnectKerberosTest {

    @BeforeClass
    public static void setDebugProperties() {
        // System.setProperty("sun.security.krb5.debug", "true");
        // System.setProperty("sun.security.spnego.debug", "true");
        // Use this if you want to use a local krb5.conf file
        // System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");
        // Use this if you want to specify Login Config
        // System.setProperty("java.security.auth.login.config", "login.conf");
        // System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
        // System.setProperty("sun.security.jgss.debug", "true");
    }

    @Test
    public void testKerberosMarkLogicClientApiConnection() {
        DatabaseClient client = DatabaseClientFactory.newClient(Configuration.MARKLOGIC_HOST,
                Configuration.APPSERVER_PORT, new DatabaseClientFactory.KerberosAuthContext(Configuration.KDC_PRINCIPAL_USER));
        String test = client.newServerEval().xquery("1+1").evalAs(String.class);
        assertEquals("2", test);
    }

    @Test
    public void testKerberosUsingSpringRestTemplate() {
        Map<String, Object> loginOptions = new HashMap<>();
        loginOptions.put("debug", "true");

        KerberosRestTemplate restTemplate =
                new KerberosRestTemplate(null, Configuration.KDC_PRINCIPAL_USER, loginOptions);
        restTemplate.getForEntity(String.format("http://%s:%d/", Configuration.MARKLOGIC_HOST, Configuration.APPSERVER_PORT), String.class);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
