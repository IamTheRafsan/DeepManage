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
    @PostMapping("/add")
    public ResponseEntity<Sale> createSale(@Valid @RequestBody SaleDto dto){
        try{
            Sale sale = saleService.createSale(dto);
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
    @GetMapping("/view")
    public ResponseEntity<List<Sale>> getAllSale(){
        try {
            return ResponseEntity.ok(saleService.getAllSale());
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
    @GetMapping("/view/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id){
        try{
            return ResponseEntity.of(saleService.getSaleById(id));
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @Valid @RequestBody SaleUpdateDto dto){
        try{
            Sale updatedSale = saleService.updateSale(id, dto);
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteSale(@PathVariable Long id){
        try{
            Sale deletedSale = saleService.deleteSale(id);

            if(deletedSale != null){
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
