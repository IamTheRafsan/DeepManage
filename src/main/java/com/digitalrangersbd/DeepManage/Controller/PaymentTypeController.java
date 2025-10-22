package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.PaymentTypeDto;
import com.digitalrangersbd.DeepManage.Entity.PaymentType;
import com.digitalrangersbd.DeepManage.Service.PaymentTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class  PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    //Create new payment
    @PostMapping("/add/{roleId}")
    public ResponseEntity<PaymentType> createPaymentType(@PathVariable String roleId, @Valid @RequestBody PaymentTypeDto dto){

        try {
            PaymentType createdPaymentType = paymentTypeService.createPaymentType(roleId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPaymentType);
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

    //View Payment Type
    @GetMapping("/view/{roleId}")
    public ResponseEntity<List<PaymentType>> getAllPaymentType(@PathVariable String roleId){

        try{
            return ResponseEntity.ok(paymentTypeService.getAllPaymentType(roleId));
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

    //delete Payment Type
    @DeleteMapping("/delete/{roleId}/{id}")
    public ResponseEntity<Boolean> deletePaymentType(@PathVariable String roleId, @PathVariable Long id){
        try{
            Boolean deletedPaymentType = paymentTypeService.deletePayment(roleId, id);
            if(deletedPaymentType){
                return ResponseEntity.noContent().build();
            }
            else{
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
