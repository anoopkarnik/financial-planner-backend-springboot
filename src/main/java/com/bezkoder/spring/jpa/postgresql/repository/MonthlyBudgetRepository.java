package com.bezkoder.spring.jpa.postgresql.repository;
import com.bezkoder.spring.jpa.postgresql.model.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
  List<MonthlyBudget> findByUserId(Long userId);

}
