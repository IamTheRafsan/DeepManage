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
    @PostMapping("/add")
    public ResponseEntity<Warehouse> createWarehosue(@Valid @RequestBody WarehouseDto dto){

        try {
            Warehouse createdWarehouse = warehouseService.createWarehouse(dto);
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
    @GetMapping("/view")
    public ResponseEntity<List<Warehouse>> getWarehouse(){

        try{
            return ResponseEntity.ok(warehouseService.getWarehouse());
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
    @GetMapping("/view/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id){

        try{
            return ResponseEntity.of(warehouseService.getWarehouseById(id));
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @Valid @RequestBody WarehouseUpdateDto dto){
        try{
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, dto);
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id){
        try{
            boolean deletedWarehouse = warehouseService.deleteWarehouse(id);

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
