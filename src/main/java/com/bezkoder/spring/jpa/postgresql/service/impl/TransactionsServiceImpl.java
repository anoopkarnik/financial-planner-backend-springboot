package com.bezkoder.spring.jpa.postgresql.service.impl;

import com.bezkoder.spring.jpa.postgresql.model.*;
import com.bezkoder.spring.jpa.postgresql.repository.*;
import com.bezkoder.spring.jpa.postgresql.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService{

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Autowired
    CategoryTypeRepository categoryTypeRepository;

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    SubAccountTypeRepository subAccountTypeRepository;

    @Autowired
    SubCategoryTypeRepository subCategoryTypeRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository){
        this.transactionsRepository = transactionsRepository;
    }



    @Override
    public Transactions createTransactions(String name,Long cost, String expenseName, String categoryName, String subCategoryName, String accountName,
                                           String subAccountName, Long userId){


        AccountType accountType = accountTypeRepository.findByName(accountName);
        Long accountTypeId = accountType.getId();

        ExpenseType expenseType = expenseTypeRepository.findByName(expenseName);
        Long expenseTypeId = expenseType.getId();

        CategoryType categoryType = categoryTypeRepository.findByName(categoryName);
        Long categoryTypeId = categoryType.getId();

        SubCategoryType subCategoryType = subCategoryTypeRepository.findByName(subCategoryName);
        Long subCategoryTypeId = subCategoryType.getId();

        SubAccountType subAccountType = subAccountTypeRepository.findByName(subAccountName);
        Long subAccountTypeId = subAccountType.getId();

        Boolean active = true;

        Transactions transactions = transactionsRepository.save(new Transactions(name,cost,expenseTypeId,userId,accountTypeId,categoryTypeId,subAccountTypeId,subCategoryTypeId,active));
        return transactions;
    };

    public List<Transactions> getTransactions(Long userId, List<String> expenseTypes, List<String> accountTypes, List<String> categoryTypes,
                                              List<String> subCategoryTypes, String dateFrom, String dateTo){
        List<Long> expenseTypeIds = new ArrayList<>();
        List<Long> accountTypeIds = new ArrayList<>();
        List<Long> categoryTypeIds = new ArrayList<>();
        List<Long> subCategoryTypeIds = new ArrayList<>();

        for(String expenseName:expenseTypes){
            ExpenseType expenseType = expenseTypeRepository.findByName(expenseName);
            expenseTypeIds.add(expenseType.getId());
        }
        for(String accountName:accountTypes){
            AccountType accountType = accountTypeRepository.findByName(accountName);
            accountTypeIds.add(accountType.getId());
        }
        for(String categoryName:categoryTypes){
            CategoryType categoryType = categoryTypeRepository.findByName(categoryName);
            categoryTypeIds.add(categoryType.getId());
        }
        for(String subCategoryName:subCategoryTypes){
            SubCategoryType subCategoryType = subCategoryTypeRepository.findByName(subCategoryName);
            subCategoryTypeIds.add(subCategoryType.getId());
        }

        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date endDate = null;
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<Transactions> transactions = transactionsRepository.findByAll(userId,expenseTypeIds,accountTypeIds,categoryTypeIds,
                subCategoryTypeIds,startDate,endDate);
        return transactions;
    };
}


