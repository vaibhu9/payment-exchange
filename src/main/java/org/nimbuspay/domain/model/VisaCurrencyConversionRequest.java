package org.nimbuspay.domain.model;

import org.nimbuspay.domain.valueobjects.AcquirerDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisaCurrencyConversionRequest  {
    private String destinationCurrencyCode;
    private String rateProductCode;
    private String sourceAmount;    
    private String sourceCurrencyCode;
    private String markupRate;      
    private AcquirerDetails acquirerDetails;

  
}
