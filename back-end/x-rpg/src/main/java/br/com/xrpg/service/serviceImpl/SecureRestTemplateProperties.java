//package br.com.xrpg.service.serviceImpl;
//
//import java.net.URL;
//import java.util.Arrays;
//import javax.net.ssl.SSLContext;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.client.RestTemplateCustomizer;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//import org.springframework.web.util.UriTemplateHandler;
//
//@ConfigurationProperties("secure-rest")
//@Data
//public class SecureRestTemplateProperties {
//
//    /**
//     * URL location, typically with file:// scheme, of a CA trust store file in JKS format.
//     */
//    String trustStore;
//
//    /**
//     * The store password of the given trust store.
//     */
//    char[] trustStorePassword;
//
//    /**
//     * One of the SSLContext algorithms listed at
//     * https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#SSLContext .
//     */
//    String protocol = "TLSv1.2";
//}
//
//@Component
//@EnableConfigurationProperties(SecureRestTemplateProperties.class)
//@Slf4j
//public class SecureRestTemplateCustomizer implements RestTemplateCustomizer {
//
//    private final SecureRestTemplateProperties properties;
//
//    @Autowired
//    public SecureRestTemplateCustomizer(
//            SecureRestTemplateProperties properties
//    ) {
//        this.properties = properties;
//    }
//
//    @Override
//    public void customize(RestTemplate restTemplate) {
//
//        final SSLContext sslContext;
//        try {
//            sslContext = SSLContextBuilder.create()
//                    .loadTrustMaterial(new URL(properties.getTrustStore()),
//                            properties.getTrustStorePassword())
//                    .setProtocol(properties.getProtocol())
//                    .build();
//        } catch (Exception e) {
//            throw new IllegalStateException(
//                    "Failed to setup client SSL context", e
//            );
//        } finally {
//            // it's good security practice to zero out passwords,
//            // which is why they're char[]
//            Arrays.fill(properties.getTrustStorePassword(), (char) 0);
//        }
//
//        final HttpClient httpClient = HttpClientBuilder.create()
//                .setSslcontext(sslContext)
//                .build();
//
//        final ClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        log.info("Registered SSL truststore {} for client requests",
//                properties.getTrustStore());
//        restTemplate.setRequestFactory(requestFactory);
//
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("ws.apicep.com")
//                .path("cep.json")
//                .queryParam("code",cepString)
//                .build();
//
//        UriTemplateHandler uri2 = new UriTemplateHandler().bu
//
//        restTemplate.setUriTemplateHandler(uri);
//    }
//}
