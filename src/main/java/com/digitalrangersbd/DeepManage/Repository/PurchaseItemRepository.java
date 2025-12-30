package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import com.digitalrangersbd.DeepManage.Entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

    Optional<PurchaseItem> findByPurchaseAndProduct(Purchase purchase, Product product);
}