package com.bayesiansamaritan.financialplanner.repository;

import com.bayesiansamaritan.financialplanner.enums.RoleEnum;
import com.bayesiansamaritan.financialplanner.model.Role;
import com.bayesiansamaritan.financialplanner.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleEnum name);

}
