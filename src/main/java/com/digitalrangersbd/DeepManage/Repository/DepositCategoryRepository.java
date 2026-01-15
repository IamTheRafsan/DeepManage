package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.DepositCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositCategoryRepository extends JpaRepository<DepositCategory, Long> {
    boolean existsById(Long id);

}
