package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.WeightLess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightLessRepository extends JpaRepository<WeightLess, Long> {

    boolean existsById(Long id);
}
