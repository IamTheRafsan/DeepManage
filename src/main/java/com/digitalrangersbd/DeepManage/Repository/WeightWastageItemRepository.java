package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.PurchaseItem;
import com.digitalrangersbd.DeepManage.Entity.WeightLessItem;
import com.digitalrangersbd.DeepManage.Entity.WeightWastage;
import com.digitalrangersbd.DeepManage.Entity.WeightWastageItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightWastageItemRepository extends JpaRepository<WeightWastageItem, Long> {

    List<WeightWastageItem> findWeightWastageById(Long weightWastageItemId);
    List<WeightWastageItem> findPurchaseItemById(Long purchaseId);
}
