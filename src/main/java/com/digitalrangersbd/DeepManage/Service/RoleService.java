package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Dto.RoleDto;
import com.digitalrangersbd.DeepManage.Dto.RoleUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //Create new role
    public Role createRole(RoleDto dto) {

        Role role = new Role();
        role.setName(dto.getName());
        role.setCreated_by_id(dto.getCreated_by_id());
        role.setCreated_by_name(dto.getCreated_by_name());
        role.setPermission(dto.getPermission());

        // Set timestamps
        role.setCreated_date(LocalDate.now());
        role.setCreated_time(LocalTime.now());
        role.setUpdated_date(LocalDate.now());
        role.setUpdated_time(LocalTime.now());

        return roleRepository.save(role);

    }

    //Get roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    //Get roles by id
    public Optional<Role> getRolesById(String id) {
        return roleRepository.findById(id);
    }

    //update role
    public Role updateRole(String id, RoleUpdateDto dto) {

        return roleRepository.findById(id)
                .map(existingRole -> {
                    if (dto.getName() != null) existingRole.setName(dto.getName());
                    if (dto.getCreated_by_id() != null) existingRole.setCreated_by_id(dto.getCreated_by_id());
                    if (dto.getCreated_by_name() != null) existingRole.setCreated_by_name(dto.getCreated_by_name());
                    if (dto.getPermission() != null) existingRole.setPermission(dto.getPermission());

                    existingRole.setUpdated_date(LocalDate.now());
                    existingRole.setUpdated_time(LocalTime.now());

                    return roleRepository.save(existingRole);
                })
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    //delete role
    public boolean deleteRole(String id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}