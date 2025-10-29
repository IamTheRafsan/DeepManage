package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.ExpenseCategoryUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.ExpenseDto;
import com.digitalrangersbd.DeepManage.Dto.ExpenseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Expense;
import com.digitalrangersbd.DeepManage.Entity.ExpenseCategory;
import com.digitalrangersbd.DeepManage.Service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    //Create new expense
    @PostMapping("/add/{roleId}")
    public ResponseEntity<Expense> createExpense(@PathVariable String roleId, @Valid @RequestBody ExpenseDto dto){
        try{
            Expense createdExpense = expenseService.createExpense(roleId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //View all expense
    @GetMapping("/view/{roleId}")
    public ResponseEntity<List<Expense>> getAllExpense(@PathVariable String roleId){
        try{
            return ResponseEntity.ok(expenseService.getAllExpense(roleId));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View expense by id
    @GetMapping("/view/{roleId}/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable String roleId, @PathVariable Long id){
        try{
            return ResponseEntity.of(expenseService.getExpenseById(roleId, id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update expense
    @PutMapping("/update/{roleId}/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String roleId, @PathVariable Long id, @Valid @RequestBody ExpenseUpdateDto dto){

        try{
            Expense updatedExpense = expenseService.updateExpense(roleId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedExpense);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Expense
    @DeleteMapping("/delete/{roleId}/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String roleId, @PathVariable Long id){
        try{
            boolean deletedExpense = expenseService.deleteExpense(roleId, id);

            if(deletedExpense){
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
