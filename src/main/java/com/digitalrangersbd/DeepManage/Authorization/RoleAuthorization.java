package com.digitalrangersbd.DeepManage.Authorization;

import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Enum.Permission;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthorization {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleAuthorization(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //Check for creating new user
    public boolean hasCreateUserPermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_USER)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing user data
    public boolean hasViewUserPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_USER)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating user data
    public boolean hasUpdateUserPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_USER)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting user data
    public boolean hasDeleteUserPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_USER)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new category
    public boolean hasCreateCategoryPermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_CATEGORY)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Category data
    public boolean hasViewCategoryPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_CATEGORY)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Category data
    public boolean hasUpdateCategoryPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_CATEGORY)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting category data
    public boolean hasDeleteCategoryPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_CATEGORY)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new brand
    public boolean hasCreateBrandPermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_BRAND)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Brand data
    public boolean hasViewBrandPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_BRAND)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Brand data
    public boolean hasUpdateBrandPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_BRAND)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Brand data
    public boolean hasDeleteBrandPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_BRAND)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new product
    public boolean hasCreateProductPermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_PRODUCT)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Product data
    public boolean hasViewProductPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_PRODUCT)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Product data
    public boolean hasUpdateProductPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_PRODUCT)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Product data
    public boolean hasDeleteProductPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_PRODUCT)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new warehouse
    public boolean hasCreateWarehousePermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_WAREHOUSE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Warehouse data
    public boolean hasViewWarehousePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_WAREHOUSE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Warehouse data
    public boolean hasUpdateWarehousePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_WAREHOUSE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Warehouse data
    public boolean hasDeleteWarehousePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_WAREHOUSE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating new outlet
    public boolean hasCreateOutletPermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_OUTLET)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Outlet data
    public boolean hasViewOutletPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_OUTLET)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Outlet data
    public boolean hasUpdateOutletPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_OUTLET)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Outlet data
    public boolean hasDeleteOutletPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_OUTLET)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating payment type
    public boolean hasCreatePaymentTypePermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_PAYMENT_TYPE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing payment type data
    public boolean hasViewPaymentTypePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_PAYMENT_TYPE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Payment Type data
    public boolean hasUpdatePaymentTypePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_PAYMENT_TYPE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Payment data
    public boolean hasDeletePaymentTypePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_PAYMENT_TYPE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Purchase
    public boolean hasCreatePurchasePermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_PURCHASE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Purchase data
    public boolean hasViewPurchasePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_PURCHASE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Purchase data
    public boolean hasUpdatePurchasePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_PURCHASE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Purchase data
    public boolean hasDeletePurchasePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_PURCHASE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating Sale
    public boolean hasCreateSalePermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_SALE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Sale data
    public boolean hasViewSalePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_SALE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Sale data
    public boolean hasUpdateSalePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_SALE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Sale data
    public boolean hasDeleteSalePermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_SALE)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating StockAdjustment
    public boolean hasCreateStockAdjustmentPermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_STOCK)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Stock Adjustment data
    public boolean hasViewStockAdjustmentPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_STOCK)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Stock Adjustment data
    public boolean hasUpdateStockAdjustmentPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_STOCK)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Stock Adjustment data
    public boolean hasDeleteStockAdjustmentPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_STOCK)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check for creating WeightLess
    public boolean hasCreateWeightLessPermission(String roleId){

        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.CREATE_WEIGHT_LESS)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before Viewing Weight Less data
    public boolean hasViewWeightLessPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.VIEW_WEIGHT_LESS)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before updating Weight Less data
    public boolean hasUpdateWeightlessPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.UPDATE_WEIGHT_LESS)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    //Check before deleting Weight Less data
    public boolean hasDeleteWeightLessPermission(String roleId){
        try{
            return roleRepository.findById(roleId)
                    .map(role -> role.getPermission().stream()
                            .anyMatch(permission -> permission == Permission.DELETE_WEIGHT_LESS)
                    ).orElse(false);
        }
        catch (Exception e){
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }
}

