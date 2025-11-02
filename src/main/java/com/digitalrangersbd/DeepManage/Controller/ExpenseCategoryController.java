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
    @PostMapping("/add/{userId}")
    public ResponseEntity<ExpenseCategory> createExpenseCategory(@PathVariable String userId, @Valid @RequestBody ExpenseCategoryDto dto){

        try{
            ExpenseCategory expenseCategory = expenseCategoryService.createExpenseCategory(userId, dto);
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
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<ExpenseCategory>> getExpenseCategory(@PathVariable String userId){

        try{
            return ResponseEntity.ok(expenseCategoryService.getExpenseCategory(userId));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View Expense category by id
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<ExpenseCategory> getExpenseCategoryById(@PathVariable String userId, @PathVariable Long id){

        try{
            return ResponseEntity.of(expenseCategoryService.getExpenseCategoryById(userId,id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update expense category
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<ExpenseCategory> updateExpenseCategory(@PathVariable String userId, @PathVariable Long id, @Valid @RequestBody ExpenseCategoryUpdateDto dto){

        try{
            ExpenseCategory expenseCategory = expenseCategoryService.updateExpenseCategory(userId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(expenseCategory);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Expense Category
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Void> deleteExpenseCategory(@PathVariable String userId, @PathVariable Long id){
        try{
            boolean deletedExpenseCategory = expenseCategoryService.deleteExpenseCategory(userId, id);

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
