package com.digitalrangersbd.DeepManage.Service;


import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.StockAdjustmentDto;
import com.digitalrangersbd.DeepManage.Dto.StockAdjustmentUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import com.digitalrangersbd.DeepManage.Entity.StockAdjustItem;
import com.digitalrangersbd.DeepManage.Entity.StockAdjustment;
import com.digitalrangersbd.DeepManage.Repository.ProductRepository;
import com.digitalrangersbd.DeepManage.Repository.StockAdjustItemRepository;
import com.digitalrangersbd.DeepManage.Repository.StockAdjustmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockAdjustmentService {

    private final StockAdjustmentRepository stockAdjustmentRepository;
    private final RoleAuthorization roleAuthorization;
    private final ProductRepository productRepository;

    public StockAdjustmentService(StockAdjustmentRepository stockAdjustmentRepository, RoleAuthorization roleAuthorization, ProductRepository productRepository) {
        this.stockAdjustmentRepository = stockAdjustmentRepository;
        this.roleAuthorization = roleAuthorization;
        this.productRepository = productRepository;
    }

    //Create new stock adjustment
    public StockAdjustment createStockAdjustment(String roleId, StockAdjustmentDto dto)
    {
        if(!roleAuthorization.hasCreateStockAdjustmentPermission(roleId)){
            throw new SecurityException("User does not have the permission to adjust stock");
        }
        else{
            StockAdjustment stockAdjustment = new StockAdjustment();
            stockAdjustment.setReference(dto.getReference());
            stockAdjustment.setWarehouse(dto.getWarehouse());
            stockAdjustment.setOutlet(dto.getOutlet());
            stockAdjustment.setAdjustedBy(dto.getAdjustedBy());
            stockAdjustment.setAdjustmentType(dto.getAdjustmentType());
            stockAdjustment.setReason(dto.getReason());

            stockAdjustment.setCreatedDate(LocalDate.now());
            stockAdjustment.setCreatedTime(LocalTime.now());
            stockAdjustment.setUpdatedDate(LocalDate.now());
            stockAdjustment.setUpdatedTime(LocalTime.now());

            List<StockAdjustItem> stockAdjustItems =  dto.getStockItems();
            if(stockAdjustItems != null && !stockAdjustItems.isEmpty()){

                for(StockAdjustItem item: stockAdjustItems){

                    item.setStockAdjustment(stockAdjustment);
                    stockAdjustment.getStockItems().add(item);
                }

            }
            return stockAdjustmentRepository.save(stockAdjustment);
        }
    }

    //Get all Stock adjustment
    public List<StockAdjustment> getAllStockAdjustment(String roleId){
        if (!roleAuthorization.hasViewStockAdjustmentPermission(roleId)) {
            throw new SecurityException("User does not have the permission to view Stock adjustment");
        }
        else{
            return stockAdjustmentRepository.findAll();
        }
    }

    //Get stock adjustment by id
    public Optional<StockAdjustment> getStockAdjustmentById(String roleId, Long id){
        if (!roleAuthorization.hasViewStockAdjustmentPermission(roleId)) {
            throw new SecurityException("User does not have the permission to view stock adjustment");
        }
        else {
            return stockAdjustmentRepository.findById(id);
        }
    }

    //update stock adjustment
    public StockAdjustment updateStockAdjustment(String roleId,Long id,  StockAdjustmentUpdateDto dto)
    {
        if (!roleAuthorization.hasUpdateStockAdjustmentPermission(roleId)) {
            throw new SecurityException("User does not have the permission to update stock adjustment");
        }
        else {
            return stockAdjustmentRepository.findById(id)
                    .map(stockAdjustment -> {
                        if(dto.getWarehouse()!= null) stockAdjustment.setWarehouse(dto.getWarehouse());
                        if(dto.getOutlet() != null) stockAdjustment.setOutlet(dto.getOutlet());
                        if(dto.getAdjustedBy() != null) stockAdjustment.setAdjustedBy(dto.getAdjustedBy());
                        if(dto.getAdjustmentType() != null) stockAdjustment.setAdjustmentType(dto.getAdjustmentType());
                        if(dto.getReason() != null) stockAdjustment.setReason(dto.getReason());

                        stockAdjustment.setUpdatedDate(LocalDate.now());
                        stockAdjustment.setUpdatedTime(LocalTime.now());

                        if(dto.getStockItems() != null){
                            stockAdjustment.getStockItems().clear();

                            for(StockAdjustItem items: dto.getStockItems()){
                                Product product = productRepository.findById(items.getProduct().getId())
                                        .orElseThrow(() -> new RuntimeException("Product with ID " + items.getProduct().getId() + " not found."));

                                items.setStockAdjustment(stockAdjustment);
                                items.setProduct(product);
                                stockAdjustment.getStockItems().add(items);
                            }
                        }

                        return stockAdjustmentRepository.save(stockAdjustment);
                    })
                    .orElseThrow(() -> new RuntimeException("Stock adjustment id not found"+id ));

        }
    }

    //Delete Stock Adjustment
    public Boolean deleteStockAdjustment(String roleId, Long id){
        if(!roleAuthorization.hasDeleteStockAdjustmentPermission(roleId)){
            throw new SecurityException("User does not have the permission to delete Stock Adjustment");
        }
        else {
            if(stockAdjustmentRepository.existsById(id)){
                stockAdjustmentRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
