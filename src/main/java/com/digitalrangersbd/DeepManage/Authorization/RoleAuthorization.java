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
}
