package com.bayesiansamaritan.financialplanner.controller;

import com.bayesiansamaritan.financialplanner.model.SubAccountType;
import com.bayesiansamaritan.financialplanner.request.SubAccountTypeRequest;
import com.bayesiansamaritan.financialplanner.response.AccountBalanceResponse;
import com.bayesiansamaritan.financialplanner.service.AccountService;
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
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;



	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<SubAccountType> createSubAccountType(@RequestBody SubAccountTypeRequest subAccountTypeRequest)
{
		try {
			SubAccountType _subAccountType = accountService.createSubAccount(subAccountTypeRequest.getAccountName(),subAccountTypeRequest.getBalance(),
					subAccountTypeRequest.getFreeLiquidity(),subAccountTypeRequest.getLiquidity(),subAccountTypeRequest.getName(),subAccountTypeRequest.getUserId());
			return new ResponseEntity<>(_subAccountType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/1")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<SubAccountType>> getSubAccountByUserAndAccount(@RequestParam("user") Long userId, @RequestParam("account") String accountName) {
		try {
			List<SubAccountType> subAccountTypeList = accountService.getSubAccountByUserAndAccount(userId,accountName);
			if (subAccountTypeList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(subAccountTypeList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/2")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<SubAccountType>> getSubAccountByUser(@RequestParam("user") Long userId) {
		try {
			List<SubAccountType> subAccountTypeList = accountService.getSubAccountByUser(userId);
			if (subAccountTypeList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(subAccountTypeList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/balances")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<AccountBalanceResponse> > getAllAccountBalances(@RequestParam("userId") Long userId) {
		try {
			List<AccountBalanceResponse> accountTypes = accountService.getAllAccountBalances(userId);
			if (accountTypes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(accountTypes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void changeBalance(@RequestParam("id") Long id, @RequestParam("cost") Long balance){
		try {
			accountService.changeBalance(id,balance);
		} catch (Exception e) {

		}
;	}

	@DeleteMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void deleteAccounts(@RequestParam("id") Long id){
		accountService.deleteAccounts(id);
	}


}