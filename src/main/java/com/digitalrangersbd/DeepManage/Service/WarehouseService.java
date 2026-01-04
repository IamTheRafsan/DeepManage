package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.WarehouseDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseResponseDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import com.digitalrangersbd.DeepManage.Repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final RoleAuthorization roleAuthorization;
    private final UserRepository userRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, RoleRepository roleRepository, RoleService roleService, RoleAuthorization roleAuthorization, UserRepository userRepository) {
        this.warehouseRepository = warehouseRepository;
        this.roleAuthorization = roleAuthorization;
        this.userRepository = userRepository;
    }

    //Create new warehouse
    public Warehouse createWarehouse(WarehouseDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateWarehousePermission(userId)){
            throw new SecurityException("User does not have permission to create warehouse");
        }
        else {

            Warehouse warehouse = new Warehouse();
            warehouse.setName(dto.getName());
            warehouse.setEmail(dto.getEmail());
            warehouse.setMobile(dto.getMobile());
            warehouse.setCountry(dto.getCountry());
            warehouse.setCity(dto.getCity());
            warehouse.setArea(dto.getArea());
            warehouse.setStatus(dto.getStatus());

            warehouse.setCreated_date(LocalDate.now());
            warehouse.setCreated_time(LocalTime.now());
            warehouse.setCreated_by_id(userId);
            warehouse.setUpdated_date(LocalDate.now());
            warehouse.setUpdated_time(LocalTime.now());

            return warehouseRepository.save(warehouse);

        }
    }

    //Get warehouse data
    public List<Warehouse> getWarehouse(){

        String userId = UserContext.getUserId();

        if(!roleAuthorization.hasViewWarehousePermission(userId)){
            throw new SecurityException("User does not have permission to view warehouse");
        }
        else{
            return warehouseRepository.findAll()
                    .stream()
                    .filter(warehouse -> !warehouse.isDeleted() )
                    .toList();
        }
    }

    //Get warehouse by id
    public Optional<Warehouse> getWarehouseById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewWarehousePermission(userId)){
            throw new SecurityException("User does not have permission to view warehouse");
        }
        else{
            return warehouseRepository.findById(id);
        }
    }

    //Update warehouse
    public Warehouse updateWarehouse(Long id, WarehouseUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateWarehousePermission(userId)){
            throw new SecurityException("User does not have the permission to update warehouse");
        }
        else{
            return warehouseRepository.findById(id)
                    .map(warehouse -> {

                        if(dto.getName() != null) warehouse.setName(dto.getName());
                        if(dto.getEmail() != null) warehouse.setEmail(dto.getEmail());
                        if(dto.getMobile() != null) warehouse.setMobile(dto.getMobile());
                        if(dto.getCountry() != null) warehouse.setCountry(dto.getCountry());
                        if(dto.getCity() != null) warehouse.setCity(dto.getCity());
                        if(dto.getArea() != null) warehouse.setArea(dto.getArea());
                        if(dto.getStatus() != null) warehouse.setStatus(dto.getStatus());

                        warehouse.setUpdated_date(LocalDate.now());
                        warehouse.setUpdated_time(LocalTime.now());
                        warehouse.setUpdated_by_id(userId);

                        return warehouseRepository.save(warehouse);
                    })
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"+id ));
        }

    }

    //Delete warehouse
    public Warehouse deleteWarehouse(Long id){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasDeleteWarehousePermission(userId)){
            throw new SecurityException("User does not have the permission to delete warehouse");
        }
        return warehouseRepository.findById(id)
                .map(warehouse -> {
                        warehouse.setDeleted(true);
                        warehouse.setDeletedById(userId);
                        warehouse.setDeletedDate(LocalDate.now());
                        warehouse.setDeletedTime(LocalTime.now());

                    return warehouseRepository.save(warehouse);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    }
}
