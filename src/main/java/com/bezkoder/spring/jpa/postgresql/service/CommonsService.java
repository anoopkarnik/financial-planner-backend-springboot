package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.*;

import java.util.List;

public interface CommonsService {

    public List<AccountType> getAllAccountTypes();
    public List<CategoryType> getAllCategoryTypes();
    public List<ExpenseType> getAllExpenseTypes();
    public List<SubCategoryType> getAllSubCategoryTypes();

    public AccountType createAccountType(String name);
    public CategoryType createCategoryType(String name);
    public ExpenseType createExpenseType(String name);
    public SubCategoryType createSubCategoryType(String name);


}
