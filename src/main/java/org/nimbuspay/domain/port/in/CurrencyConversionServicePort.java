package org.nimbuspay.domain.port.in;

import org.nimbuspay.domain.model.ClientCurrencyConversionRequest;
import org.nimbuspay.domain.model.VisaCurrencyConversionResponse;

public interface CurrencyConversionServicePort {

        VisaCurrencyConversionResponse requestCurrencyConversion(ClientCurrencyConversionRequest request);

}
