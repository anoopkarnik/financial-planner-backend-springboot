package com.bayesiansamaritan.financialplanner.service.impl;

import com.bayesiansamaritan.financialplanner.model.*;
import com.bayesiansamaritan.financialplanner.repository.*;
import com.bayesiansamaritan.financialplanner.service.FundService;
import com.bayesiansamaritan.financialplanner.model.*;
import com.bayesiansamaritan.financialplanner.repository.*;
import com.bayesiansamaritan.financialplanner.response.FundResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundServiceImpl implements FundService {
    private final AccountTypeRepository accountTypeRepository;
    private final SubAccountTypeRepository subAccountTypeRepository;
    private final FundRepository fundRepository;
    private final BudgetPlanRepository budgetPlanRepository;
    private final ExpenseTypeRepository expenseTypeRepository;
    private final IncomeRepository incomeRepository;

    public FundServiceImpl(AccountTypeRepository accountTypeRepository,
                           SubAccountTypeRepository subAccountTypeRepository,
                           FundRepository fundRepository,
                           BudgetPlanRepository budgetPlanRepository,
                           ExpenseTypeRepository expenseTypeRepository,
                           IncomeRepository incomeRepository) {
        this.accountTypeRepository = accountTypeRepository;
        this.subAccountTypeRepository = subAccountTypeRepository;
        this.fundRepository = fundRepository;
        this.budgetPlanRepository = budgetPlanRepository;
        this.expenseTypeRepository = expenseTypeRepository;
        this.incomeRepository = incomeRepository;
    }

    @Override
    public FundResponse getFundSummary(Long userId){
        String accountName = "investments";
        AccountType accountType = accountTypeRepository.findByName(accountName);
        List<SubAccountType> subAccountTypes = subAccountTypeRepository.findByUserIdAndAccountTypeId(userId,accountType.getId());
        Long totalAmount = 0L;
        Long amountAvailable = 0L;
        Long amountAllocated = 0L;
        Long amountNeeded = 0L;

        for(SubAccountType subAccountType:subAccountTypes){
            totalAmount+=subAccountType.getBalance();
            if(subAccountType.getFreeLiquidity() || subAccountType.getLiquidity()){
                amountAvailable+=subAccountType.getBalance();
            }
        }

        List<Fund> funds = fundRepository.findByUserId(userId);
        for(Fund fund: funds){
            amountAllocated+=fund.getAmountAllocated();
            amountNeeded+=fund.getAmountNeeded();
        }

        String expenseName = "savings";
        ExpenseType expenseType = expenseTypeRepository.findByName(expenseName);
        BudgetPlan budgetPlan = budgetPlanRepository.findByUserIdAndExpenseTypeId(userId,expenseType.getId());
        List<Income> incomes = incomeRepository.findByUserId(userId);
        Long totalIncome = 0L;
        for(Income income: incomes){
            totalIncome+=income.getIncome();
        }
        Long spending = (100-budgetPlan.getPlanPercentage())*totalIncome/100;
        Long financialIndependenceAmount = spending*12*30+amountNeeded;
        Long financialIndependencePercentage = (totalAmount-amountAllocated)*100/financialIndependenceAmount;
        Long savingsPerMonth = budgetPlan.getPlanPercentage()*totalIncome/100;
        Long financialIndependenceAmountLeft = (100-financialIndependencePercentage)*financialIndependenceAmount/100;
        Long timeLeft = financialIndependenceAmountLeft/savingsPerMonth/12;
        FundResponse fundResponse = new FundResponse();
        fundResponse.setAmountAllocated(amountAllocated);
        fundResponse.setAmountAvailable(amountAvailable);
        fundResponse.setTotalAmount(totalAmount);
        fundResponse.setFinancialIndependenceAmount(financialIndependenceAmount);
        fundResponse.setFinancialIndependencePercentage(financialIndependencePercentage);
        fundResponse.setTimeLeft(timeLeft);
        return fundResponse;
    };





}


