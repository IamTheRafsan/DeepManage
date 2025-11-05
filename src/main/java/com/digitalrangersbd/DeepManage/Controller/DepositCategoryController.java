package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.DepositCategoryDto;
import com.digitalrangersbd.DeepManage.Dto.DepositCategoryUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.DepositCategory;
import com.digitalrangersbd.DeepManage.Service.DepositCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/deposit_category")
public class DepositCategoryController {
    public final DepositCategoryService dipositCategoryService;

    public DepositCategoryController(DepositCategoryService dipositCategoryService) {
        this.dipositCategoryService = dipositCategoryService;
    }

    //Create new diposit category
    @PostMapping("/add")
    public ResponseEntity<DepositCategory> createDepositCategory(@Valid @RequestBody DepositCategoryDto dto){

        try{
            DepositCategory dipositCategory = dipositCategoryService.createDepositCategory(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dipositCategory);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //View all diposit category
    @GetMapping("/view")
    public ResponseEntity<List<DepositCategory>> getDepositCategory(){

        try{
            return ResponseEntity.ok(dipositCategoryService.getDepositCategory());
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View Deposit category by id
    @GetMapping("/view/{id}")
    public ResponseEntity<DepositCategory> getDepositCategoryById(@PathVariable Long id){

        try{
            return ResponseEntity.of(dipositCategoryService.getDepositCategoryById(id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update diposit category
    @PutMapping("/update/{id}")
    public ResponseEntity<DepositCategory> updateDepositCategory(@PathVariable Long id, @Valid @RequestBody DepositCategoryUpdateDto dto){

        try{
            DepositCategory dipositCategory = dipositCategoryService.updateDepositCategory(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dipositCategory);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Deposit Category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepositCategory(@PathVariable Long id){
        try{
            boolean deletedDepositCategory = dipositCategoryService.deleteDepositCategory(id);

            if(deletedDepositCategory){
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