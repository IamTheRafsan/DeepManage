package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.RoleDto;
import com.digitalrangersbd.DeepManage.Dto.RoleUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    //Add role to the database
    @PostMapping("/add")
    public ResponseEntity<Role> createRole(@Valid @RequestBody RoleDto dto){

        try{
            Role createdRole = roleService.createRole(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //Get all roles
    @GetMapping("/view")
    public ResponseEntity<List<Role>> getAllRoles(){

        try{
            return ResponseEntity.ok(roleService.getAllRoles());
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //Get roles by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Role> getRolesById(@PathVariable String id){

        try{
            return ResponseEntity.of(roleService.getRolesById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update role
    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable String id, @Valid @RequestBody RoleUpdateDto dto){

        try{
            Role updatedRole = roleService.updateRole(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedRole);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Delete Role
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id){

        try{
            boolean deletedRole =  roleService.deleteRole(id);
            if(deletedRole){
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