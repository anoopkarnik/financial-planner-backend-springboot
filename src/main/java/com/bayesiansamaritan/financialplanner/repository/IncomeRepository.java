package com.bayesiansamaritan.financialplanner.repository;
import com.bayesiansamaritan.financialplanner.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserId(Long userId);

    void deleteById(Long id);

}
