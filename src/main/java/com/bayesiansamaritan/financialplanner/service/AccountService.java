package com.bayesiansamaritan.financialplanner.service;

import com.bayesiansamaritan.financialplanner.model.SubAccountType;
import com.bayesiansamaritan.financialplanner.response.AccountBalanceResponse;

import java.util.List;

public interface AccountService {

    public List<AccountBalanceResponse>  getAllAccountBalances(Long userId);
    public SubAccountType createSubAccount(String accountName, Long balance, Boolean freeLiquidity, Boolean liquidity, String name, Long userId);
    public List<SubAccountType> getSubAccountByUserAndAccount(Long userId, String accountName);
    public List<SubAccountType> getSubAccountByUser(Long userId);
    public void updateBalance(Long id, Long cost);
    public void changeBalance(Long id, Long balance);

    public void deleteAccounts(Long id);


}
