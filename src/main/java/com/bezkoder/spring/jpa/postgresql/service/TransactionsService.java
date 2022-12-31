package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.Transactions;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;
import com.bezkoder.spring.jpa.postgresql.repository.TransactionsRepository;

import java.util.List;

public interface TransactionsService {

    public List<Transactions> getTransactionsByUserId(Long userId);

    public Transactions createTransactions(String name,Long cost, String expenseName, String categoryName, String SubCategoryName, String AccountName,
                                           String SubAccountName, String userName);

}
