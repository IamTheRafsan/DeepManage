package com.digitalrangersbd.DeepManage.Authorization;

import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Enum.Permission;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthorization {

    @Autowired
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleAuthorization(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    //Check for creating new user
    public boolean hasCreateUserPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_USER)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing user data
    public boolean hasViewUserPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_USER)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating user data
    public boolean hasUpdateUserPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_USER)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting user data
    public boolean hasDeleteUserPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_USER)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new category
    public boolean hasCreateCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Category data
    public boolean hasViewCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Category data
    public boolean hasUpdateCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting category data
    public boolean hasDeleteCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new brand
    public boolean hasCreateBrandPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_BRAND)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Brand data
    public boolean hasViewBrandPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_BRAND)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Brand data
    public boolean hasUpdateBrandPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_BRAND)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Brand data
    public boolean hasDeleteBrandPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_BRAND)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new product
    public boolean hasCreateProductPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_PRODUCT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Product data
    public boolean hasViewProductPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_PRODUCT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Product data
    public boolean hasUpdateProductPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_PRODUCT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Product data
    public boolean hasDeleteProductPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_PRODUCT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new warehouse
    public boolean hasCreateWarehousePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_WAREHOUSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Warehouse data
    public boolean hasViewWarehousePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_WAREHOUSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Warehouse data
    public boolean hasUpdateWarehousePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_WAREHOUSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Warehouse data
    public boolean hasDeleteWarehousePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_WAREHOUSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new outlet
    public boolean hasCreateOutletPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_OUTLET)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Outlet data
    public boolean hasViewOutletPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_OUTLET)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Outlet data
    public boolean hasUpdateOutletPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_OUTLET)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Outlet data
    public boolean hasDeleteOutletPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_OUTLET)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating payment type
    public boolean hasCreatePaymentTypePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_PAYMENT_TYPE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing payment type data
    public boolean hasViewPaymentTypePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_PAYMENT_TYPE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Payment Type data
    public boolean hasUpdatePaymentTypePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_PAYMENT_TYPE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Payment data
    public boolean hasDeletePaymentTypePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_PAYMENT_TYPE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Purchase
    public boolean hasCreatePurchasePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_PURCHASE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Purchase data
    public boolean hasViewPurchasePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_PURCHASE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Purchase data
    public boolean hasUpdatePurchasePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_PURCHASE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Purchase data
    public boolean hasDeletePurchasePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_PURCHASE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Sale
    public boolean hasCreateSalePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_SALE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Sale data
    public boolean hasViewSalePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_SALE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Sale data
    public boolean hasUpdateSalePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_SALE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Sale data
    public boolean hasDeleteSalePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_SALE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating StockAdjustment
    public boolean hasCreateStockAdjustmentPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_STOCK)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Stock Adjustment data
    public boolean hasViewStockAdjustmentPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_STOCK)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Stock Adjustment data
    public boolean hasUpdateStockAdjustmentPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_STOCK)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Stock Adjustment data
    public boolean hasDeleteStockAdjustmentPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_STOCK)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating WeightLess
    public boolean hasCreateWeightLessPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_WEIGHT_LESS)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Weight Less data
    public boolean hasViewWeightLessPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_WEIGHT_LESS)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Weight Less data
    public boolean hasUpdateWeightlessPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_WEIGHT_LESS)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Weight Less data
    public boolean hasDeleteWeightLessPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_WEIGHT_LESS)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Weight Wastage
    public boolean hasCreateWeightWastagePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_WEIGHT_WASTAGE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Weight Wastage data
    public boolean hasViewWeightWastagePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_WEIGHT_WASTAGE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Weight Wastage data
    public boolean hasUpdateWeightWastagePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_WEIGHT_WASTAGE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Weight Wastage data
    public boolean hasDeleteWeightWastagePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_WEIGHT_WASTAGE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Expense Category
    public boolean hasCreateExpenseCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_EXPENSE_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Expense Category
    public boolean hasViewExpenseCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_EXPENSE_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Expense Category
    public boolean hasUpdateExpenseCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_EXPENSE_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Expense Category data
    public boolean hasDeleteExpenseCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_EXPENSE_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Expense
    public boolean hasCreateExpensePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_EXPENSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Expense
    public boolean hasViewExpensePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_EXPENSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Expense
    public boolean hasUpdateExpensePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_EXPENSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Expense data
    public boolean hasDeleteExpensePermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_EXPENSE)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Deposit Category
    public boolean hasCreateDepositCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_DEPOSIT_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Deposit Category
    public boolean hasViewDepositCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_DEPOSIT_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Deposit Category
    public boolean hasUpdateDepositCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_DEPOSIT_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Deposit Category data
    public boolean hasDeleteDepositCategoryPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_DEPOSIT_CATEGORY)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Deposit
    public boolean hasCreateDepositPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.CREATE_DEPOSIT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
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
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.VIEW_DEPOSIT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Deposit
    public boolean hasUpdateDepositPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.UPDATE_DEPOSIT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Deposit data
    public boolean hasDeleteDepositPermission(String userId){
        try{
            return userRepository.findById(userId)
                    .map(user -> {
                        String role_Id = user.getRole_id();

                        if (role_Id != null){
                            return roleRepository.findById(role_Id)
                                    .map(role -> role.getPermission().stream()
                                            .anyMatch(permission -> permission == Permission.DELETE_DEPOSIT)
                                    ).orElse(false);
                        }
                        return false;
                    })
                    .orElse(false);

        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

}

