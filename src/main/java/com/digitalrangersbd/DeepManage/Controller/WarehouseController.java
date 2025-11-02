package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.WarehouseDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.Service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    //Create new Warehosue
    @PostMapping("/add/{userId}")
    public ResponseEntity<Warehouse> createWarehosue(@PathVariable String userId, @Valid @RequestBody WarehouseDto dto){

        try {
            Warehouse createdWarehouse = warehouseService.createWarehouse(userId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWarehouse);
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

    //View warehouse
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<Warehouse>> getWarehouse(@PathVariable String userId){

        try{
            return ResponseEntity.ok(warehouseService.getWarehouse(userId));
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

    //View warehouse by id
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable String userId, @PathVariable Long id){

        try{
            return ResponseEntity.of(warehouseService.getWarehouseById(userId, id));
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

    //Update warehouse date
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable String userId, @PathVariable Long id, @Valid @RequestBody WarehouseUpdateDto dto){
        try{
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(userId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedWarehouse);
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

    //Delete Warehouse
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable String userId, @PathVariable Long id){
        try{
            boolean deletedWarehouse = warehouseService.deleteWarehouse(userId, id);

            if(deletedWarehouse){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
