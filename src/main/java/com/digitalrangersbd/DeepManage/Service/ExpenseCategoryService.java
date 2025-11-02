package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.ExpenseCategoryDto;
import com.digitalrangersbd.DeepManage.Dto.ExpenseCategoryUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.ExpenseCategory;
import com.digitalrangersbd.DeepManage.Repository.ExpenseCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final RoleAuthorization roleAuthorization;

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository, RoleAuthorization roleAuthorization) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.roleAuthorization = roleAuthorization;
    }

    //Create Expense Category
    public ExpenseCategory createExpenseCategory(String userId, ExpenseCategoryDto dto){
        if(!roleAuthorization.hasCreateExpenseCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to create expense category");
        }
        else{
            ExpenseCategory expenseCategory = new ExpenseCategory();
            expenseCategory.setName(dto.getName());
            expenseCategory.setCreated_date(LocalDate.now());
            expenseCategory.setCreated_time(LocalTime.now());
            expenseCategory.setUpdated_date(LocalDate.now());
            expenseCategory.setUpdated_time(LocalTime.now());

            return expenseCategoryRepository.save(expenseCategory);
        }
    }

    //View expense category
    public List<ExpenseCategory> getExpenseCategory(String userId){
        if(!roleAuthorization.hasViewExpenseCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to view expense category");
        }
        else{
            return expenseCategoryRepository.findAll();
        }
    }

    //View expense category by id
    public Optional<ExpenseCategory> getExpenseCategoryById(String userId, Long id){
        if(!roleAuthorization.hasViewExpenseCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to view expense category");
        }
        else{
            if(!expenseCategoryRepository.existsById(id)){
                throw new RuntimeException("Expense Category does not exists");
            }
            else{
                return expenseCategoryRepository.findById(id);
            }
        }
    }

    //Update Expense Category
    public ExpenseCategory updateExpenseCategory(String userId, Long id, ExpenseCategoryUpdateDto dto){
        if(!roleAuthorization.hasUpdateExpenseCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to update expense category");
        }
        else {
            return expenseCategoryRepository.findById(id)
                    .map(expenseCategory -> {
                        if(dto.getName() != null) expenseCategory.setName(dto.getName());
                        expenseCategory.setUpdated_date(LocalDate.now());
                        expenseCategory.setUpdated_time(LocalTime.now());

                        return expenseCategoryRepository.save(expenseCategory);
                    })
                    .orElseThrow(() -> new RuntimeException("Expense category does not exists."));
        }
    }

    //Delete Expense Category
    public Boolean deleteExpenseCategory(String userId, Long id){
        if(!roleAuthorization.hasDeleteExpenseCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to delete expense category");
        }
        else {
            if(!expenseCategoryRepository.existsById(id)){
                throw new RuntimeException("Expense category does not exists");
            }
            else{
                expenseCategoryRepository.deleteById(id);
                return true;
            }
        }
    }

}
