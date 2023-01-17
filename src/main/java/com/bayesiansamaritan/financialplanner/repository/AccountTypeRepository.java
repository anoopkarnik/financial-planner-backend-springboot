package com.bayesiansamaritan.financialplanner.repository;

import com.bayesiansamaritan.financialplanner.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    AccountType findByName(String name);
    Optional<AccountType> findById(Long Id);

    @Transactional
    @Modifying
    @Query("update AccountType set name = :name,updated_at=now() where id=:id")
    public void modifyName(@Param("id") Long id, @Param("name") String name);

}
