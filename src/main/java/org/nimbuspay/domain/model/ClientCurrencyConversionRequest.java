package org.nimbuspay.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCurrencyConversionRequest {

    private String amount;       
    private String sourceCurrencyCode;     
    private String destinationCurrencyCode;
}
