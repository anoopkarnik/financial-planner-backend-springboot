package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;

import java.util.List;

public interface AccountService {

    public SubAccountType createSubAccount(String accountName, Long balance, Boolean freeLiquidity, Boolean liquidity, String name, String userName);

    public List<SubAccountType> getSubAccountByUserAndAccount(String userName, String accountName);

}
