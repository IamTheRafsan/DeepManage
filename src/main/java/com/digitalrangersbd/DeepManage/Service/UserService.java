package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.UserDataResponseDto;
import com.digitalrangersbd.DeepManage.Dto.UserDto;
import com.digitalrangersbd.DeepManage.Dto.UserResponseDto;
import com.digitalrangersbd.DeepManage.Dto.UserUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.OutletRepository;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import com.digitalrangersbd.DeepManage.Repository.WarehouseRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleAuthorization roleAuthorization;

    private final RoleRepository roleRepository;

    private final WarehouseRepository warehouseRepository;

    private final OutletRepository outletRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleAuthorization roleAuthorization, RoleRepository roleRepository, WarehouseRepository warehouseRepository, OutletRepository outletRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleAuthorization = roleAuthorization;
        this.roleRepository = roleRepository;
        this.warehouseRepository = warehouseRepository;
        this.outletRepository = outletRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Mapper for response
    private UserDataResponseDto mapToUserResponseDto(User user) {
        return new UserDataResponseDto(
                user.getUser_id(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGender().name(),
                String.valueOf(user.getMobile()),
                user.getCountry(),
                user.getCity(),
                user.getAddress(),
                user.getRole()
                        .stream()
                        .map(Role::getName)
                        .toList(),
                user.isDeleted(),
                user.getCreated_date(),
                user.getCreated_time(),
                user.getUpdated_date(),
                user.getUpdated_time()
        );
    }


    //Create a user in the database
    public User createUser(UserDto dto) {

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasCreateUserPermission(userId)) {
            throw new SecurityException("User does not have permission to create users");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }
        if (userRepository.existsByMobile(dto.getMobile())) {
            throw new RuntimeException("Mobile already exists: " + dto.getMobile());
        } else {
            User user = new User();

            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setGender(dto.getGender());
            user.setMobile(dto.getMobile());
            user.setCountry(dto.getCountry());
            user.setCity(dto.getCity());
            user.setAddress(dto.getAddress());

            //Put the password in database after encryption
            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            user.setPassword(encodedPassword);


            //Getting the roles for user
            Set<Role> roleSet = new HashSet<>();
            for (String roleId : dto.getRole_id()) {
                Role role = roleRepository.findById(roleId)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                roleSet.add(role);
            }
            user.setRole(roleSet);

            //Getting the warehouse for user
            if (dto.getWarehouse() != null) {
                Set<Warehouse> warehouseSet = new HashSet<>();
                for (Long warehouseId : dto.getWarehouse()) {
                    Warehouse warehouse = warehouseRepository.findById(warehouseId)
                            .orElseThrow(() -> new RuntimeException("Warehouse not found"));
                    warehouseSet.add(warehouse);
                }
                user.setWarehouse(warehouseSet);
            }

            //Getting the outlet for users
            if (dto.getOutlet() != null) {
                Set<Outlet> outletSet = new HashSet<>();
                for (Long outletId : dto.getOutlet()) {
                    Outlet outlet = outletRepository.findById(outletId)
                            .orElseThrow(() -> new RuntimeException("Outlet not found"));
                    outletSet.add(outlet);
                }
                user.setOutlet(outletSet);
            }

            User currentUser = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            user.setCreated_by_name(currentUser.getFirstName() + " " + currentUser.getLastName());


            user.setCreated_date(LocalDate.now());
            user.setCreated_time(LocalTime.now());
            user.setUpdated_date(LocalDate.now());
            user.setUpdated_time(LocalTime.now());

            return userRepository.save(user);

        }
    }

    //Get all users
    public List<UserDataResponseDto> getAllUser() {

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasViewUserPermission(userId)) {
            throw new SecurityException("User does not have permission to view users");
        }

        return userRepository.findAll()
                .stream()
                .filter(user -> !user.isDeleted())
                .map(this::mapToUserResponseDto)
                .toList();
    }


    //Get Users by id
    public Optional<UserDataResponseDto> getUserById(String id) {

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasViewUserPermission(userId)) {
            throw new SecurityException("User does not have permission to view users");
        } else {
            return userRepository.findById(id)
                    .map(this::mapToUserResponseDto);
        }
    }

    //Update user
    public User updateUser(String id, UserUpdateDto dto) {

        String userId = UserContext.getUserId();
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        if (!roleAuthorization.hasUpdateUserPermission(userId)) {
            throw new SecurityException("User does not have permission to update users");
        }
        //Check if email already exists
        if (dto.getEmail() != null && !dto.getEmail().equals(existingUser.getEmail())) {
            Optional<User> userWithSameEmail = userRepository.findByEmail(dto.getEmail());
            if (userWithSameEmail.isPresent() && !userWithSameEmail.get().getUser_id().equals(id)) {
                throw new RuntimeException("Email already exists: " + dto.getEmail());
            }
        }
        //Check if mobile already exists
        if (dto.getMobile() != null && !dto.getMobile().equals(existingUser.getMobile())) {
            Optional<User> userWithSameMobile = userRepository.findByMobile(dto.getMobile());
            if (userWithSameMobile.isPresent() && !userWithSameMobile.get().getUser_id().equals(id)) {
                throw new RuntimeException("Mobile already exists: " + dto.getMobile());
            }
        }
        return userRepository.findById(id)
                .map(user -> {
                    if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
                    if (dto.getLastName() != null) user.setLastName(dto.getLastName());
                    if (dto.getEmail() != null) user.setEmail(dto.getEmail());
                    if (dto.getPassword() != null) user.setPassword(dto.getPassword());
                    if (dto.getGender() != null) user.setGender(dto.getGender());
                    if (dto.getMobile() != null) user.setMobile(dto.getMobile());
                    if (dto.getCountry() != null) user.setCountry(dto.getCountry());
                    if (dto.getCity() != null) user.setCity(dto.getCity());
                    if (dto.getAddress() != null) user.setAddress(dto.getAddress());

                    //Encode password
                    if (dto.getPassword() != null) {
                        String encodedPassword = passwordEncoder.encode(dto.getPassword());
                        user.setPassword(encodedPassword);
                    }

                    //Update the roles
                    if (dto.getRole_id() != null && !dto.getRole_id().isEmpty()) {
                        Set<Role> roleSet = new HashSet<>();
                        for (String roleId : dto.getRole_id()) {
                            Role role = roleRepository.findById(roleId)
                                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleId));
                            roleSet.add(role);
                        }
                        user.setRole(roleSet);
                    }

                    //Update the warehosue
                    if (dto.getWarehouse() != null && !dto.getWarehouse().isEmpty()) {
                        Set<Warehouse> warehouseSet = new HashSet<>();

                        for (Long warehouseId : dto.getWarehouse()) {
                            Warehouse warehouse = warehouseRepository.findById(warehouseId)
                                    .orElseThrow(() -> new RuntimeException("Warehouse not found" + warehouseId));
                            warehouseSet.add(warehouse);
                        }
                        user.setWarehouse(warehouseSet);
                    }

                    //Update the outlets
                    if (dto.getOutlet() != null && !dto.getOutlet().isEmpty()) {
                        Set<Outlet> outletSet = new HashSet<>();

                        for (Long outletId : dto.getOutlet()) {
                            Outlet outlet = outletRepository.findById(outletId)
                                    .orElseThrow(() -> new RuntimeException("Outlet not found" + outletId));
                            outletSet.add(outlet);
                        }
                        user.setOutlet(outletSet);
                    }

                    user.setUpdated_by_id(userId);
                    User currentUser = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    user.setUpdated_by_name(currentUser.getFirstName() + " " + currentUser.getLastName());

                    user.setUpdated_date(LocalDate.now());
                    user.setUpdated_time(LocalTime.now());

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    }

    //Delete user
    public User deleteUser(String id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteUserPermission(userId)){
            throw new SecurityException("User does not have permission to delete users");
        }
        return userRepository.findById(id)
                .map(user -> {
                    User currentUser = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    user.setDeleted(true);
                    user.setDeletedById(userId);
                    user.setDeletedByName(currentUser.getFirstName() + " " + currentUser.getLastName());
                    user.setDeletedDate(LocalDate.now());
                    user.setDeletedTime(LocalTime.now());

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    }
}