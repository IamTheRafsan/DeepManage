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
}
