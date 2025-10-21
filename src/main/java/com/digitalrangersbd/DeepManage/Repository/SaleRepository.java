package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    boolean existsById(Long id);
    boolean existsByReference(String reference);
}
