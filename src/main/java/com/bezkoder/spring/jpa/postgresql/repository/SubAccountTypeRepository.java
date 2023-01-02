package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.SubAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubAccountTypeRepository extends JpaRepository<SubAccountType, Long> {
  List<SubAccountType> findByUserIdAndAccountTypeId(Long userId,Long subAccountTypeId);

  List<SubAccountType> findByUserId(Long userId);

  SubAccountType findByName(String name);

}
