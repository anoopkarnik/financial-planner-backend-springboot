package com.bayesiansamaritan.financialplanner.security.services;

import com.bayesiansamaritan.financialplanner.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bayesiansamaritan.financialplanner.model.UserProfile;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserProfileRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        UserProfile user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));
        return UserDetailsImpl.build(user);

    }
}
