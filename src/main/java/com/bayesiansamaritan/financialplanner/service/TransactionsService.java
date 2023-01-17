package com.bayesiansamaritan.financialplanner.service;

import com.bayesiansamaritan.financialplanner.model.Transactions;

import java.util.List;

public interface TransactionsService {


    public Transactions createTransactions(String name,Long cost, String expenseName, String categoryName, String SubCategoryName, String AccountName,
                                           String SubAccountName, Long userId);

    public List<Transactions> getTransactions(Long userId, List<String> expenseTypes, List<String> accountTypes, List<String> categoryTypes,
                                              List<String> subCategoryTypes, String dateFrom, String dateTo);

    public void deleteTransactions(Long id);

}
