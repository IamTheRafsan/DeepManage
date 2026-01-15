package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.DepositDto;
import com.digitalrangersbd.DeepManage.Dto.DepositUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Deposit;
import com.digitalrangersbd.DeepManage.Service.DepositService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    //Create new deposit
    @PostMapping("/add")
    public ResponseEntity<Deposit> createDeposit(@Valid @RequestBody DepositDto dto){
        try{
            Deposit createdDeposit = depositService.createDeposit(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDeposit);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //View all deposit
    @GetMapping("/view")
    public ResponseEntity<List<Deposit>> getAllDeposit(){
        try{
            return ResponseEntity.ok(depositService.getAllDeposit());
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View deposit by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable Long id){
        try{
            return ResponseEntity.of(depositService.getDepositById(id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update deposit
    @PutMapping("/update/{id}")
    public ResponseEntity<Deposit> updateDeposit(@PathVariable Long id, @Valid @RequestBody DepositUpdateDto dto){

        try{
            Deposit updatedDeposit = depositService.updateDeposit(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedDeposit);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Deposit
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long id){
        try{
            Deposit deletedDeposit = depositService.deleteDeposit(id);

            if(deletedDeposit != null){
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
