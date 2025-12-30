package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.RoleDto;
import com.digitalrangersbd.DeepManage.Dto.RoleUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleAuthorization roleAuthorization;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, RoleAuthorization roleAuthorization, UserRepository userRepository) {

        this.roleRepository = roleRepository;
        this.roleAuthorization = roleAuthorization;
        this.userRepository = userRepository;
    }

    //Create new role
    public Role createRole(RoleDto dto) {

        String userId = UserContext.getUserId();

        if(!roleAuthorization.hasCreateRolePermission(userId)){
            throw new SecurityException("User does not have the permission to create role.");
        }

        Role role = new Role();
        role.setName(dto.getName());
        role.setPermission(dto.getPermission());
        role.setCreated_by_id(userId);
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        role.setCreated_by_name(currentUser.getFirstName()+" "+currentUser.getLastName());


        // Set timestamps
        role.setCreated_date(LocalDate.now());
        role.setCreated_time(LocalTime.now());
        role.setUpdated_date(LocalDate.now());
        role.setUpdated_time(LocalTime.now());

        return roleRepository.save(role);

    }

    //Get roles
    public List<Role> getAllRoles() {

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewRolePermission(userId)){
            throw new SecurityException("User does not have the permission to view role.");
        }

        return roleRepository.findByDeletedFalse();
    }

    //Get roles by id
    public Optional<Role> getRolesById(String id) {

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewRolePermission(userId)){
            throw new SecurityException("User does not have the permission to view role.");
        }

        return roleRepository.findById(id);
    }

    //update role
    public Role updateRole(String id, RoleUpdateDto dto) {

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateRolePermission(userId)){
            throw new SecurityException("User does not have the permission to update role.");
        }

        return roleRepository.findById(id)
                .map(existingRole -> {
                    if (dto.getName() != null) existingRole.setName(dto.getName());
                    if (dto.getPermission() != null) {
                        existingRole.getPermission().clear();
                        existingRole.setPermission(dto.getPermission());
                    }

                    existingRole.setUpdated_by_id(userId);
                    User currentUser = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    existingRole.setUpdated_by_name(currentUser.getFirstName()+" "+currentUser.getLastName());
                    existingRole.setUpdated_date(LocalDate.now());
                    existingRole.setUpdated_time(LocalTime.now());

                    return roleRepository.save(existingRole);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    //delete role
    public Role deleteRole(String id) {
        String userId = UserContext.getUserId();

        if (!roleAuthorization.hasDeleteRolePermission(userId)) {
            throw new SecurityException("No permission to delete role.");
        }

        return roleRepository.findById(id)
                .map(role -> {
                    User currentUser = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    role.setDeleted(true);
                    role.setDeletedById(userId);
                    role.setDeletedByName(currentUser.getFirstName() + " " + currentUser.getLastName());
                    role.setDeletedDate(LocalDate.now());
                    role.setDeletedTime(LocalTime.now());

                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }


}