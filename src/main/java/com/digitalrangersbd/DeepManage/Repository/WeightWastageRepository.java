package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.WeightWastage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightWastageRepository extends JpaRepository<WeightWastage, Long> {

    boolean existsById(Long id);

    List<WeightWastage> findByDeletedFalse();

}
