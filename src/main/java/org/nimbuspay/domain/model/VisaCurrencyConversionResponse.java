package org.nimbuspay.domain.model;

import org.nimbuspay.domain.valueobjects.AcquirerDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisaCurrencyConversionResponse {
    private String conversionRate;                 
    private String destinationAmount;               
    private String rateProductCode;                 
    private String markupRateApplied;               
    private String sourceAmountWithoutMarkup;       
    private AcquirerDetails acquirerDetails; 
}
