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
    public static void setProperties() {
        System.setProperty("sun.security.krb5.debug", "true");
        System.setProperty("sun.security.spnego.debug", "true");
    }

    @Test
    public void testMarkLogicClientApiConnection() {
        DatabaseClient client = DatabaseClientFactory.newClient(Configuration.MARKLOGIC_HOST,
                Configuration.APPSERVER_PORT, new DatabaseClientFactory.KerberosAuthContext(Configuration.KDC_PRINCIPAL_USER));
        String test = client.newServerEval().xquery("1+1").evalAs(String.class);
        assertEquals("2", test);
    }

    @Test
    public void testKerberosUsingSpringRestTemplate() {
        Map<String, Object> loginOptions = new HashMap<>();
               loginOptions.put("principal", Configuration.KDC_PRINCIPAL_USER);
        loginOptions.put("userPrincipal", Configuration.KDC_PRINCIPAL_USER);
        loginOptions.put("useTicketCache", "true");
        loginOptions.put("doNotPrompt", "true");
          loginOptions.put("isInitiator", "true");
          loginOptions.put("useTicketCache", "true");
         loginOptions.put("renewTGT", "true");
         loginOptions.put("refreshKrb5Config", "true");
        loginOptions.put("debug", "true");

        KerberosRestTemplate restTemplate =
                new KerberosRestTemplate(null, Configuration.KDC_PRINCIPAL_USER, loginOptions);
        restTemplate.getForEntity("http://engrlab-130-217:9002/manage/LATEST/databases/App-Services/properties", String.class);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
