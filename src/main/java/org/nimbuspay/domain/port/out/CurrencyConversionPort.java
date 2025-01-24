package org.nimbuspay.domain.port.out;

import org.nimbuspay.domain.model.VisaCurrencyConversionRequest;
import org.nimbuspay.domain.model.VisaCurrencyConversionResponse;

public interface CurrencyConversionPort {

    VisaCurrencyConversionResponse executeCurrencyConversion(VisaCurrencyConversionRequest request);
}
