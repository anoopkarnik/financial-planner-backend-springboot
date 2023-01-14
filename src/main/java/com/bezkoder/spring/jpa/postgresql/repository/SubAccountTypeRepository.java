package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubAccountTypeRepository extends JpaRepository<SubAccountType, Long> {
  List<SubAccountType> findByUserIdAndAccountTypeId(Long userId,Long subAccountTypeId);

  List<SubAccountType> findByUserId(Long userId);

  SubAccountType findByName(String name);

  @Transactional
  @Modifying
  @Query("update SubAccountType set balance = balance - :cost,updated_at=now() where id=:id")
  public void updateBalance(@Param("id") Long id,@Param("cost") Long cost);

  @Transactional
  @Modifying
  @Query("update SubAccountType set balance = :balance,updated_at=now() where id=:id")
  public void changeBalance(@Param("id") Long id,@Param("balance") Long balance);

  void deleteById(Long id);

}
