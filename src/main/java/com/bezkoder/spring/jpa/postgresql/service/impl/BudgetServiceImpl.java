package com.bezkoder.spring.jpa.postgresql.service.impl;

import com.bezkoder.spring.jpa.postgresql.model.*;
import com.bezkoder.spring.jpa.postgresql.repository.*;
import com.bezkoder.spring.jpa.postgresql.response.BudgetPlanResponse;
import com.bezkoder.spring.jpa.postgresql.response.MonthlyBudgetResponse;
import com.bezkoder.spring.jpa.postgresql.service.BudgetService;
import com.bezkoder.spring.jpa.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    MonthlyBudgetRepository monthlyBudgetRepository;
    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    CategoryTypeRepository categoryTypeRepository;
    @Autowired
    ExpenseTypeRepository expenseTypeRepository;
    @Autowired
    SubCategoryTypeRepository subCategoryTypeRepository;
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private final BudgetPlanRepository budgetPlanRepository;
    @Autowired
    IncomeRepository incomeRepository;


    public BudgetServiceImpl(MonthlyBudgetRepository monthlyBudgetRepository,
                             BudgetPlanRepository budgetPlanRepository){
        this.monthlyBudgetRepository=monthlyBudgetRepository;

        this.budgetPlanRepository = budgetPlanRepository;
    }

    @Override
    public MonthlyBudget createMonthlyBudget(Long cost, String expenseName, String categoryName, String subCategoryName, Long userId){

        ExpenseType expenseType = expenseTypeRepository.findByName(expenseName);
        Long expenseTypeId = expenseType.getId();

        CategoryType categoryType = categoryTypeRepository.findByName(categoryName);
        Long categoryTypeId = categoryType.getId();

        SubCategoryType subCategoryType = subCategoryTypeRepository.findByName(subCategoryName);
        Long subCategoryTypeId = subCategoryType.getId();

        Boolean active = true;

        MonthlyBudget monthlyBudget = monthlyBudgetRepository.save(new MonthlyBudget(cost,expenseTypeId,active,categoryTypeId,subCategoryTypeId,userId));
        return monthlyBudget;
    };

    @Override
    public List<MonthlyBudgetResponse> getMonthlyBudget(String expenseName, Long userId) throws ParseException {
        ExpenseType expenseType = expenseTypeRepository.findByName(expenseName);
        Long expenseTypeId = expenseType.getId();

        List<MonthlyBudgetResponse> monthlyBudgetResponses = new ArrayList<>();

        List<MonthlyBudget> monthlyBudgets = monthlyBudgetRepository.findByUserIdAndExpenseTypeId(userId,expenseTypeId);
        LocalDate todayDate = LocalDate.now();
        Date startDate =  new SimpleDateFormat("yyyy-MM-dd").parse(todayDate.with(TemporalAdjusters.firstDayOfMonth()).toString());
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(todayDate.with(TemporalAdjusters.firstDayOfNextMonth()).toString());

        for (MonthlyBudget monthlyBudget:monthlyBudgets){
            Optional<CategoryType> categoryType = categoryTypeRepository.findById(monthlyBudget.getCategoryTypeId());
            Optional<SubCategoryType> subCategoryType = subCategoryTypeRepository.findById(monthlyBudget.getSubCategoryTypeId());
            List<Transactions> transactions = transactionsRepository.findBySome(userId,expenseTypeId,monthlyBudget.getCategoryTypeId(),
                    monthlyBudget.getSubCategoryTypeId(),startDate,endDate);
            Long amountSpent = 0L;
            for (Transactions transaction:transactions){
                amountSpent+=transaction.getCost();
            }
            MonthlyBudgetResponse monthlyBudgetResponse = new MonthlyBudgetResponse();
            monthlyBudgetResponse.setBudgetAmount(monthlyBudget.getCost());
            monthlyBudgetResponse.setCategoryName(categoryType.get().getName());
            monthlyBudgetResponse.setExpenseName(expenseName);
            monthlyBudgetResponse.setSubCategoryName(subCategoryType.get().getName());
            monthlyBudgetResponse.setId(monthlyBudget.getId());
            monthlyBudgetResponse.setAmountSpent(amountSpent);
            monthlyBudgetResponses.add(monthlyBudgetResponse);
        }
        return monthlyBudgetResponses;
    };


    @Override
    public BudgetPlan createBudgetPlan(String expenseName, Long planPercentage, Long userId){
        ExpenseType expenseType = expenseTypeRepository.findByName(expenseName);
        Long expenseTypeId = expenseType.getId();

        BudgetPlan budgetPlan = budgetPlanRepository.save(new BudgetPlan(planPercentage,expenseTypeId,userId));
        return budgetPlan;
    };

    @Override
    public List<BudgetPlanResponse> getBudgetPlans(Long userId) throws ParseException {

        List<ExpenseType> expenseTypes = expenseTypeRepository.findAll();
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        List<CategoryType> categoryTypes = categoryTypeRepository.findAll();
        List<SubCategoryType> subCategoryTypes = subCategoryTypeRepository.findAll();

        List<Long> accountTypeIds = new ArrayList<>();
        List<Long> categoryTypeIds = new ArrayList<>();
        List<Long> subCategoryTypeIds = new ArrayList<>();

        for(AccountType accountType:accountTypes){
            accountTypeIds.add(accountType.getId());
        }
        for(CategoryType categoryType:categoryTypes){
            categoryTypeIds.add(categoryType.getId());
        }
        for(SubCategoryType subCategoryType:subCategoryTypes){
            subCategoryTypeIds.add(subCategoryType.getId());
        }

        LocalDate todayDate = LocalDate.now();
        Date startDate =  new SimpleDateFormat("yyyy-MM-dd").parse(todayDate.with(TemporalAdjusters.firstDayOfMonth()).toString());
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(todayDate.with(TemporalAdjusters.firstDayOfNextMonth()).toString());

        List<Income> incomes = incomeRepository.findByUserId(userId);

        Long totalIncome = 0L;
        for(Income income:incomes){
            totalIncome+=income.getIncome();
        }


        List<BudgetPlanResponse> budgetPlanResponses = new ArrayList<>();
        for (ExpenseType expenseType:expenseTypes) {
            List<MonthlyBudget> monthlyBudgets = monthlyBudgetRepository.findByUserIdAndExpenseTypeId(userId,expenseType.getId());
            Long allottedTotal = 0L;
            for (MonthlyBudget monthlyBudget :monthlyBudgets){
                allottedTotal+=monthlyBudget.getCost();
            }
            List<Long> expenseTypeIds = new ArrayList<>();
            expenseTypeIds.add(expenseType.getId());
            List<Transactions> transactions = transactionsRepository.findByAll(userId,expenseTypeIds,accountTypeIds,categoryTypeIds,
                    subCategoryTypeIds,startDate,endDate);
            Long totalTransactions = 0L;
            for(Transactions transaction:transactions){
                totalTransactions+=transaction.getCost();
            }
            BudgetPlan budgetPlan = budgetPlanRepository.findByUserIdAndExpenseTypeId(userId, expenseType.getId());
            Long transactionPercentage = totalTransactions * 100 / totalIncome;
            BudgetPlanResponse budgetPlanResponse = new BudgetPlanResponse();
            budgetPlanResponse.setId(budgetPlan.getId());
            budgetPlanResponse.setExpenseName(expenseType.getName());
            budgetPlanResponse.setPlanPercentage(budgetPlan.getPlanPercentage());
            budgetPlanResponse.setTransactionTotal(totalTransactions);
            budgetPlanResponse.setTransactionPercentage(transactionPercentage);
            budgetPlanResponse.setPlanTotal(budgetPlan.getPlanPercentage() * totalIncome / 100);
            budgetPlanResponse.setAllottedTotal(allottedTotal);
            budgetPlanResponses.add(budgetPlanResponse);
        }
        return budgetPlanResponses;
    };
}


