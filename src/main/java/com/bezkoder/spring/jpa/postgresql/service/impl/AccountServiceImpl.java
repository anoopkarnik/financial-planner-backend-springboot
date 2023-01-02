package com.bezkoder.spring.jpa.postgresql.service.impl;

import com.bezkoder.spring.jpa.postgresql.model.AccountType;
import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;
import com.bezkoder.spring.jpa.postgresql.repository.AccountTypeRepository;
import com.bezkoder.spring.jpa.postgresql.repository.SubAccountTypeRepository;
import com.bezkoder.spring.jpa.postgresql.repository.UserProfileRepository;
import com.bezkoder.spring.jpa.postgresql.service.AccountService;
import com.bezkoder.spring.jpa.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    SubAccountTypeRepository subAccountTypeRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    AccountTypeRepository accountTypeRepository;


    public AccountServiceImpl(SubAccountTypeRepository subAccountTypeRepository,UserProfileRepository userProfileRepository, AccountTypeRepository accountTypeRepository){
        this.subAccountTypeRepository=subAccountTypeRepository;
        this.userProfileRepository = userProfileRepository;
        this.accountTypeRepository=accountTypeRepository;

    }

    @Override
    public SubAccountType createSubAccount(String accountName, Long balance, Boolean freeLiquidity, Boolean liquidity, String name, Long userId){


        AccountType accountType = accountTypeRepository.findByName(accountName);
        Long accountTypeId = accountType.getId();

        SubAccountType subAccountType = subAccountTypeRepository.save(new SubAccountType(name,balance,accountTypeId,userId,freeLiquidity,liquidity));
        return subAccountType;
    };

    @Override
    public List<SubAccountType> getSubAccountByUserAndAccount(Long userId, String accountName){
        AccountType accountType = accountTypeRepository.findByName(accountName);
        Long accountTypeId = accountType.getId();

        List<SubAccountType> subAccountTypeList = subAccountTypeRepository.findByUserIdAndAccountTypeId(userId,accountTypeId);
        return subAccountTypeList;

    };

    @Override
    public List<SubAccountType> getSubAccountByUser(Long userId){
        List<SubAccountType> subAccountTypeList = subAccountTypeRepository.findByUserId(userId);
        return subAccountTypeList;

    };
}


