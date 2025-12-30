package com.digitalrangersbd.DeepManage.Service;


import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.StockAdjustmentDto;
import com.digitalrangersbd.DeepManage.Dto.StockAdjustmentUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StockAdjustmentService {

    private final StockAdjustmentRepository stockAdjustmentRepository;
    private final RoleAuthorization roleAuthorization;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final OutletRepository outletRepository;
    private final UserRepository userRepository;


    public StockAdjustmentService(StockAdjustmentRepository stockAdjustmentRepository, RoleAuthorization roleAuthorization, ProductRepository productRepository, WarehouseRepository warehouseRepository, OutletRepository outletRepository, UserRepository userRepository) {
        this.stockAdjustmentRepository = stockAdjustmentRepository;
        this.roleAuthorization = roleAuthorization;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.outletRepository = outletRepository;
        this.userRepository = userRepository;
    }

    //Create new stock adjustment
    public StockAdjustment createStockAdjustment(StockAdjustmentDto dto)
    {
        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateStockAdjustmentPermission(userId)){
            throw new SecurityException("User does not have the permission to adjust stock");
        }
        else{
            StockAdjustment stockAdjustment = new StockAdjustment();
            stockAdjustment.setReference(dto.getReference());
            stockAdjustment.setAdjustmentType(dto.getAdjustmentType());
            stockAdjustment.setReason(dto.getReason());

            //Getting the warehouse
            if(dto.getWarehouse() != null){
                Warehouse warehouse = warehouseRepository.findById(dto.getWarehouse())
                        .orElseThrow(() -> new RuntimeException("Warehouse not found"));
                stockAdjustment.setWarehouse(warehouse);
            }

            //Getting the outlet
            if(dto.getOutlet() != null){
                Outlet outlet = outletRepository.findById(dto.getOutlet())
                        .orElseThrow(() -> new RuntimeException("Outlet not found"));
                stockAdjustment.setOutlet(outlet);
            }

            //Getting the user
            if(dto.getAdjustedBy() != null){
                User user = userRepository.findById(dto.getAdjustedBy())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                stockAdjustment.setAdjustedBy(user);
            }


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
    public List<StockAdjustment> getAllStockAdjustment(){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasViewStockAdjustmentPermission(userId)) {
            throw new SecurityException("User does not have the permission to view Stock adjustment");
        }
        else{
            return stockAdjustmentRepository.findAll();
        }
    }

    //Get stock adjustment by id
    public Optional<StockAdjustment> getStockAdjustmentById(Long id){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasViewStockAdjustmentPermission(userId)) {
            throw new SecurityException("User does not have the permission to view stock adjustment");
        }
        else {
            return stockAdjustmentRepository.findById(id);
        }
    }

    //update stock adjustment
    public StockAdjustment updateStockAdjustment(Long id,  StockAdjustmentUpdateDto dto)
    {
        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasUpdateStockAdjustmentPermission(userId)) {
            throw new SecurityException("User does not have the permission to update stock adjustment");
        }
        else {
            return stockAdjustmentRepository.findById(id)
                    .map(stockAdjustment -> {
                        if(dto.getAdjustmentType() != null) stockAdjustment.setAdjustmentType(dto.getAdjustmentType());
                        if(dto.getReason() != null) stockAdjustment.setReason(dto.getReason());

                        //Getting the warehouse
                        if(dto.getWarehouse() != null){
                            Warehouse warehouse = warehouseRepository.findById(dto.getWarehouse())
                                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));
                            stockAdjustment.setWarehouse(warehouse);
                        }

                        //Getting the outlet
                        if(dto.getOutlet() != null){
                            Outlet outlet = outletRepository.findById(dto.getOutlet())
                                    .orElseThrow(() -> new RuntimeException("Outlet not found"));
                            stockAdjustment.setOutlet(outlet);
                        }

                        //Getting the user
                        if(dto.getAdjustedBy() != null){
                            User user = userRepository.findById(dto.getAdjustedBy())
                                    .orElseThrow(() -> new RuntimeException("User not found"));
                            stockAdjustment.setAdjustedBy(user);
                        }

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
    public Boolean deleteStockAdjustment(Long id){


        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteStockAdjustmentPermission(userId)){
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
