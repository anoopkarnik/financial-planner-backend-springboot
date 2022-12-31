package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.CategoryType;
import com.bezkoder.spring.jpa.postgresql.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
  ExpenseType findByName(String name);

}
