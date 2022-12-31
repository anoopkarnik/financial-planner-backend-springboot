package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.*;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    public UserProfile createUser(String name);

}
