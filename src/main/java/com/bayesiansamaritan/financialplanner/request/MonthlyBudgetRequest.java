package com.bayesiansamaritan.financialplanner.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonthlyBudgetRequest {
    private Long userId;
    private Long cost;
    private String categoryName;
    private String subCategoryName;
    private String expenseName;


}
