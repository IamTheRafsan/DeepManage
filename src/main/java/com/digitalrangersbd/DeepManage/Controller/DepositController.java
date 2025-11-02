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
    @PostMapping("/add/{roleId}")
    public ResponseEntity<Deposit> createDeposit(@PathVariable String roleId, @Valid @RequestBody DepositDto dto){
        try{
            Deposit createdDeposit = depositService.createDeposit(roleId, dto);
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
    @GetMapping("/view/{roleId}")
    public ResponseEntity<List<Deposit>> getAllDeposit(@PathVariable String roleId){
        try{
            return ResponseEntity.ok(depositService.getAllDeposit(roleId));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View deposit by id
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable String roleId, @PathVariable Long id){
        try{
            return ResponseEntity.of(depositService.getDepositById(roleId, id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update deposit
    @PutMapping("/update/{roleId}/{id}")
    public ResponseEntity<Deposit> updateDeposit(@PathVariable String roleId, @PathVariable Long id, @Valid @RequestBody DepositUpdateDto dto){

        try{
            Deposit updatedDeposit = depositService.updateDeposit(roleId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedDeposit);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Deposit
    @DeleteMapping("/delete/{roleId}/{id}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable String roleId, @PathVariable Long id){
        try{
            boolean deletedDeposit = depositService.deleteDeposit(roleId, id);

            if(deletedDeposit){
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
