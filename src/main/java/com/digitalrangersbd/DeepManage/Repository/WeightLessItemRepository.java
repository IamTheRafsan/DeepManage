package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WeightLessItemRepository extends JpaRepository<WeightLessItem, Long> {
    List<WeightLessItem> findByWeightLessId(Long weightlessId);
    List<WeightLessItem> findByPurchaseItemId(Long purchaseItemId);
}
