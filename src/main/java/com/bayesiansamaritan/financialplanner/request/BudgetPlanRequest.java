package com.bayesiansamaritan.financialplanner.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetPlanRequest {
    private Long userId;
    private Long planPercentage;
    private String expenseName;


}
