package org.nimbuspay.infrastructure.adapter;

import org.nimbuspay.domain.model.ClientCurrencyConversionRequest;
import org.nimbuspay.domain.port.in.CurrencyConversionServicePort;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/currency-conversion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class CurrencyConversionResource {

    private CurrencyConversionServicePort convertCurrencyUseCase;

     public CurrencyConversionResource( CurrencyConversionServicePort convertCurrencyUseCase) {
        this.convertCurrencyUseCase = convertCurrencyUseCase;
    }   

    @POST
    @Path("/fxRate")
    public Response convertCurrency(ClientCurrencyConversionRequest request) {
        try {
            log.info("Received ClientCurrencyConversionRequest : {}", request);
            var response = convertCurrencyUseCase.requestCurrencyConversion(request);
            return Response.ok(response).build();
        } catch (Exception e) {
            log.error("Error in processing conversion request", e);
            return Response.serverError()
                    .entity("Currency conversion failed: " + e.getMessage())
                    .build();
        }
    }
}