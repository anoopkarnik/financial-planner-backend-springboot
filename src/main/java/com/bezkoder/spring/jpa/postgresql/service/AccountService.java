package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;

import java.util.List;

public interface AccountService {

    public SubAccountType createSubAccount(String accountName, Long balance, Boolean freeLiquidity, Boolean liquidity, String name, Long userId);

    public List<SubAccountType> getSubAccountByUserAndAccount(Long userId, String accountName);

}
