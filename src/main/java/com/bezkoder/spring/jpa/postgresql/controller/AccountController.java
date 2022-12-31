package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.AccountType;
import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;
import com.bezkoder.spring.jpa.postgresql.request.SubAccountTypeRequest;
import com.bezkoder.spring.jpa.postgresql.service.AccountService;
import com.bezkoder.spring.jpa.postgresql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;

	@PostMapping
	public ResponseEntity<SubAccountType> createSubAccountType(@RequestBody SubAccountTypeRequest subAccountTypeRequest)
{
		try {
			SubAccountType _subAccountType = accountService.createSubAccount(subAccountTypeRequest.getAccountName(),subAccountTypeRequest.getBalance(),
					subAccountTypeRequest.getFreeLiquidity(),subAccountTypeRequest.getLiquidity(),subAccountTypeRequest.getName(),subAccountTypeRequest.getUserName());
			return new ResponseEntity<>(_subAccountType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<SubAccountType>> getSubAccountByUserAndAccount(@RequestParam("user") String userName, @RequestParam("account") String accountName) {
		try {
			List<SubAccountType> subAccountTypeList = accountService.getSubAccountByUserAndAccount(userName,accountName);
			if (subAccountTypeList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(subAccountTypeList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}