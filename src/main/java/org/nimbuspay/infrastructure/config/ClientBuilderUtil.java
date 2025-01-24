package org.nimbuspay.infrastructure.config;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.SSLContext;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.nimbuspay.infrastructure.client.FXPaymentService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

@ApplicationScoped
public class ClientBuilderUtil {
   
    private final VisaApiConfig config;

    @Inject
    public ClientBuilderUtil(VisaApiConfig config) {
        this.config = config;
    }

    public FXPaymentService createVisaServiceClient(SSLContext sslContext) {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(config.getBaseUrl()))
                .sslContext(sslContext)
                .register(new AuthRequestFilter(config.getUsername(), config.getPassword())) 
                .build(FXPaymentService.class);
    }
    private static class AuthRequestFilter implements ClientRequestFilter {
        private final String encodedAuth;
 
        public AuthRequestFilter(String userId, String password) {
            String auth = userId + ":" + password;
            this.encodedAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        }
 
        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            requestContext.getHeaders().add("Authorization", encodedAuth);
        }
    }
}
