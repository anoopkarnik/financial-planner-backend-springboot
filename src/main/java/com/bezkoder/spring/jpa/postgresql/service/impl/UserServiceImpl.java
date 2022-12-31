package com.bezkoder.spring.jpa.postgresql.service.impl;

import com.bezkoder.spring.jpa.postgresql.model.*;
import com.bezkoder.spring.jpa.postgresql.repository.*;
import com.bezkoder.spring.jpa.postgresql.service.UserService;
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
    public UserProfile createUser(String name){
        UserProfile userProfile = userProfileRepository.save(new UserProfile(name));
        return userProfile;
    }


}


