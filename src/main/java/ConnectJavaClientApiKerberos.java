import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class ConnectKrb {
    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        DatabaseClient client = DatabaseClientFactory.newClient(Configuration.MARKLOGIC_HOST,
                Configuration.APPSERVER_PORT, new DatabaseClientFactory.KerberosAuthContext(Configuration.KDC_PRINCIPAL_USER));
        LOG.info(String.format("Testing connection (eval 1+1): %s", client.newServerEval().xquery("1+1").evalAs(String.class)));
    }
}
