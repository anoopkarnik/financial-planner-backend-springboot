package com.bayesiansamaritan.financialplanner.repository;

import com.bayesiansamaritan.financialplanner.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
  ExpenseType findByName(String name);

  @Transactional
  @Modifying
  @Query("update ExpenseType set name = :name,updated_at=now() where id=:id")
  public void modifyName(@Param("id") Long id, @Param("name") String name);

}
