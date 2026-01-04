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
    @PostMapping("/add")
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody ExpenseDto dto){
        try{
            Expense createdExpense = expenseService.createExpense(dto);
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
    @GetMapping("/view")
    public ResponseEntity<List<Expense>> getAllExpense(){
        try{
            return ResponseEntity.ok(expenseService.getAllExpense());
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View expense by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        try{
            return ResponseEntity.of(expenseService.getExpenseById(id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update expense
    @PutMapping("/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseUpdateDto dto){

        try{
            Expense updatedExpense = expenseService.updateExpense(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedExpense);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Expense
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id){
        try{
            Expense deletedExpense = expenseService.deleteExpense(id);

            if(deletedExpense != null){
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
