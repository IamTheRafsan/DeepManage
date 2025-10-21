package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.SaleDto;
import com.digitalrangersbd.DeepManage.Dto.SaleUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Sale;
import com.digitalrangersbd.DeepManage.Service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    //Create new Sale
    @PostMapping("/add/{roleId}")
    public ResponseEntity<Sale> createSale(@PathVariable String roleId, @Valid @RequestBody SaleDto dto){
        try{
            Sale sale = saleService.createSale(roleId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(sale);
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

    //View all Sale
    @GetMapping("/view/{roleId}")
    public ResponseEntity<List<Sale>> getAllSale(@PathVariable String roleId){
        try {
            return ResponseEntity.ok(saleService.getAllSale(roleId));
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

    //View sale by id
    @GetMapping("/view/{roleId}/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable String roleId,@PathVariable Long id){
        try{
            return ResponseEntity.of(saleService.getSaleById(roleId, id));
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

    //Update Sale
    @PutMapping("/update/{roleId}/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable String roleId, @PathVariable Long id, @Valid @RequestBody SaleUpdateDto dto){
        try{
            Sale updatedSale = saleService.updateSale(roleId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedSale);
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

    //Delete Sale
    @DeleteMapping("/delete/{roleId}/{id}")
    public ResponseEntity<Boolean> deleteSale(@PathVariable String roleId, @PathVariable Long id){
        try{
            Boolean deletedSale = saleService.deleteSale(roleId, id);

            if(deletedSale){
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
