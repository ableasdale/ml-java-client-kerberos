import com.kerb4j.client.SpnegoClient;
import com.kerb4j.client.SpnegoContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Basic Kerb4JExample
 */
public class Kerb4JExample  {

    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) throws Exception {
        SpnegoClient spnegoClient = SpnegoClient.loginWithTicketCache(Configuration.KDC_PRINCIPAL_USER);
        URL url = new URL(String.format("http://%s:%d", Configuration.MARKLOGIC_HOST, Configuration.APPSERVER_PORT));
        SpnegoContext context = spnegoClient.createContext(new URL(String.format("http://%s", Configuration.MARKLOGIC_HOST)));
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestProperty("Authorization", context.createTokenAsAuthroizationHeader());
        LOG.info("Response code: " + huc.getResponseCode());
        LOG.info("Response message: " + huc.getResponseMessage());
        BufferedReader br;

        if (200 <= huc.getResponseCode() && huc.getResponseCode() <= 299) {
            br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(huc.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        LOG.info(sb.toString());
    }
}
