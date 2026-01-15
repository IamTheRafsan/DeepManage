package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Dto.PurchaseResponseDto;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsById(Long id);
    boolean existsByReference(String reference);

    List<Purchase> findByDeletedFalse();
}
