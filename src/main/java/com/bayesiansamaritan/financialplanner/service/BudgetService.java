package com.bayesiansamaritan.financialplanner.service;

import com.bayesiansamaritan.financialplanner.model.BudgetPlan;
import com.bayesiansamaritan.financialplanner.model.MonthlyBudget;
import com.bayesiansamaritan.financialplanner.response.BudgetPlanResponse;
import com.bayesiansamaritan.financialplanner.response.MonthlyBudgetResponse;

import java.text.ParseException;
import java.util.List;

public interface BudgetService {

    public MonthlyBudget createMonthlyBudget(Long cost, String expenseName, String categoryName, String SubCategoryName, Long userId);

    public List<MonthlyBudgetResponse> getMonthlyBudget(String expenseName, Long userId) throws ParseException;

    public BudgetPlan createBudgetPlan(String expenseName, Long planPercentage, Long userId);

    public List<BudgetPlanResponse> getBudgetPlans(Long userId) throws ParseException;

}
