package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.*;
import com.bezkoder.spring.jpa.postgresql.service.CommonsService;
import com.bezkoder.spring.jpa.postgresql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin(origins = {"http://localhost:3001","http://financial-planner-react.anoopkarnik.net/"})
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;



	@PostMapping
	public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
		try {
			UserProfile _userProfile = userService.createUser(userProfile.getName());
			return new ResponseEntity<>(_userProfile, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}