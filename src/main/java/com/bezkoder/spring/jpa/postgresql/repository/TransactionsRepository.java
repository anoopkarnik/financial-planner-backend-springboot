package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

  @Query("Select t from Transactions t where t.userId = :user_id and t.expenseTypeId in :expense_type_id and t.accountId in :account_id and " +
          "t.categoryId in :category_id and t.subCategoryId in :sub_category_id " +
          "and t.createdAt between :start_date and :end_date")
  List<Transactions> findByAll(@Param("user_id") Long userId, @Param("expense_type_id") List<Long> expenseTypeIds,
                               @Param("account_id") List<Long> accountTypeIds, @Param("category_id") List<Long> categoryTypeIds,
                               @Param("sub_category_id") List<Long> subCategoryTypeIds,
                               @Param("start_date") Date dateFrom, @Param("end_date") Date dateTo);

  @Query("Select t from Transactions t where t.userId = :user_id and t.expenseTypeId=:expense_type_id and " +
          "t.categoryId=:category_id and t.subCategoryId=:sub_category_id " +
          "and t.createdAt between :start_date and :end_date")
  List<Transactions> findBySome(@Param("user_id") Long userId, @Param("expense_type_id") Long expenseTypeId,
                                @Param("category_id") Long categoryTypeId,
                                @Param("sub_category_id") Long subCategoryTypeId,
                                @Param("start_date") Date dateFrom, @Param("end_date") Date dateTo);

  void deleteById(Long id);

}
