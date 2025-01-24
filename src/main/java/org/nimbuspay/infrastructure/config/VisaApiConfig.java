package org.nimbuspay.infrastructure.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;

@ApplicationScoped
@Getter
public class VisaApiConfig {

    @Inject
    @ConfigProperty(name = "visa.api.url")
    private String apiUrl;

    @Inject
    @ConfigProperty(name = "visa.base.url")
    private String baseUrl;

    @Inject
    @ConfigProperty(name = "visa.api.username")
    private String username;

    @Inject
    @ConfigProperty(name = "visa.api.password")
    private String password;

    @Inject
    @ConfigProperty(name = "ssl.keystore.path")
    private String keystorePath;

    @Inject
    @ConfigProperty(name = "ssl.keystore.password")
    private String keystorePassword;

   
 
}
