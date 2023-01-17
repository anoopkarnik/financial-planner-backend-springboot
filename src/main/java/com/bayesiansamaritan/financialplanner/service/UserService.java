package com.bayesiansamaritan.financialplanner.service;

import com.bayesiansamaritan.financialplanner.model.UserProfile;
import com.bayesiansamaritan.financialplanner.model.*;

public interface UserService {

    public UserProfile createUser(String name,String email, String password);

}
