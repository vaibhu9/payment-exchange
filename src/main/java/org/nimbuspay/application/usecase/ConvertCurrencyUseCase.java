package org.nimbuspay.application.usecase;

import org.nimbuspay.application.constant.CurrencyConversionConstant;
import org.nimbuspay.domain.model.ClientCurrencyConversionRequest;
import org.nimbuspay.domain.model.VisaCurrencyConversionRequest;
import org.nimbuspay.domain.model.VisaCurrencyConversionResponse;
import org.nimbuspay.domain.port.in.CurrencyConversionServicePort;
import org.nimbuspay.domain.port.out.CurrencyConversionPort;
import org.nimbuspay.domain.valueobjects.AcquirerDetails;
import org.nimbuspay.domain.valueobjects.Settlement;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class ConvertCurrencyUseCase implements CurrencyConversionServicePort{

    private final CurrencyConversionPort currencyConversionPort;

    @Override
    public VisaCurrencyConversionResponse requestCurrencyConversion(ClientCurrencyConversionRequest request) {
        VisaCurrencyConversionRequest visaRequest = prepareVisaRequest(request);
        return currencyConversionPort.executeCurrencyConversion(visaRequest);
    }

    private VisaCurrencyConversionRequest prepareVisaRequest(ClientCurrencyConversionRequest clientRequest) {
        VisaCurrencyConversionRequest visaRequest = new VisaCurrencyConversionRequest();
        visaRequest.setDestinationCurrencyCode(clientRequest.getDestinationCurrencyCode());
        visaRequest.setSourceAmount(clientRequest.getAmount());
        visaRequest.setSourceCurrencyCode(clientRequest.getSourceCurrencyCode());
        visaRequest.setRateProductCode(CurrencyConversionConstant.RATE_PRODUCT_CODE); 
        visaRequest.setMarkupRate(CurrencyConversionConstant.MARKUP_RATE); 
        AcquirerDetails acquirerDetails = new AcquirerDetails();
        acquirerDetails.setBin(CurrencyConversionConstant.ACQUIRER_BIN); 
        Settlement settlement = new Settlement();
        settlement.setCurrencyCode( CurrencyConversionConstant.SETTLEMENT_CURRENCY_CODE);
        acquirerDetails.setSettlement(settlement);
        visaRequest.setAcquirerDetails(acquirerDetails);
        return visaRequest;
    }

       

}
