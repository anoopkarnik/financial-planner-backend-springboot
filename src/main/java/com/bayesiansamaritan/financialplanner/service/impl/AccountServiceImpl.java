package com.bayesiansamaritan.financialplanner.service.impl;

import com.bayesiansamaritan.financialplanner.model.AccountType;
import com.bayesiansamaritan.financialplanner.model.SubAccountType;
import com.bayesiansamaritan.financialplanner.repository.AccountTypeRepository;
import com.bayesiansamaritan.financialplanner.repository.SubAccountTypeRepository;
import com.bayesiansamaritan.financialplanner.repository.UserProfileRepository;
import com.bayesiansamaritan.financialplanner.response.AccountBalanceResponse;
import com.bayesiansamaritan.financialplanner.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void updateBalance(Long id, Long cost){
        Optional<SubAccountType> subAccountType = subAccountTypeRepository.findById(id);
        subAccountTypeRepository.updateBalance(id,cost);
    };

    @Override
    public void changeBalance(Long id, Long balance){
        Optional<SubAccountType> subAccountType = subAccountTypeRepository.findById(id);
        subAccountTypeRepository.changeBalance(id,balance);
    };

    @Override
    public void deleteAccounts(Long id){
        subAccountTypeRepository.deleteById(id);
    }

    @Override
    public List<AccountBalanceResponse> getAllAccountBalances(Long userId){
        List<AccountType> accountTypes = new ArrayList<>();
        accountTypeRepository.findAll().forEach(accountTypes::add);
        List<AccountBalanceResponse> accountBalances = new ArrayList<>();
        for (AccountType accountType:accountTypes){
            List<SubAccountType> subAccountTypes =subAccountTypeRepository.findByUserIdAndAccountTypeId(userId,accountType.getId());
            Long balance = 0L;
            for(SubAccountType subAccountType:subAccountTypes){
                balance+=subAccountType.getBalance();
            }
            AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
            accountBalanceResponse.setId(accountType.getId());
            accountBalanceResponse.setName(accountType.getName());
            accountBalanceResponse.setBalance(balance);
            accountBalances.add(accountBalanceResponse);
        }
        return accountBalances;
    }


}


