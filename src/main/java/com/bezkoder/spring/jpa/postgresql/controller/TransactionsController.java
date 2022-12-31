package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.model.Transactions;
import com.bezkoder.spring.jpa.postgresql.repository.TransactionsRepository;
import com.bezkoder.spring.jpa.postgresql.request.SubAccountTypeRequest;
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
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@Autowired
	TransactionsService transactionsService;

	@PostMapping
	public ResponseEntity<Transactions> createTransactions(@RequestBody TransactionsRequest transactionsRequest)
{
		try {
			Transactions transactions = transactionsService.createTransactions(transactionsRequest.getName(),transactionsRequest.getCost(),
					transactionsRequest.getExpenseName(),transactionsRequest.getCategoryName(),transactionsRequest.getSubCategoryName(),
					transactionsRequest.getAccountName(),transactionsRequest.getSubAccountName(),transactionsRequest.getUserName());
			return new ResponseEntity<>(transactions, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}