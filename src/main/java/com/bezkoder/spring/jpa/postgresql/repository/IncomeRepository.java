package com.bezkoder.spring.jpa.postgresql.repository;
import com.bezkoder.spring.jpa.postgresql.model.BudgetPlan;
import com.bezkoder.spring.jpa.postgresql.model.Income;
import com.bezkoder.spring.jpa.postgresql.model.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserId(Long userId);

}
