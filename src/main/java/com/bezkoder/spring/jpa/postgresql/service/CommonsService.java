package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.*;
import com.bezkoder.spring.jpa.postgresql.response.AccountBalanceResponse;

import java.util.List;
import java.util.Optional;

public interface CommonsService {

    public List<AccountBalanceResponse>  getAllAccountTypes(Long userId);
    public List<CategoryType> getAllCategoryTypes();
    public List<ExpenseType> getAllExpenseTypes();
    public List<SubCategoryType> getAllSubCategoryTypes();

    public AccountType createAccountType(String name);
    public CategoryType createCategoryType(String name);
    public ExpenseType createExpenseType(String name);
    public SubCategoryType createSubCategoryType(String name);
    public Optional<AccountType> getAccountById(Long id);


}
