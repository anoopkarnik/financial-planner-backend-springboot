package com.bayesiansamaritan.financialplanner.controller;

import com.bayesiansamaritan.financialplanner.model.Income;
import com.bayesiansamaritan.financialplanner.model.MonthlyBudget;
import com.bayesiansamaritan.financialplanner.repository.BudgetPlanRepository;
import com.bayesiansamaritan.financialplanner.repository.IncomeRepository;
import com.bayesiansamaritan.financialplanner.repository.MonthlyBudgetRepository;
import com.bayesiansamaritan.financialplanner.response.BudgetPlanResponse;
import com.bayesiansamaritan.financialplanner.model.BudgetPlan;
import com.bayesiansamaritan.financialplanner.request.BudgetPlanRequest;
import com.bayesiansamaritan.financialplanner.request.MonthlyBudgetRequest;
import com.bayesiansamaritan.financialplanner.response.MonthlyBudgetResponse;
import com.bayesiansamaritan.financialplanner.service.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/budget")
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
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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
	@DeleteMapping("/income")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void deleteIncomes(@RequestParam("id") Long id){
		incomeRepository.deleteById(id);
	}


	@GetMapping("/planAmount")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void deleteBudget(@RequestParam("id") Long id){
		monthlyBudgetRepository.deleteById(id);
	}

	@PatchMapping("/monthly")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void changeCost(@RequestParam("id") Long id, @RequestParam("cost") Long cost){
		try {
			monthlyBudgetRepository.changeCost(id,cost);
		} catch (Exception e) {

		};
	};

	@PatchMapping("/plan")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void changePlanPercentage(@RequestParam("id") Long id, @RequestParam("plan_percentage") Long plan_percentage){
		try {
			budgetPlanRepository.changePlanPercentage(id,plan_percentage);
		} catch (Exception e) {

		};
	};

}