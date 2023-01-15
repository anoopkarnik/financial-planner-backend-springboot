package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.Fund;
import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import com.bezkoder.spring.jpa.postgresql.repository.FundRepository;
import com.bezkoder.spring.jpa.postgresql.request.SubAccountTypeRequest;
import com.bezkoder.spring.jpa.postgresql.response.FundResponse;
import com.bezkoder.spring.jpa.postgresql.service.AccountService;
import com.bezkoder.spring.jpa.postgresql.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin(origins = {"http://localhost:3001","http://financial-planner-react.anoopkarnik.net/"})
@RestController
@RequestMapping("/funds")
public class FundController {
	@Autowired
	private FundRepository fundRepository;
	@Autowired
	AccountService accountService;

	@Autowired
	FundService fundService;

	@PostMapping
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
	public void changeAmountNeeded(@RequestParam("id") Long id, @RequestParam("amountNeeded") Long amountNeeded){
		try {
			fundRepository.updateAmountNeeded(id,amountNeeded);
		} catch (Exception e) {

		}
;	}

	@PatchMapping("/allocated")
	public void changeAmountAllocated(@RequestParam("id") Long id, @RequestParam("amountAllocated") Long amountAllocated){
		try {
			fundRepository.updateAmountAllocated(id,amountAllocated);
		} catch (Exception e) {

		}
		;	}

	@DeleteMapping
	public void deleteFunds(@RequestParam("id") Long id){
		fundRepository.deleteById(id);
	}

	@GetMapping("/summary")
	public ResponseEntity<FundResponse> getFundSummary(@RequestParam("userId") Long userId){

		try {
			FundResponse fund= fundService.getFundSummary(userId);

			return new ResponseEntity<>(fund, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}