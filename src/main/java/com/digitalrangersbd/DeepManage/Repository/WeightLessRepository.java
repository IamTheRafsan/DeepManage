package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.WeightLess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightLessRepository extends JpaRepository<WeightLess, Long> {

    boolean existsById(Long id);

    List<WeightLess> findByDeletedFalse();

}
