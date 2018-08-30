import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class ConnectKrb {

    //
    // private DatabaseClient client;
    private static String appServerHostName = "engrlab-130-217";
    private static String kdcPrincipalUser = "mjones@kerberostest.local";
    private static int appServerHostPort = 9001;
    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        LOG.info("trying to connect using the Kerberos Auth Context");
        DatabaseClient client = DatabaseClientFactory.newClient(appServerHostName,
                appServerHostPort, new DatabaseClientFactory.KerberosAuthContext(kdcPrincipalUser));
        LOG.info("Test:"+client.newServerEval().xquery("1+1").evalAs(String.class));
    }
}
