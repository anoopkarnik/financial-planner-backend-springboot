package com.bayesiansamaritan.financialplanner.controller;

import com.bayesiansamaritan.financialplanner.model.AccountType;
import com.bayesiansamaritan.financialplanner.model.CategoryType;
import com.bayesiansamaritan.financialplanner.model.ExpenseType;
import com.bayesiansamaritan.financialplanner.model.SubCategoryType;
import com.bayesiansamaritan.financialplanner.repository.AccountTypeRepository;
import com.bayesiansamaritan.financialplanner.repository.CategoryTypeRepository;
import com.bayesiansamaritan.financialplanner.repository.ExpenseTypeRepository;
import com.bayesiansamaritan.financialplanner.repository.SubCategoryTypeRepository;
import com.bayesiansamaritan.financialplanner.response.AccountBalanceResponse;
import com.bayesiansamaritan.financialplanner.model.*;
import com.bayesiansamaritan.financialplanner.repository.*;
import com.bayesiansamaritan.financialplanner.service.CommonsService;
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
@RequestMapping("/api/commons")
public class CommonsController {
	@Autowired
	CommonsService commonsService;
	@Autowired
	AccountTypeRepository accountTypeRepository;
	@Autowired
	ExpenseTypeRepository expenseTypeRepository;
	@Autowired
	CategoryTypeRepository categoryTypeRepository;
	@Autowired
	SubCategoryTypeRepository subCategoryTypeRepository;

	@GetMapping("/accounts")
	public ResponseEntity<List<AccountType>> getAllAccounts() {
		try {
			List<AccountType> accountTypes =commonsService.getAllAccountTypes();
			if (accountTypes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(accountTypes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/accounts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AccountType> createAccountType(@RequestBody AccountType accountType) {
		try {
			AccountType _accountType = commonsService.createAccountType(accountType.getName());
			return new ResponseEntity<>(_accountType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/accounts")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAccountType(@RequestParam Long id) {
		try {
			accountTypeRepository.deleteById(id);
		} catch (Exception e) {

		}
	}
	@PatchMapping("/accounts")
	@PreAuthorize("hasRole('ADMIN')")
	public void modifyAccountType(@RequestParam Long id,@RequestParam String name) {
		try {
			accountTypeRepository.modifyName(id,name);
		} catch (Exception e) {

		}
	}

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryType>> getAllCategories() {
		try {
			List<CategoryType> categoryTypes = commonsService.getAllCategoryTypes();
			if (categoryTypes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(categoryTypes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/categories")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryType> createCategoryType(@RequestBody CategoryType categoryType) {
		try {
			CategoryType _categoryType = commonsService.createCategoryType(categoryType.getName());
			return new ResponseEntity<>(_categoryType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/categories")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteCategoryType(@RequestParam Long id) {
		try {
			categoryTypeRepository.deleteById(id);
		} catch (Exception e) {

		}
	}

	@PatchMapping("/categories")
	@PreAuthorize("hasRole('ADMIN')")
	public void modifyCategoryType(@RequestParam Long id,@RequestParam String name) {
		try {
			categoryTypeRepository.modifyName(id,name);
		} catch (Exception e) {

		}
	}


	@GetMapping("/subCategories")
	public ResponseEntity<List<SubCategoryType>> getAllSubCategories() {
		try {
			List<SubCategoryType> subCategoryTypes = commonsService.getAllSubCategoryTypes();
			if (subCategoryTypes .isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(subCategoryTypes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/subCategories")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SubCategoryType> createSubCategoryType(@RequestBody SubCategoryType subCategoryType) {
		try {
			SubCategoryType _subCategoryType = commonsService.createSubCategoryType(subCategoryType.getName());
			return new ResponseEntity<>(_subCategoryType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/subCategories")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteSubCategoryType(@RequestParam Long id) {
		try {
			subCategoryTypeRepository.deleteById(id);
		} catch (Exception e) {

		}
	}

	@PatchMapping("/subCategories")
	@PreAuthorize("hasRole('ADMIN')")
	public void modifySubCategoryType(@RequestParam Long id,@RequestParam String name) {
		try {
			subCategoryTypeRepository.modifyName(id,name);
		} catch (Exception e) {

		}
	}


	@GetMapping("/expenses")
	public ResponseEntity<List<ExpenseType>> getAllExpenses() {
		try {
			List<ExpenseType> expenseTypes = commonsService.getAllExpenseTypes();
			if (expenseTypes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(expenseTypes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/expenses")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ExpenseType> createExpenseType(@RequestBody ExpenseType expenseType) {
		try {
			ExpenseType _expenseType = commonsService.createExpenseType(expenseType.getName());
			return new ResponseEntity<>(_expenseType, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/expenses")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteExpenseType(@RequestParam Long id) {
		try {
			expenseTypeRepository.deleteById(id);
		} catch (Exception e) {

		}
	}

	@PatchMapping("/expenses")
	@PreAuthorize("hasRole('ADMIN')")
	public void modifyExpenseType(@RequestParam Long id,@RequestParam String name) {
		try {
			expenseTypeRepository.modifyName(id,name);
		} catch (Exception e) {

		}
	}

}