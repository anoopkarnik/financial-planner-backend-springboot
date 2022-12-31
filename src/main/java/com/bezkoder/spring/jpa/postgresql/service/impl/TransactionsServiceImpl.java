package com.bezkoder.spring.jpa.postgresql.service.impl;

import com.bezkoder.spring.jpa.postgresql.model.*;
import com.bezkoder.spring.jpa.postgresql.repository.*;
import com.bezkoder.spring.jpa.postgresql.service.TransactionsService;
import com.bezkoder.spring.jpa.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Transactions> getTransactionsByUserId(Long userId) {
        List<Transactions> transactions = new ArrayList<>();
        transactionsRepository.findByUserId(userId).forEach(transactions::add);
        return transactions;
    }

    @Override
    public Transactions createTransactions(String name,Long cost, String expenseName, String categoryName, String subCategoryName, String accountName,
                                           String subAccountName, String userName){

        UserProfile userProfile = userProfileRepository.findByName(userName);
        Long userId = userProfile.getId();

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
}


