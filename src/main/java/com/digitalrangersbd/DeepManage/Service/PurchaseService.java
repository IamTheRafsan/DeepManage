package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.PurchaseDto;
import com.digitalrangersbd.DeepManage.Dto.PurchaseItemDto;
import com.digitalrangersbd.DeepManage.Dto.PurchaseResponseDto;
import com.digitalrangersbd.DeepManage.Dto.PurchaseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.*;
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
    private final UserRepository userRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    public PurchaseService(ProductRepository productRepository, PurchaseRepository purchaseRepository, RoleAuthorization roleAuthorization, ProductRepository productRepository1, WarehouseRepository warehouseRepository, UserRepository userRepository, PaymentTypeRepository paymentTypeRepository) {
        this.purchaseRepository = purchaseRepository;
        this.roleAuthorization = roleAuthorization;
        this.productRepository = productRepository1;
        this.warehouseRepository = warehouseRepository;
        this.userRepository = userRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    //Create new Purchase
    public Purchase createPurchase(PurchaseDto dto){

        String userId = UserContext.getUserId();

        if (!roleAuthorization.hasCreatePurchasePermission(userId)) {
            throw new SecurityException("User does not have the permission to create purchase");
        }
        else{
            Purchase purchase = new Purchase();

            purchase.setReference(dto.getReference());
            purchase.setPurchaseDate(dto.getPurchaseDate());

            if(dto.getSupplier() != null){
                User supplier = userRepository.findById(dto.getSupplier())
                        .orElseThrow(() -> new RuntimeException("Supplier not found"));
                purchase.setSupplier(supplier);
            }

            if(dto.getPurchasedBy() != null){
                User purchasedBy = userRepository.findById(dto.getPurchasedBy())
                        .orElseThrow(() -> new RuntimeException("Purchased By id not found"));
                purchase.setPurchasedBy(purchasedBy);
            }

            if(dto.getWarehouse() != null){
                Warehouse warehouse = warehouseRepository.findById(dto.getWarehouse())
                        .orElseThrow(() -> new RuntimeException("Warehouse not found"));
                purchase.setWarehouse(warehouse);
            }

            if(dto.getPaymentType() != null){
                PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType())
                        .orElseThrow(() -> new RuntimeException("Payment type not found"));
                purchase.setPaymentType(paymentType);
            }

            purchase.setCreated_date(LocalDate.now());
            purchase.setCreated_time(LocalTime.now());
            purchase.setUpdated_date(LocalDate.now());
            purchase.setUpdated_time(LocalTime.now());
            purchase.setCreated_by_id(userId);

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
    public List<Purchase> getAllPurchase(){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasViewPurchasePermission(userId)) {
            throw new SecurityException("User does not have the permission to view purchase");
        }
        else{
            return purchaseRepository.findByDeletedFalse();
        }
    }

    //Get purchase by id
    public Optional<Purchase> getPurchaseById(Long id){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasViewPurchasePermission(userId)) {
            throw new SecurityException("User does not have the permission to view purchase");
        }
        else {
            return purchaseRepository.findById(id);
        }
    }

    //Update Purchase
    public Purchase updatePurchase(Long id, PurchaseUpdateDto dto){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasUpdatePurchasePermission(userId)) {
            throw new SecurityException("User does not have the permission to update purchase");
        }
        else{
            return purchaseRepository.findById(id)
                    .map(purchase -> {
                        if(dto.getReference() != null) purchase.setReference(dto.getReference());
                        if(dto.getPurchaseDate() != null) purchase.setPurchaseDate(dto.getPurchaseDate());

                        purchase.setUpdated_date(LocalDate.now());
                        purchase.setUpdated_time(LocalTime.now());
                        purchase.setUpdated_by_id(userId);

                        if(dto.getSupplier() != null){
                            User supplier = userRepository.findById(dto.getSupplier())
                                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
                            purchase.setSupplier(supplier);
                        }

                        if(dto.getPurchasedBy() != null){
                            User purchasedBy = userRepository.findById(dto.getPurchasedBy())
                                    .orElseThrow(() -> new RuntimeException("Purchased By id not found"));
                            purchase.setPurchasedBy(purchasedBy);
                        }

                        if(dto.getWarehouse() != null){
                            Warehouse warehouse = warehouseRepository.findById(dto.getWarehouse())
                                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));
                            purchase.setWarehouse(warehouse);
                        }

                        if(dto.getPaymentType() != null){
                            PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType())
                                    .orElseThrow(() -> new RuntimeException("Payment type not found"));
                            purchase.setPaymentType(paymentType);
                        }

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
    public Purchase deletePurchase(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeletePurchasePermission(userId)){
            throw new SecurityException("User does not have the permission to delete purchase");
        }
        return purchaseRepository.findById(id)
                .map(purchase -> {
                    purchase.setDeleted(true);
                    purchase.setDeletedById(userId);
                    purchase.setDeletedDate(LocalDate.now());
                    purchase.setDeletedTime(LocalTime.now());

                    return purchaseRepository.save(purchase);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }
}
