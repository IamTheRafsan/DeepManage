package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.OutletUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.PaymentTypeDto;
import com.digitalrangersbd.DeepManage.Entity.Outlet;
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
    @PostMapping("/add")
    public ResponseEntity<PaymentType> createPaymentType(@Valid @RequestBody PaymentTypeDto dto){

        try {
            PaymentType createdPaymentType = paymentTypeService.createPaymentType(dto);
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
    @GetMapping("/view")
    public ResponseEntity<List<PaymentType>> getAllPaymentType(){

        try{
            return ResponseEntity.ok(paymentTypeService.getAllPaymentType());
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

    //View Payment Type by id
    @GetMapping("/view/{id}")
    public ResponseEntity<PaymentType> getPaymentTypeById(@PathVariable Long id){

        try{
            return ResponseEntity.of(paymentTypeService.getPaymentTypeById(id));
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

    //Update Payment Type
    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentType> updatePaymentType(@PathVariable Long id, @Valid @RequestBody PaymentTypeDto dto){
        try{
            PaymentType paymentType = paymentTypeService.updatePaymentType(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentType);
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

    //delete Payment Type
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePaymentType(@PathVariable Long id){
        try{
            Boolean deletedPaymentType = paymentTypeService.deletePayment(id);
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
