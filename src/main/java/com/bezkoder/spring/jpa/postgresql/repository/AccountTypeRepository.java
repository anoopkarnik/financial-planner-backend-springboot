package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.AccountType;
import com.bezkoder.spring.jpa.postgresql.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    AccountType findByName(String name);

}
