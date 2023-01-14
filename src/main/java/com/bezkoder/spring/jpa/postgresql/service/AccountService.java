package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.AccountType;
import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public SubAccountType createSubAccount(String accountName, Long balance, Boolean freeLiquidity, Boolean liquidity, String name, Long userId);
    public List<SubAccountType> getSubAccountByUserAndAccount(Long userId, String accountName);
    public List<SubAccountType> getSubAccountByUser(Long userId);
    public void updateBalance(Long id, Long cost);
    public void changeBalance(Long id, Long balance);

    public void deleteAccounts(Long id);


}
