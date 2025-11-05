package com.digitalrangersbd.DeepManage.Authorization;

import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Enum.Permission;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleAuthorization {

    @Autowired
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleAuthorization(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    //Look for permission in the database
    public boolean hasPermission(String userId, Permission permissionToCheck) {
        try {
            return userRepository.findById(userId)
                    .map(user -> user.getRole().stream()
                            .anyMatch(role -> role.getPermission().contains(permissionToCheck))
                    )
                    .orElse(false);
        } catch (Exception e) {
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }



    //Check for creating new role
    public boolean hasCreateRolePermission(String userId){
        return hasPermission(userId, Permission.CREATE_ROLE);
    }

    //Check before Viewing Role data
    public boolean hasViewRolePermission(String userId){
        return hasPermission(userId, Permission.VIEW_ROLE);
    }

    //Check before updating role data
    public boolean hasUpdateRolePermission(String userId){
        return hasPermission(userId, Permission.UPDATE_ROLE);
    }

    //Check before deleting Role data
    public boolean hasDeleteRolePermission(String userId){
        return hasPermission(userId, Permission.DELETE_ROLE);
    }

    //Check for creating new user
    public boolean hasCreateUserPermission(String userId){
        return hasPermission(userId, Permission.CREATE_USER);
    }

    //Check before Viewing user data
    public boolean hasViewUserPermission(String userId){
        return hasPermission(userId, Permission.VIEW_USER);
    }

    //Check before updating user data
    public boolean hasUpdateUserPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_USER);
    }

    //Check before deleting user data
    public boolean hasDeleteUserPermission(String userId){
        return hasPermission(userId, Permission.DELETE_USER);
    }

    //Check for creating new category
    public boolean hasCreateCategoryPermission(String userId){
        return hasPermission(userId, Permission.CREATE_CATEGORY);
    }

    //Check before Viewing Category data
    public boolean hasViewCategoryPermission(String userId){
        return hasPermission(userId, Permission.VIEW_CATEGORY);
    }

    //Check before updating Category data
    public boolean hasUpdateCategoryPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_CATEGORY);
    }

    //Check before deleting category data
    public boolean hasDeleteCategoryPermission(String userId){
        return hasPermission(userId, Permission.DELETE_CATEGORY);
    }

    //Check for creating new brand
    public boolean hasCreateBrandPermission(String userId){
        return hasPermission(userId, Permission.CREATE_BRAND);
    }

    //Check before Viewing Brand data
    public boolean hasViewBrandPermission(String userId){
        return hasPermission(userId, Permission.VIEW_BRAND);
    }

    //Check before updating Brand data
    public boolean hasUpdateBrandPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_BRAND);
    }

    //Check before deleting Brand data
    public boolean hasDeleteBrandPermission(String userId){
        return hasPermission(userId, Permission.DELETE_BRAND);
    }

    //Check for creating new product
    public boolean hasCreateProductPermission(String userId){
        return hasPermission(userId, Permission.CREATE_PRODUCT);
    }

    //Check before Viewing Product data
    public boolean hasViewProductPermission(String userId){
        return hasPermission(userId, Permission.VIEW_PRODUCT);
    }

    //Check before updating Product data
    public boolean hasUpdateProductPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_PRODUCT);
    }

    //Check before deleting Product data
    public boolean hasDeleteProductPermission(String userId){
        return hasPermission(userId, Permission.DELETE_PRODUCT);
    }

    //Check for creating new warehouse
    public boolean hasCreateWarehousePermission(String userId){
        return hasPermission(userId, Permission.CREATE_WAREHOUSE);
    }

    //Check before Viewing Warehouse data
    public boolean hasViewWarehousePermission(String userId){
        return hasPermission(userId, Permission.VIEW_WAREHOUSE);
    }

    //Check before updating Warehouse data
    public boolean hasUpdateWarehousePermission(String userId){
        return hasPermission(userId, Permission.UPDATE_WAREHOUSE);
    }

    //Check before deleting Warehouse data
    public boolean hasDeleteWarehousePermission(String userId){
        return hasPermission(userId, Permission.DELETE_WAREHOUSE);
    }

    //Check for creating new outlet
    public boolean hasCreateOutletPermission(String userId){
        return hasPermission(userId, Permission.CREATE_OUTLET);
    }

    //Check before Viewing Outlet data
    public boolean hasViewOutletPermission(String userId){
        return hasPermission(userId, Permission.VIEW_OUTLET);
    }

    //Check before updating Outlet data
    public boolean hasUpdateOutletPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_OUTLET);
    }

    //Check before deleting Outlet data
    public boolean hasDeleteOutletPermission(String userId){
        return hasPermission(userId, Permission.DELETE_OUTLET);
    }

    //Check for creating payment type
    public boolean hasCreatePaymentTypePermission(String userId){
        return hasPermission(userId, Permission.CREATE_PAYMENT_TYPE);
    }

    //Check before Viewing payment type data
    public boolean hasViewPaymentTypePermission(String userId){
        return hasPermission(userId, Permission.VIEW_PAYMENT_TYPE);
    }

    //Check before updating Payment Type data
    public boolean hasUpdatePaymentTypePermission(String userId){
        return hasPermission(userId, Permission.UPDATE_PAYMENT_TYPE);
    }

    //Check before deleting Payment data
    public boolean hasDeletePaymentTypePermission(String userId){
        return hasPermission(userId, Permission.DELETE_PAYMENT_TYPE);
    }

    //Check for creating Purchase
    public boolean hasCreatePurchasePermission(String userId){
        return hasPermission(userId, Permission.CREATE_PURCHASE);
    }

    //Check before Viewing Purchase data
    public boolean hasViewPurchasePermission(String userId){
        return hasPermission(userId, Permission.VIEW_PURCHASE);
    }

    //Check before updating Purchase data
    public boolean hasUpdatePurchasePermission(String userId){
        return hasPermission(userId, Permission.UPDATE_PURCHASE);
    }

    //Check before deleting Purchase data
    public boolean hasDeletePurchasePermission(String userId){
        return hasPermission(userId, Permission.DELETE_PURCHASE);
    }

    //Check for creating Sale
    public boolean hasCreateSalePermission(String userId){
        return hasPermission(userId, Permission.CREATE_SALE);
    }

    //Check before Viewing Sale data
    public boolean hasViewSalePermission(String userId){
        return hasPermission(userId, Permission.VIEW_SALE);
    }

    //Check before updating Sale data
    public boolean hasUpdateSalePermission(String userId){
        return hasPermission(userId, Permission.UPDATE_SALE);
    }

    //Check before deleting Sale data
    public boolean hasDeleteSalePermission(String userId){
        return hasPermission(userId, Permission.DELETE_SALE);
    }

    //Check for creating StockAdjustment
    public boolean hasCreateStockAdjustmentPermission(String userId){
        return hasPermission(userId, Permission.CREATE_STOCK);
    }

    //Check before Viewing Stock Adjustment data
    public boolean hasViewStockAdjustmentPermission(String userId){
        return hasPermission(userId, Permission.VIEW_STOCK);
    }

    //Check before updating Stock Adjustment data
    public boolean hasUpdateStockAdjustmentPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_STOCK);
    }

    //Check before deleting Stock Adjustment data
    public boolean hasDeleteStockAdjustmentPermission(String userId){
        return hasPermission(userId, Permission.DELETE_STOCK);
    }

    //Check for creating WeightLess
    public boolean hasCreateWeightLessPermission(String userId){
        return hasPermission(userId, Permission.CREATE_WEIGHT_LESS);
    }

    //Check before Viewing Weight Less data
    public boolean hasViewWeightLessPermission(String userId){
        return hasPermission(userId, Permission.VIEW_WEIGHT_LESS);
    }

    //Check before updating Weight Less data
    public boolean hasUpdateWeightlessPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_WEIGHT_LESS);
    }

    //Check before deleting Weight Less data
    public boolean hasDeleteWeightLessPermission(String userId){
        return hasPermission(userId, Permission.DELETE_WEIGHT_LESS);
    }

    //Check for creating Weight Wastage
    public boolean hasCreateWeightWastagePermission(String userId){
        return hasPermission(userId, Permission.CREATE_WEIGHT_WASTAGE);
    }

    //Check before Viewing Weight Wastage data
    public boolean hasViewWeightWastagePermission(String userId){
        return hasPermission(userId, Permission.VIEW_WEIGHT_WASTAGE);
    }

    //Check before updating Weight Wastage data
    public boolean hasUpdateWeightWastagePermission(String userId){
        return hasPermission(userId, Permission.UPDATE_WEIGHT_WASTAGE);
    }

    //Check before deleting Weight Wastage data
    public boolean hasDeleteWeightWastagePermission(String userId){
        return hasPermission(userId, Permission.DELETE_WEIGHT_WASTAGE);
    }

    //Check for creating Expense Category
    public boolean hasCreateExpenseCategoryPermission(String userId){
        return hasPermission(userId, Permission.CREATE_EXPENSE_CATEGORY);
    }

    //Check before Viewing Expense Category
    public boolean hasViewExpenseCategoryPermission(String userId){
        return hasPermission(userId, Permission.VIEW_EXPENSE_CATEGORY);
    }

    //Check before updating Expense Category
    public boolean hasUpdateExpenseCategoryPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_EXPENSE_CATEGORY);
    }

    //Check before deleting Expense Category data
    public boolean hasDeleteExpenseCategoryPermission(String userId){
        return hasPermission(userId, Permission.DELETE_EXPENSE_CATEGORY);
    }

    //Check for creating Expense
    public boolean hasCreateExpensePermission(String userId){
        return hasPermission(userId, Permission.CREATE_EXPENSE);
    }

    //Check before Viewing Expense
    public boolean hasViewExpensePermission(String userId){
        return hasPermission(userId, Permission.VIEW_EXPENSE);
    }

    //Check before updating Expense
    public boolean hasUpdateExpensePermission(String userId){
        return hasPermission(userId, Permission.UPDATE_EXPENSE);
    }

    //Check before deleting Expense data
    public boolean hasDeleteExpensePermission(String userId){
        return hasPermission(userId, Permission.DELETE_EXPENSE);
    }

    //Check for creating Deposit Category
    public boolean hasCreateDepositCategoryPermission(String userId){
        return hasPermission(userId, Permission.CREATE_DEPOSIT_CATEGORY);
    }

    //Check before Viewing Deposit Category
    public boolean hasViewDepositCategoryPermission(String userId){
        return hasPermission(userId, Permission.VIEW_DEPOSIT_CATEGORY);
    }

    //Check before updating Deposit Category
    public boolean hasUpdateDepositCategoryPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_DEPOSIT_CATEGORY);
    }

    //Check before deleting Deposit Category data
    public boolean hasDeleteDepositCategoryPermission(String userId){
        return hasPermission(userId, Permission.DELETE_DEPOSIT_CATEGORY);
    }

    //Check for creating Deposit
    public boolean hasCreateDepositPermission(String userId){
        return hasPermission(userId, Permission.CREATE_DEPOSIT);
    }

//    //Check before Viewing Deposit
//    public boolean hasViewDepositPermission(String userId
//   ){
//        try{
//           return roleRepository.findById userId
//          )
//                   .map(role -> role.getPermission().stream()
//                           .anyMatch(permission -> permission == Permission.VIEW_DEPOSIT)
//                  ).orElse(false);
//        }
//        catch (Exception e){
//            System.err.println("Error checking permission: " + e.getMessage());
//            return false;
//        }
//    }

    //Check before Viewing Deposit
    public boolean hasViewDepositPermission(String userId){
        return hasPermission(userId, Permission.VIEW_DEPOSIT);
    }

    //Check before updating Deposit
    public boolean hasUpdateDepositPermission(String userId){
        return hasPermission(userId, Permission.UPDATE_DEPOSIT);
    }

    //Check before deleting Deposit data
    public boolean hasDeleteDepositPermission(String userId){
        return hasPermission(userId, Permission.DELETE_DEPOSIT);
    }

}

