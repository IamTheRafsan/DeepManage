package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutletRepository extends JpaRepository<Outlet, Long> {

    boolean existsById(Long id);
    boolean existsByEmail(String email);
    boolean existsByName(String name);

}
