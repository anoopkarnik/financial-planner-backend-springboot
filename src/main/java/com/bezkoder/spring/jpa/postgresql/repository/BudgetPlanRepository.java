package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Long> {
  List<BudgetPlan> findByUserId(Long userId);

}
