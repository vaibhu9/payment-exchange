package org.nimbuspay.infrastructure.config;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class VisaSSLContextProvider {
    
    @Inject
    private  VisaApiConfig config;

    public  SSLContext createSSLContext() {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            try (FileInputStream fis = new FileInputStream(config.getKeystorePath())) {
                ks.load(fis, config.getKeystorePassword().toCharArray());
            }

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, config.getKeystorePassword().toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);
            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SSLContext: " + e.getMessage(), e);
        }
    }

}