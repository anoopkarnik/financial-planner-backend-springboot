package com.bayesiansamaritan.financialplanner.controller;

import com.bayesiansamaritan.financialplanner.model.Fund;
import com.bayesiansamaritan.financialplanner.repository.FundRepository;
import com.bayesiansamaritan.financialplanner.response.FundResponse;
import com.bayesiansamaritan.financialplanner.service.AccountService;
import com.bayesiansamaritan.financialplanner.service.FundService;
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
@RequestMapping("/api/funds")
public class FundController {
	@Autowired
	private FundRepository fundRepository;
	@Autowired
	AccountService accountService;

	@Autowired
	FundService fundService;

	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Fund> createFund(@RequestBody Fund fund)
{
		try {
			Fund _fund = fundRepository.save(new Fund(fund.getName(), fund.getAmountAllocated(),fund.getAmountNeeded(),fund.getUserId(),true));
			return new ResponseEntity<>(_fund, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Fund>> getFund(@RequestParam("userId") Long userId) {
		try {
			List<Fund> funds= fundRepository.findByUserId(userId);
			if (funds.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(funds, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/needed")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void changeAmountNeeded(@RequestParam("id") Long id, @RequestParam("amountNeeded") Long amountNeeded){
		try {
			fundRepository.updateAmountNeeded(id,amountNeeded);
		} catch (Exception e) {

		}
;	}

	@PatchMapping("/allocated")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void changeAmountAllocated(@RequestParam("id") Long id, @RequestParam("amountAllocated") Long amountAllocated){
		try {
			fundRepository.updateAmountAllocated(id,amountAllocated);
		} catch (Exception e) {

		}
		;	}

	@DeleteMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void deleteFunds(@RequestParam("id") Long id){
		fundRepository.deleteById(id);
	}

	@GetMapping("/summary")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<FundResponse> getFundSummary(@RequestParam("userId") Long userId){

		try {
			FundResponse fund= fundService.getFundSummary(userId);

			return new ResponseEntity<>(fund, HttpStatus.OK);
		} catch (Exception e) {
			FundResponse fund = new FundResponse();
			fund.setAmountAllocated(0L);
			fund.setAmountAvailable(0L);
			fund.setTimeLeft(100L);
			fund.setTotalAmount(0L);
			fund.setFinancialIndependenceAmount(50000000L);
			fund.setFinancialIndependencePercentage(0L);
			return new ResponseEntity<>(fund, HttpStatus.OK);
		}
	}


}