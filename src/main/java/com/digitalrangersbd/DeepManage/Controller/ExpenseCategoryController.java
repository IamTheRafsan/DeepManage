package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.BrandDto;
import com.digitalrangersbd.DeepManage.Dto.BrandUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.ExpenseCategoryDto;
import com.digitalrangersbd.DeepManage.Dto.ExpenseCategoryUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Brand;
import com.digitalrangersbd.DeepManage.Entity.ExpenseCategory;
import com.digitalrangersbd.DeepManage.Service.ExpenseCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/expense_category")
public class ExpenseCategoryController {
    public final ExpenseCategoryService expenseCategoryService;

    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    //Create new expense category
    @PostMapping("/add/{roleId}")
    public ResponseEntity<ExpenseCategory> createExpenseCategory(@PathVariable String roleId, @Valid @RequestBody ExpenseCategoryDto dto){

        try{
            ExpenseCategory expenseCategory = expenseCategoryService.createExpenseCategory(roleId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(expenseCategory);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //View all expense category
    @GetMapping("/view/{roleId}")
    public ResponseEntity<List<ExpenseCategory>> getExpenseCategory(@PathVariable String roleId){

        try{
            return ResponseEntity.ok(expenseCategoryService.getExpenseCategory(roleId));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View Expense category by id
    @GetMapping("/view/{roleId}/{id}")
    public ResponseEntity<ExpenseCategory> getExpenseCategoryById(@PathVariable String roleId, @PathVariable Long id){

        try{
            return ResponseEntity.of(expenseCategoryService.getExpenseCategoryById(roleId,id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update expense category
    @PutMapping("/update/{roleId}/{id}")
    public ResponseEntity<ExpenseCategory> updateExpenseCategory(@PathVariable String roleId, @PathVariable Long id, @Valid @RequestBody ExpenseCategoryUpdateDto dto){

        try{
            ExpenseCategory expenseCategory = expenseCategoryService.updateExpenseCategory(roleId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(expenseCategory);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Expense Category
    @DeleteMapping("/delete/{roleId}/{id}")
    public ResponseEntity<Void> deleteExpenseCategory(@PathVariable String roleId, @PathVariable Long id){
        try{
            boolean deletedExpenseCategory = expenseCategoryService.deleteExpenseCategory(roleId, id);

            if(deletedExpenseCategory){
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
