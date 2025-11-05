package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.WarehouseDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.RoleRepository;
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

    public WarehouseService(WarehouseRepository warehouseRepository, RoleRepository roleRepository, RoleService roleService, RoleAuthorization roleAuthorization) {
        this.warehouseRepository = warehouseRepository;
        this.roleAuthorization = roleAuthorization;
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
            warehouse.setUpdated_date(LocalDate.now());
            warehouse.setUpdated_time(LocalTime.now());

            return warehouseRepository.save(warehouse);

        }
    }

    //Get warehouse date
    public List<Warehouse> getWarehouse(){

        String userId = UserContext.getUserId();

        if(!roleAuthorization.hasViewWarehousePermission(userId)){
            throw new SecurityException("User does not have permission to view warehouse");
        }
        else{
            return warehouseRepository.findAll();
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

                        return warehouseRepository.save(warehouse);
                    })
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"+id ));
        }

    }

    //Delete warehouse
    public Boolean deleteWarehouse(Long id){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasDeleteWarehousePermission(userId)){
            throw new SecurityException("User does not have the permission to delete warehouse");
        }
        else{
            if (warehouseRepository.existsById(id)){
                warehouseRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
