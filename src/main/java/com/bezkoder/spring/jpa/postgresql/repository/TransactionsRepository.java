package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

  @Query("Select t from Transactions t where t.userId = :user_id and t.expenseTypeId in :expense_type_id and t.accountId in :account_id and " +
          "t.categoryId in :category_id and t.subAccountId in :sub_account_id and t.subCategoryId in :sub_category_id")
  List<Transactions> findByAll(@Param("user_id") Long userId, @Param("expense_type_id") List<Long> expenseTypeIds,
                               @Param("account_id") List<Long> accountTypeIds, @Param("category_id") List<Long> categoryTypeIds,
                               @Param("sub_account_id") List<Long> subAccountTypeIds, @Param("sub_category_id") List<Long> subCategoryTypeIds);

}
