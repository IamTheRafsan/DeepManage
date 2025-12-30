package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.PurchaseDto;
import com.digitalrangersbd.DeepManage.Dto.PurchaseUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.StockAdjustmentDto;
import com.digitalrangersbd.DeepManage.Dto.StockAdjustmentUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import com.digitalrangersbd.DeepManage.Entity.StockAdjustment;
import com.digitalrangersbd.DeepManage.Service.PurchaseService;
import com.digitalrangersbd.DeepManage.Service.StockAdjustmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/stock-adjustment")
public class StockAdjustmentController {

    private final StockAdjustmentService stockAdjustmentService;

    public StockAdjustmentController(StockAdjustmentService stockAdjustmentService) {
        this.stockAdjustmentService = stockAdjustmentService;
    }


    //create new stock adjustment
    @PostMapping("/add")
    public ResponseEntity<StockAdjustment> createStockAdjustment(@Valid @RequestBody StockAdjustmentDto dto){
        try{
            StockAdjustment stockAdjustment = stockAdjustmentService.createStockAdjustment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(stockAdjustment);
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get all stock adjustment
    @GetMapping("/view")
    public ResponseEntity<List<StockAdjustment>> getAllStockAdjustment(){
        try{
            return ResponseEntity.ok(stockAdjustmentService.getAllStockAdjustment());
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.ok(Collections.emptyList());
        }

    }

    //Get Stock adjustment by id
    @GetMapping("/view/{id}")
    public ResponseEntity<StockAdjustment> getStockAdjustmentById(@PathVariable Long id){
        try{
            return ResponseEntity.of(stockAdjustmentService.getStockAdjustmentById(id));
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update Stock Adjustment
    @PutMapping("/update/{id}")
    public ResponseEntity<StockAdjustment> updateStockAdjustment(@PathVariable Long id, @Valid @RequestBody StockAdjustmentUpdateDto dto){
        try{
            StockAdjustment updatedStockAdjustment = stockAdjustmentService.updateStockAdjustment(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedStockAdjustment);
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Stock adjustment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteStockAdjustment(@PathVariable Long id){
        try{
            Boolean deleteStockAdjustment = stockAdjustmentService.deleteStockAdjustment(id);

            if(deleteStockAdjustment){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
