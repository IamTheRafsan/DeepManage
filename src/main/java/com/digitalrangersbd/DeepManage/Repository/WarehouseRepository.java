package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    boolean existsById(Long id);
    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
