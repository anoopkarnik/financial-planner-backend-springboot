package com.bayesiansamaritan.financialplanner.service.impl;

import com.bayesiansamaritan.financialplanner.model.*;
import com.bayesiansamaritan.financialplanner.repository.*;
import com.bayesiansamaritan.financialplanner.response.AccountBalanceResponse;
import com.bayesiansamaritan.financialplanner.model.*;
import com.bayesiansamaritan.financialplanner.repository.*;
import com.bayesiansamaritan.financialplanner.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommonsServiceImpl implements CommonsService {

    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    CategoryTypeRepository categoryTypeRepository;

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    SubCategoryTypeRepository subCategoryTypeRepository;
    private final SubAccountTypeRepository subAccountTypeRepository;

    public CommonsServiceImpl(AccountTypeRepository accountTypeRepository, CategoryTypeRepository categoryTypeRepository,
                              ExpenseTypeRepository expenseTypeRepository, SubCategoryTypeRepository subCategoryTypeRepository,
                              SubAccountTypeRepository subAccountTypeRepository){
        this.accountTypeRepository=accountTypeRepository;
        this.categoryTypeRepository=categoryTypeRepository;
        this.expenseTypeRepository=expenseTypeRepository;
        this.subCategoryTypeRepository=subCategoryTypeRepository;
        this.subAccountTypeRepository = subAccountTypeRepository;
    }


    @Override
    public List<AccountType> getAllAccountTypes(){
        List<AccountType> accountTypes = new ArrayList<>();
        accountTypeRepository.findAll().forEach(accountTypes::add);
        return accountTypes;
    }


    @Override
    public List<CategoryType> getAllCategoryTypes(){
        List<CategoryType> categoryTypes = new ArrayList<>();
        categoryTypeRepository.findAll().forEach(categoryTypes::add);
        return categoryTypes;
    }

    @Override
    public List<ExpenseType> getAllExpenseTypes(){
        List<ExpenseType> expenseTypes = new ArrayList<>();
        expenseTypeRepository.findAll().forEach(expenseTypes::add);
        return expenseTypes;
    }

    @Override
    public List<SubCategoryType> getAllSubCategoryTypes(){
        List<SubCategoryType> subCategoryTypes = new ArrayList<>();
        subCategoryTypeRepository.findAll().forEach(subCategoryTypes::add);
        return subCategoryTypes;
    }

    @Override
    public AccountType createAccountType(String name){
        AccountType accountType = accountTypeRepository
                .save(new AccountType(name));
        return accountType;
    }

    @Override
    public CategoryType createCategoryType(String name){
        CategoryType categoryType = categoryTypeRepository.save(new CategoryType(name));
        return categoryType;
    }
    @Override
    public ExpenseType createExpenseType(String name){
        ExpenseType expenseType = expenseTypeRepository.save(new ExpenseType(name));
        return expenseType;
    }

    @Override
    public SubCategoryType createSubCategoryType(String name){
        SubCategoryType subCategoryType = subCategoryTypeRepository.save(new SubCategoryType(name));
        return subCategoryType;
    }

    @Override
    public Optional<AccountType> getAccountById(Long id){
        Optional<AccountType> accountType = accountTypeRepository.findById(id);
        return accountType;

    };

}


