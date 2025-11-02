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
    @PostMapping("/add/{userId}")
    public ResponseEntity<Sale> createSale(@PathVariable String userId, @Valid @RequestBody SaleDto dto){
        try{
            Sale sale = saleService.createSale(userId, dto);
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
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<Sale>> getAllSale(@PathVariable String userId){
        try {
            return ResponseEntity.ok(saleService.getAllSale(userId));
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
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable String userId,@PathVariable Long id){
        try{
            return ResponseEntity.of(saleService.getSaleById(userId, id));
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
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable String userId, @PathVariable Long id, @Valid @RequestBody SaleUpdateDto dto){
        try{
            Sale updatedSale = saleService.updateSale(userId, id, dto);
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
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Boolean> deleteSale(@PathVariable String userId, @PathVariable Long id){
        try{
            Boolean deletedSale = saleService.deleteSale(userId, id);

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
