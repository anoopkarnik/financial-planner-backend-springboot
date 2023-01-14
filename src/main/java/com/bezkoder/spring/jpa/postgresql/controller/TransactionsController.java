package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.Income;
import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.model.Transactions;
import com.bezkoder.spring.jpa.postgresql.repository.IncomeRepository;
import com.bezkoder.spring.jpa.postgresql.repository.TransactionsRepository;
import com.bezkoder.spring.jpa.postgresql.request.SubAccountTypeRequest;
import com.bezkoder.spring.jpa.postgresql.request.TransactionsListRequest;
import com.bezkoder.spring.jpa.postgresql.request.TransactionsRequest;
import com.bezkoder.spring.jpa.postgresql.service.AccountService;
import com.bezkoder.spring.jpa.postgresql.service.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@Autowired
	TransactionsService transactionsService;

	@Autowired
	IncomeRepository incomeRepository;

	@PostMapping
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
	public void deleteTransactions(@RequestParam("id") Long id){
		transactionsService.deleteTransactions(id);
	}

	@PostMapping("/income")
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