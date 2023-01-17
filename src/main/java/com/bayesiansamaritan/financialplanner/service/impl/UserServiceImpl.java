package com.bayesiansamaritan.financialplanner.service.impl;

import com.bayesiansamaritan.financialplanner.model.UserProfile;
import com.bayesiansamaritan.financialplanner.repository.UserProfileRepository;
import com.bayesiansamaritan.financialplanner.model.*;
import com.bayesiansamaritan.financialplanner.repository.*;
import com.bayesiansamaritan.financialplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserProfileRepository userProfileRepository;


    public UserServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository=userProfileRepository;

    }

    @Override
    public UserProfile createUser(String name,String email, String password){
        UserProfile userProfile = userProfileRepository.save(new UserProfile(name,email,password));
        return userProfile;
    }


}


