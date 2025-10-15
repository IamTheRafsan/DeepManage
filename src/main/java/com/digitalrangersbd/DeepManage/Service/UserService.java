package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.UserDto;
import com.digitalrangersbd.DeepManage.Dto.UserUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleAuthorization roleAuthorization;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleAuthorization roleAuthorization, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleAuthorization = roleAuthorization;
        this.roleRepository = roleRepository;
    }

    //Create a user in the database
    public User createUser(String roleId, UserDto dto){

        if(!roleAuthorization.hasCreateUserPermission(String.valueOf(roleId))){
            throw new SecurityException("User does not have permission to create users");
        } else if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }else if (userRepository.existsByMobile(dto.getMobile())) {
            throw new RuntimeException("Mobile already exists: " + dto.getMobile());
        }
        else if (!roleRepository.existsById(dto.getRole_id())) {
            throw new RuntimeException("Role id does not exist: " + dto.getMobile());
        }
        else if (!roleRepository.existsByName(dto.getRole_name())) {
            throw new RuntimeException("Role name does not exist: " + dto.getMobile());
        }
        else if (!roleRepository.existsByName(dto.getRole_name())) {
            throw new RuntimeException("Role name does not exist: " + dto.getMobile());
        }
        else{
            User user = new User();

            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setRole_id(dto.getRole_id());
            user.setRole_name(dto.getRole_name());
            user.setGender(dto.getGender());
            user.setMobile(dto.getMobile());
            user.setCountry(dto.getCountry());
            user.setCity(dto.getCity());
            user.setAddress(dto.getAddress());
            user.setWarehouse_id(dto.getWarehouse_id());
            user.setWarehouse_name(dto.getWarehouse_name());

            user.setCreated_date(LocalDate.now());
            user.setCreated_time(LocalTime.now());
            user.setUpdated_date(LocalDate.now());
            user.setUpdated_time(LocalTime.now());

            return userRepository.save(user);

        }
    }

    //Get all users
    public List<User> getAllUser(String roleId){
        if(!roleAuthorization.hasViewUserPermission(roleId)){
            throw new SecurityException("User does not have permission to view users");
        }
        else {
            return userRepository.findAll();
        }
    }

    //Get Users by id
    public Optional<User> getUserById(String roleId, String id){
        if(!roleAuthorization.hasViewUserPermission(roleId)){
            throw new SecurityException("User does not have permission to view users");
        }
        else {
            return userRepository.findById(id);
        }
    }

    //Update user
    public User updateUser(String roleId, String id, UserUpdateDto dto){
        if(!roleAuthorization.hasUpdateUserPermission(roleId)){
            throw new SecurityException("User does not have permission to update users");
        }
        else{
            return userRepository.findById(id)
                    .map(user -> {
                        if(dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
                        if(dto.getLastName() != null) user.setLastName(dto.getLastName());
                        if(dto.getEmail() != null) user.setEmail(dto.getEmail());
                        if(dto.getPassword() != null) user.setPassword(dto.getPassword());
                        if(dto.getRole_id() != null) user.setRole_id(dto.getRole_id());
                        if(dto.getRole_name() != null) user.setRole_name(dto.getRole_name());
                        if(dto.getGender() != null) user.setGender(dto.getGender());
                        if(dto.getMobile() != null) user.setMobile(dto.getMobile());
                        if(dto.getCountry() != null) user.setCountry(dto.getCountry());
                        if(dto.getCity() != null) user.setCity(dto.getCity());
                        if(dto.getAddress() != null) user.setAddress(dto.getAddress());
                        if(dto.getWarehouse_id() != null) user.setWarehouse_id(dto.getWarehouse_id());
                        if(dto.getWarehouse_name() != null) user.setWarehouse_name(dto.getWarehouse_name());

                        user.setUpdated_date(LocalDate.now());
                        user.setUpdated_time(LocalTime.now());

                        return userRepository.save(user);
                    })
                    .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        }
    }


    //Delete user
    public boolean deleteUser(String roleId, String id){

        if(!roleAuthorization.hasDeleteUserPermission(roleId)){
            throw new SecurityException("User does not have permission to delete users");
        }
        else {
            if(userRepository.existsById(id)){
                userRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}