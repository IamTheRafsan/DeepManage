package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.PurchaseDto;
import com.digitalrangersbd.DeepManage.Dto.PurchaseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import com.digitalrangersbd.DeepManage.Service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    //create new purchase
    @PostMapping("/add")
    public ResponseEntity<Purchase> createPurchase(@Valid @RequestBody PurchaseDto dto){
        try{
            Purchase purchase = purchaseService.createPurchase(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(purchase);
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

    //get all purchase
    @GetMapping("/view")
    public ResponseEntity<List<Purchase>> getAllPurchase(){
        try{
            return ResponseEntity.ok(purchaseService.getAllPurchase());
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

    //Get Purchase by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id){
        try{
            return ResponseEntity.of(purchaseService.getPurchaseById(id));
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

    //Update Purchase
    @PutMapping("/update/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @Valid @RequestBody PurchaseUpdateDto dto){
        try{
            Purchase updatedPurchase = purchaseService.updatePurchase(id,dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedPurchase);
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

    //Delete Purchase
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePurchase(@PathVariable Long id){
        try{
            Boolean deletedPurchase = purchaseService.deletePurchase(id);

            if(deletedPurchase){
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
