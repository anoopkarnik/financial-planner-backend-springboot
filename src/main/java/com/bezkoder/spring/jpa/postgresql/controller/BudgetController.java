package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.BudgetPlan;
import com.bezkoder.spring.jpa.postgresql.model.Income;
import com.bezkoder.spring.jpa.postgresql.model.MonthlyBudget;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;
import com.bezkoder.spring.jpa.postgresql.repository.BudgetPlanRepository;
import com.bezkoder.spring.jpa.postgresql.repository.IncomeRepository;
import com.bezkoder.spring.jpa.postgresql.repository.MonthlyBudgetRepository;
import com.bezkoder.spring.jpa.postgresql.request.BudgetPlanRequest;
import com.bezkoder.spring.jpa.postgresql.request.MonthlyBudgetRequest;
import com.bezkoder.spring.jpa.postgresql.request.TransactionsListRequest;
import com.bezkoder.spring.jpa.postgresql.request.TransactionsRequest;
import com.bezkoder.spring.jpa.postgresql.response.BudgetPlanResponse;
import com.bezkoder.spring.jpa.postgresql.response.MonthlyBudgetResponse;
import com.bezkoder.spring.jpa.postgresql.service.BudgetService;
import com.bezkoder.spring.jpa.postgresql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/budget")
public class BudgetController {
	@Autowired
	private MonthlyBudgetRepository monthlyBudgetRepository;
	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private BudgetPlanRepository budgetPlanRepository;
	@Autowired
	BudgetService budgetService;



	@PostMapping("/monthly")
	public ResponseEntity<MonthlyBudget> createMonthlyBudget(@RequestBody MonthlyBudgetRequest monthlyBudgetRequest) {
		try {
			MonthlyBudget monthlyBudget= budgetService.createMonthlyBudget(monthlyBudgetRequest.getCost(),
					monthlyBudgetRequest.getExpenseName(),monthlyBudgetRequest.getCategoryName(),monthlyBudgetRequest.getSubCategoryName(),
					monthlyBudgetRequest.getUserId());
			return new ResponseEntity<>(monthlyBudget, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/monthly")
	public ResponseEntity<List<MonthlyBudgetResponse>> getMonthlyBudget(@RequestParam("userId") Long userId,@RequestParam("expenseType") String expenseType) {
		try {
			List<MonthlyBudgetResponse> monthlyBudgets = budgetService.getMonthlyBudget(expenseType,userId);
			if (monthlyBudgets.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(monthlyBudgets, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/plan")
	public ResponseEntity<BudgetPlan> createBudgetPlan(@RequestBody BudgetPlanRequest budgetPlanRequest) {
		try {
			BudgetPlan budgetPlan = budgetService.createBudgetPlan(budgetPlanRequest.getExpenseName(),budgetPlanRequest.getPlanPercentage(),
					budgetPlanRequest.getUserId());
			return new ResponseEntity<>(budgetPlan, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/income")
	public ResponseEntity<List<Income>> getIncome(@RequestParam("userId") Long userId) {
		try {
			List<Income> incomes= incomeRepository.findByUserId(userId);
			if (incomes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(incomes, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/planAmount")
	public ResponseEntity<List<BudgetPlanResponse>> getBudgetPlanAmount(@RequestParam("userId") Long userId) {
		try {
			List<BudgetPlanResponse> budgetPlans = budgetService.getBudgetPlans(userId);
			if (budgetPlans.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(budgetPlans, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/monthly")
	public void deleteBudget(@RequestParam("id") Long id){
		monthlyBudgetRepository.deleteById(id);
	}

	@PatchMapping("/monthly")
	public void changeCost(@RequestParam("id") Long id, @RequestParam("cost") Long cost){
		try {
			monthlyBudgetRepository.changeCost(id,cost);
		} catch (Exception e) {

		};
	};

	@PatchMapping("/plan")
	public void changePlanPercentage(@RequestParam("id") Long id, @RequestParam("plan_percentage") Long plan_percentage){
		try {
			budgetPlanRepository.changePlanPercentage(id,plan_percentage);
		} catch (Exception e) {

		};
	};

}