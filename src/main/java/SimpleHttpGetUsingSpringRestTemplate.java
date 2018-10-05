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

public class SimpleHttpGetUsingSpringRestTemplate {

    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpHost target = new HttpHost("localhost", 8000, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target),
                new UsernamePasswordCredentials("username", "password"));

        CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider)
                .build();
        factory.setHttpClient(client);
        RestTemplate restTemplate = new RestTemplate(factory);

        ResponseEntity<String> response
                = restTemplate.getForEntity(target.toURI() + "/LATEST/documents", String.class);

        LOG.info("Response code: " + response.getStatusCode());
        LOG.info(response.toString());

    }
}
