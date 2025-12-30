package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    //Check if role id exists before creating user
    boolean existsById(String roleId);

    //Check if role name exists before creating user
    boolean existsByName(String roleName);

    List<Role> findByDeletedFalse();

}
