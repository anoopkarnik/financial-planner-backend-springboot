package com.bezkoder.spring.jpa.postgresql.repository;
import com.bezkoder.spring.jpa.postgresql.model.Fund;
import com.bezkoder.spring.jpa.postgresql.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {

    List<Fund> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("update Fund set amount_needed = :amountNeeded,updated_at=now() where id=:id")
    public void updateAmountNeeded(@Param("id") Long id, @Param("amountNeeded") Long amountNeeded);

    @Transactional
    @Modifying
    @Query("update Fund set amount_allocated = :amountAllocated,updated_at=now() where id=:id")
    public void updateAmountAllocated(@Param("id") Long id,@Param("amountAllocated") Long amountAllocated);

}
