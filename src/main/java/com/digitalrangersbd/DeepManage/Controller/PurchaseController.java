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
    @PostMapping("/add/{userId}")
    public ResponseEntity<Purchase> createPurchase(@PathVariable String userId,@Valid @RequestBody PurchaseDto dto){
        try{
            Purchase purchase = purchaseService.createPurchase(userId, dto);
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
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<Purchase>> getAllPurchase(@PathVariable String userId){
        try{
            return ResponseEntity.ok(purchaseService.getAllPurchase(userId));
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
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable String userId, @PathVariable Long id){
        try{
            return ResponseEntity.of(purchaseService.getPurchaseById(userId, id));
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
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable String userId, @PathVariable Long id, @Valid @RequestBody PurchaseUpdateDto dto){
        try{
            Purchase updatedPurchase = purchaseService.updatePurchase(userId,id,dto);
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
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Boolean> deletePurchase(@PathVariable String userId, @PathVariable Long id){
        try{
            Boolean deletedPurchase = purchaseService.deletePurchase(userId, id);

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
