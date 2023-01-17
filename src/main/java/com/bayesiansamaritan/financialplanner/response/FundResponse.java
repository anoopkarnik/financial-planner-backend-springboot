package com.bayesiansamaritan.financialplanner.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundResponse {

    private Long totalAmount;
    private Long amountAvailable;
    private Long amountAllocated;

    private Long  financialIndependenceAmount;
    private Long financialIndependencePercentage;
    private Long timeLeft;
}
