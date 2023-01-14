package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.BudgetPlan;
import com.bezkoder.spring.jpa.postgresql.model.MonthlyBudget;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;
import com.bezkoder.spring.jpa.postgresql.response.BudgetPlanResponse;
import com.bezkoder.spring.jpa.postgresql.response.MonthlyBudgetResponse;

import java.text.ParseException;
import java.util.List;

public interface BudgetService {

    public MonthlyBudget createMonthlyBudget(Long cost, String expenseName, String categoryName, String SubCategoryName, Long userId);

    public List<MonthlyBudgetResponse> getMonthlyBudget(String expenseName, Long userId) throws ParseException;

    public BudgetPlan createBudgetPlan(String expenseName, Long planPercentage, Long userId);

    public List<BudgetPlanResponse> getBudgetPlans(Long userId) throws ParseException;

}
