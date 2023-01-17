package com.bayesiansamaritan.financialplanner.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyBudgetResponse {

    private Long id;
    private String expenseName;
    private String categoryName;
    private String subCategoryName;
    private Long budgetAmount;
    private Long amountSpent;


}
