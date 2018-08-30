import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class ConnectKrb {

    private static String appServerHostName = "marklogic-hostname-to-connect-to";
    private static String kdcPrincipalUser = "windowsusername@KERBEROSTEST.LOCAL";
    private static int appServerHostPort = 9000;
    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        LOG.info("trying to connect using the Kerberos Auth Context");
        DatabaseClient client = DatabaseClientFactory.newClient(appServerHostName,
                appServerHostPort, new DatabaseClientFactory.KerberosAuthContext(kdcPrincipalUser));
        LOG.info("Testing connection (eval 1+1): "+client.newServerEval().xquery("1+1").evalAs(String.class));
    }
}
