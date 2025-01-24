package org.nimbuspay.infrastructure.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.nimbuspay.domain.model.VisaCurrencyConversionRequest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RegisterRestClient(configKey = "visa-api")
public interface FXPaymentService {

    @POST
    @Path("/forexrates/v2/foreignexchangerates")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response getExchangeRate(VisaCurrencyConversionRequest conversionRequest);
}