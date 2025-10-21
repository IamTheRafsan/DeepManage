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
    @PostMapping("/add/{roleId}")
    public ResponseEntity<Purchase> createPurchase(@PathVariable String roleId,@Valid @RequestBody PurchaseDto dto){
        try{
            Purchase purchase = purchaseService.createPurchase(roleId, dto);
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
    @GetMapping("/view/{roleId}")
    public ResponseEntity<List<Purchase>> getAllPurchase(@PathVariable String roleId){
        try{
            return ResponseEntity.ok(purchaseService.getAllPurchase(roleId));
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
    @GetMapping("/view/{roleId}/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable String roleId, @PathVariable Long id){
        try{
            return ResponseEntity.of(purchaseService.getPurchaseById(roleId, id));
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
    @PutMapping("/update/{roleId}/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable String roleId, @PathVariable Long id, @Valid @RequestBody PurchaseUpdateDto dto){
        try{
            Purchase updatedPurchase = purchaseService.updatePurchase(roleId,id,dto);
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
    @DeleteMapping("/delete/{roleId}/{id}")
    public ResponseEntity<Boolean> deletePurchase(@PathVariable String roleId, @PathVariable Long id){
        try{
            Boolean deletedPurchase = purchaseService.deletePurchase(roleId, id);

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
