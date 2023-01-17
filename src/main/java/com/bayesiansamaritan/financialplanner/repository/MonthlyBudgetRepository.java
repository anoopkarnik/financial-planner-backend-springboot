package com.bayesiansamaritan.financialplanner.repository;
import com.bayesiansamaritan.financialplanner.model.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
  List<MonthlyBudget> findByUserIdAndExpenseTypeId(Long userId,Long ExpenseTypeId);

  void deleteById(Long id);

  @Transactional
  @Modifying
  @Query("update MonthlyBudget set cost = :cost,updated_at=now() where id=:id")
  public void changeCost(@Param("id") Long id, @Param("cost") Long cost);



}
