package com.bayesiansamaritan.financialplanner.controller;

import com.bayesiansamaritan.financialplanner.model.Income;
import com.bayesiansamaritan.financialplanner.repository.IncomeRepository;
import com.bayesiansamaritan.financialplanner.request.TransactionsListRequest;
import com.bayesiansamaritan.financialplanner.request.TransactionsRequest;
import com.bayesiansamaritan.financialplanner.service.TransactionsService;
import com.bayesiansamaritan.financialplanner.model.Transactions;
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
@RequestMapping("/api/transactions")
public class TransactionsController {

	@Autowired
	TransactionsService transactionsService;

	@Autowired
	IncomeRepository incomeRepository;

	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Transactions> createTransactions(@RequestBody TransactionsRequest transactionsRequest)
{
		try {
			Transactions transactions = transactionsService.createTransactions(transactionsRequest.getName(),transactionsRequest.getCost(),
					transactionsRequest.getExpenseName(),transactionsRequest.getCategoryName(),transactionsRequest.getSubCategoryName(),
					transactionsRequest.getAccountName(),transactionsRequest.getSubAccountName(),transactionsRequest.getUserId());
			return new ResponseEntity<>(transactions, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Transactions>> getTransactions(@RequestBody TransactionsListRequest transactionsListRequest) {
		try {
			List<Transactions> transactionsList =transactionsService.getTransactions(transactionsListRequest.getUserId(),transactionsListRequest.getExpenseTypes(),
					transactionsListRequest.getAccountTypes(),transactionsListRequest.getCategoryTypes(),transactionsListRequest.getSubCategoryTypes(),
					transactionsListRequest.getDateFrom(),transactionsListRequest.getDateTo());
			if (transactionsList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(transactionsList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void deleteTransactions(@RequestParam("id") Long id){
		transactionsService.deleteTransactions(id);
	}

	@PostMapping("/income")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Income> createIncome(@RequestBody Income income)
	{
		try {
			Income _income = incomeRepository.save(new Income(income.getName(),income.getIncome(),true,income.getUserId()));
			return new ResponseEntity<>(_income, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}