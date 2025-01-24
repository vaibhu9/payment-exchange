package org.nimbuspay.infrastructure.adapter;

import org.nimbuspay.domain.model.VisaCurrencyConversionRequest;
import org.nimbuspay.domain.model.VisaCurrencyConversionResponse;
import org.nimbuspay.domain.port.out.CurrencyConversionPort;
import org.nimbuspay.infrastructure.client.FXPaymentService;
import org.nimbuspay.infrastructure.config.ClientBuilderUtil;
import org.nimbuspay.infrastructure.config.VisaApiConfig;
import org.nimbuspay.infrastructure.config.VisaSSLContextProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CurrencyConversionAdapter implements CurrencyConversionPort {
 
    @Inject
    private VisaApiConfig config;
    
    private final FXPaymentService fxPaymentService;
    
    public CurrencyConversionAdapter(ClientBuilderUtil clientBuilderUtil, VisaSSLContextProvider visaSSLContextProvider) {
        this.fxPaymentService = clientBuilderUtil.createVisaServiceClient(visaSSLContextProvider.createSSLContext());
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

     @Override
     public VisaCurrencyConversionResponse executeCurrencyConversion(VisaCurrencyConversionRequest request) {
         VisaCurrencyConversionResponse response = null;
         try {
            log.info("Request for exchange for visa api: " + request);
           Response fxResponse = fxPaymentService.getExchangeRate(request);
             if (fxResponse.getStatus() == 200) {
                 String responseBody = fxResponse.readEntity(String.class);
                 response = mapResponseToVisaCurrencyConversionResponse(responseBody);
                 log.info("Successful response: " + responseBody);
             } else {
                 log.error("Error: " + fxResponse.getStatus() + " - " + fxResponse.readEntity(String.class));
             }
         } catch (Exception ex) {
             log.info("Error in currency conversion: " + ex.getMessage());
         }
         return response;
     }

     private VisaCurrencyConversionResponse mapResponseToVisaCurrencyConversionResponse(String responseBody) {
         try {
             return objectMapper.readValue(responseBody, VisaCurrencyConversionResponse.class);
         } catch (Exception e) {
             log.error("Failed to parse JSON response: " + e.getMessage());
             return null;
        }
     }
    
}