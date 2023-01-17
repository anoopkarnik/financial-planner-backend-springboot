package com.bayesiansamaritan.financialplanner.service;

import com.bayesiansamaritan.financialplanner.model.AccountType;
import com.bayesiansamaritan.financialplanner.model.CategoryType;
import com.bayesiansamaritan.financialplanner.model.ExpenseType;
import com.bayesiansamaritan.financialplanner.model.SubCategoryType;
import com.bayesiansamaritan.financialplanner.response.AccountBalanceResponse;
import com.bayesiansamaritan.financialplanner.model.*;

import java.util.List;
import java.util.Optional;

public interface CommonsService {

    public List<AccountType>  getAllAccountTypes();
    public List<CategoryType> getAllCategoryTypes();
    public List<ExpenseType> getAllExpenseTypes();
    public List<SubCategoryType> getAllSubCategoryTypes();

    public AccountType createAccountType(String name);
    public CategoryType createCategoryType(String name);
    public ExpenseType createExpenseType(String name);
    public SubCategoryType createSubCategoryType(String name);
    public Optional<AccountType> getAccountById(Long id);


}
