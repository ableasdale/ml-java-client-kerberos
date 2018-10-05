import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;

/**
 * Simple example usage for connecting the Spring ReST Template to MarkLogic using (default) Digest Authentication
 */
public class SimpleHttpGetUsingSpringRestTemplate {

    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpHost target = new HttpHost("localhost", 8002, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target),
                new UsernamePasswordCredentials("username", "password"));

        CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider)
                .build();
        factory.setHttpClient(client);
        RestTemplate restTemplate = new RestTemplate(factory);

        ResponseEntity<String> response
                = restTemplate.getForEntity(String.format("%s/manage/LATEST/databases/App-Services/properties", target.toURI()), String.class);

        LOG.info("Response code: " + response.getStatusCode());
        LOG.info(response.toString());
    }
}
