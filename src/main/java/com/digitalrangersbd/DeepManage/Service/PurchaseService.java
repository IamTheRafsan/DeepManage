package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.PurchaseDto;
import com.digitalrangersbd.DeepManage.Dto.PurchaseItemDto;
import com.digitalrangersbd.DeepManage.Dto.PurchaseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import com.digitalrangersbd.DeepManage.Entity.PurchaseItem;
import com.digitalrangersbd.DeepManage.Repository.ProductRepository;
import com.digitalrangersbd.DeepManage.Repository.PurchaseRepository;
import com.digitalrangersbd.DeepManage.Repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final RoleAuthorization roleAuthorization;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public PurchaseService(ProductRepository productRepository, PurchaseRepository purchaseRepository, RoleAuthorization roleAuthorization, ProductRepository productRepository1, WarehouseRepository warehouseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.roleAuthorization = roleAuthorization;
        this.productRepository = productRepository1;
        this.warehouseRepository = warehouseRepository;
    }

    //Create new Purchase
    public Purchase createPurchase(String roleId, PurchaseDto dto){

        if (!roleAuthorization.hasCreatePurchasePermission(roleId)) {
            throw new SecurityException("User does not have the permission to create purchase");
        }
        else{
            Purchase purchase = new Purchase();

            purchase.setSupplier(dto.getSupplier());
            purchase.setPurchasedBy(dto.getPurchasedBy());
            purchase.setReference(dto.getReference());
            purchase.setPurchaseDate(dto.getPurchaseDate());
            purchase.setWarehouse(dto.getWarehouse());

            purchase.setCreated_date(LocalDate.now());
            purchase.setCreated_time(LocalTime.now());
            purchase.setUpdated_date(LocalDate.now());
            purchase.setUpdated_time(LocalTime.now());

            List<PurchaseItem> purchaseItems = dto.getPurchaseItem();
            if(purchaseItems != null && !purchaseItems.isEmpty()){
                for(PurchaseItem item: purchaseItems){
                    item.setPurchase(purchase);

                    purchase.getPurchaseItem().add(item);
                }
            }

            return purchaseRepository.save(purchase);
        }
    }

    //Get all Purchase
    public List<Purchase> getAllPurchase(String roleId){
        if (!roleAuthorization.hasViewPurchasePermission(roleId)) {
            throw new SecurityException("User does not have the permission to view purchase");
        }
        else{
            return purchaseRepository.findAll();
        }
    }

    //Get purchase by id
    public Optional<Purchase> getPurchaseById(String roleId, Long id){
        if (!roleAuthorization.hasViewPurchasePermission(roleId)) {
            throw new SecurityException("User does not have the permission to view purchase");
        }
        else {
            return purchaseRepository.findById(id);
        }
    }

    //Update Purchase
    public Purchase updatePurchase(String roleId, Long id, PurchaseUpdateDto dto){

        if (!roleAuthorization.hasUpdatePurchasePermission(roleId)) {
            throw new SecurityException("User does not have the permission to update purchase");
        }
        else{
            return purchaseRepository.findById(id)
                    .map(purchase -> {
                        if(dto.getSupplier() != null) purchase.setSupplier(dto.getSupplier());
                        if(dto.getPurchasedBy() != null) purchase.setPurchasedBy(dto.getPurchasedBy());
                        if(dto.getReference() != null) purchase.setReference(dto.getReference());
                        if(dto.getWarehouse() != null) purchase.setWarehouse(dto.getWarehouse());
                        if(dto.getPurchaseDate() != null) purchase.setPurchaseDate(dto.getPurchaseDate());

                        purchase.setUpdated_date(LocalDate.now());
                        purchase.setUpdated_time(LocalTime.now());

                        if(dto.getPurchaseItem() != null) {
                            purchase.getPurchaseItem().clear();

                            for(PurchaseItem item : dto.getPurchaseItem()) {
                                Product product = productRepository.findById(item.getProduct().getId())
                                        .orElseThrow(() -> new RuntimeException("Product with ID " + item.getProduct().getId() + " not found."));

                                item.setPurchase(purchase);
                                item.setProduct(product);
                                purchase.getPurchaseItem().add(item);
                            }
                        }

                        return purchaseRepository.save(purchase);
                    })
                    .orElseThrow(() -> new RuntimeException("Purchase id not found"+id ));
        }
    }

    //Delete Purchase
    public Boolean deletePurchase(String roleId, Long id){
        if(!roleAuthorization.hasDeletePurchasePermission(roleId)){
            throw new SecurityException("User does not have the permission to delete purchase");
        }
        else {
            if(purchaseRepository.existsById(id)){
                purchaseRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
