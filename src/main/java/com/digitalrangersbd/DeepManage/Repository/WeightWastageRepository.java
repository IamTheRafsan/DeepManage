package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.WeightWastage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightWastageRepository extends JpaRepository<WeightWastage, Long> {

    boolean existsById(Long id);

}
