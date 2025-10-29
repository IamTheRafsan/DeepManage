package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.ExpenseDto;
import com.digitalrangersbd.DeepManage.Dto.ExpenseUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.OutletDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final RoleAuthorization roleAuthorization;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final WarehouseRepository warehouseRepository;
    private final OutletRepository outletRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, RoleAuthorization roleAuthorization, ExpenseCategoryRepository expenseCategoryRepository, PaymentTypeRepository paymentTypeRepository, WarehouseRepository warehouseRepository, OutletRepository outletRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.roleAuthorization = roleAuthorization;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.warehouseRepository = warehouseRepository;
        this.outletRepository = outletRepository;
        this.userRepository = userRepository;
    }

    //Create new expense
    public Expense createExpense(String roleId, ExpenseDto dto){

        if(!roleAuthorization.hasCreateExpensePermission(roleId)){
            throw new SecurityException("User does not have the persmission to create expense.");
        }
        else{
            Expense expense = new Expense();
            expense.setName(dto.getName());
            expense.setStatus(dto.getStatus());
            expense.setAmount(dto.getAmount());
            expense.setDescription(dto.getDescription());

            expense.setCreated_date(LocalDate.now());
            expense.setCreated_time(LocalTime.now());
            expense.setUpdated_date(LocalDate.now());
            expense.setUpdated_time(LocalTime.now());

            if(dto.getCategory() != null){
                ExpenseCategory expenseCategory = expenseCategoryRepository.findById(dto.getCategory())
                        .orElseThrow(() -> new RuntimeException("Expense category does not exists"));
                expense.setCategory(expenseCategory);
            }

            if(dto.getPaymentType() != null){
                PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType())
                        .orElseThrow(() -> new RuntimeException("Payment type does not exists"));
                expense.setPaymentType(paymentType);
            }

            if(dto.getWarehouseId() != null){
                Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                        .orElseThrow(() -> new RuntimeException("Warehouse does not exists"));
                expense.setWarehouse(warehouse);
            }

            if(dto.getOutletId() != null){
                Outlet outlet = outletRepository.findById(dto.getOutletId())
                        .orElseThrow(() -> new RuntimeException("Outlet does not exists"));
                expense.setOutlet(outlet);
            }

            if(dto.getUserId() != null){
                if(!userRepository.existsById(dto.getUserId())){
                    throw new RuntimeException("User id does not exists");
                }
                else{
                    expense.setUserId(dto.getUserId());
                }
            }

            return expenseRepository.save(expense);

        }

    }

    //View all expense
    public List<Expense> getAllExpense(String roleId){
        if(!roleAuthorization.hasViewExpensePermission(roleId)){
            throw new SecurityException("User does not have the persmission to view expense.");
        }
        else{
            return expenseRepository.findAll();
        }
    }

    //View expense by id
    public Optional<Expense> getExpenseById(String roleId, Long id){
        if(!roleAuthorization.hasViewExpensePermission(roleId)){
            throw new SecurityException("User does not have the persmission to view expense.");
        }
        else{
            return expenseRepository.findById(id);
        }
    }

    //Update expense
    public Expense updateExpense(String roleId, Long id, ExpenseUpdateDto dto){
        if(!roleAuthorization.hasUpdateExpensePermission(roleId)){
            throw new SecurityException("User does not have the persmission to update expense.");
        }
        else{
            return expenseRepository.findById(id)
                    .map(expense -> {
                        if(dto.getName() != null) expense.setName(dto.getName());
                        if(dto.getStatus() != null) expense.setStatus(dto.getStatus());
                        if(dto.getAmount() != null) expense.setAmount(dto.getAmount());
                        if(dto.getDescription() != null) expense.setDescription(dto.getDescription());

                        expense.setUpdated_date(LocalDate.now());
                        expense.setUpdated_time(LocalTime.now());

                        if(dto.getCategory() != null){
                            ExpenseCategory expenseCategory = expenseCategoryRepository.findById(dto.getCategory().getId())
                                    .orElseThrow(() -> new RuntimeException("Expense category does not exists"));
                            expense.setCategory(expenseCategory);
                        }

                        if(dto.getPaymentType() != null){
                            PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType().getId())
                                    .orElseThrow(() -> new RuntimeException("Payment type does not exists"));
                            expense.setPaymentType(paymentType);
                        }

                        return expenseRepository.save(expense);

                    })
                    .orElseThrow(() -> new RuntimeException("Expense does not exists."));
        }
    }

    //Delete expense
    public Boolean deleteExpense(String roleId, Long id){
        if(!roleAuthorization.hasDeleteExpensePermission(roleId)){
            throw new SecurityException("User does not have the persmission to delete expense.");
        }
        else{
            if(expenseRepository.existsById(id)){
                expenseRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}

