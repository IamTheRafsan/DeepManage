package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.OutletDto;
import com.digitalrangersbd.DeepManage.Dto.OutletUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.OutletRepository;
import com.digitalrangersbd.DeepManage.Repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OutletService {

    private final OutletRepository outletRepository;
    private final WarehouseRepository warehouseRepository;
    private final RoleAuthorization roleAuthorization;

    public OutletService(OutletRepository outletRepository, WarehouseRepository warehouseRepository, RoleAuthorization roleAuthorization) {
        this.outletRepository = outletRepository;
        this.warehouseRepository = warehouseRepository;
        this.roleAuthorization = roleAuthorization;
    }

    //Create outlet
    public Outlet createOutlet(OutletDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateOutletPermission(userId)){
            throw new SecurityException("User does not have permission to create outlet");
        }
        else {

            Outlet outlet = new Outlet();
            outlet.setName(dto.getName());
            outlet.setEmail(dto.getEmail());
            outlet.setMobile(dto.getMobile());
            outlet.setCountry(dto.getCountry());
            outlet.setCity(dto.getCity());
            outlet.setArea(dto.getArea());
            outlet.setStatus(dto.getStatus());
            if(dto.getWarehouse_id() != null){
                Warehouse warehouse = warehouseRepository.findById(dto.getWarehouse_id())
                        .orElseThrow(() -> new RuntimeException("Warehouse not found"));
                outlet.setWarehouse(warehouse);
            }

            outlet.setCreated_date(LocalDate.now());
            outlet.setCreated_time(LocalTime.now());
            outlet.setUpdated_date(LocalDate.now());
            outlet.setUpdated_time(LocalTime.now());
            outlet.setCreated_by_id(userId);

            return outletRepository.save(outlet);

        }
    }

    //View outlet
    public List<Outlet> getOutlet(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewOutletPermission(userId)){
            throw new SecurityException("User does not have permission to view outlet");
        }
        else{
            return outletRepository.findAll()
                    .stream()
                    .filter(outlet -> !outlet.isDeleted())
                    .toList();

        }
    }

    //View outlet by id
    public Optional<Outlet> getOutletById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewOutletPermission(userId)){
            throw new SecurityException("User does not have permission to view outlet");
        }
        else{
            return outletRepository.findById(id);
        }
    }

    //Update Outlet
    public Outlet updateOutlet(Long id, OutletUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateOutletPermission(userId)){
            throw new RuntimeException("User does not have the permission to update outlet");
        }
        else{
            return outletRepository.findById(id)
                    .map(outlet -> {

                        if(dto.getName() != null) outlet.setName(dto.getName());
                        if(dto.getEmail() != null) outlet.setEmail(dto.getEmail());
                        if(dto.getMobile() != null) outlet.setMobile(dto.getMobile());
                        if(dto.getCountry() != null) outlet.setCountry(dto.getCountry());
                        if(dto.getCity() != null) outlet.setCity(dto.getCity());
                        if(dto.getArea() != null) outlet.setArea(dto.getArea());
                        if(dto.getStatus() != null) outlet.setStatus(dto.getStatus());
                        if(dto.getWarehouse_id() != null){
                            Warehouse warehouse = warehouseRepository.findById(dto.getWarehouse_id())
                                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));
                            outlet.setWarehouse(warehouse);
                        }

                        outlet.setUpdated_date(LocalDate.now());
                        outlet.setUpdated_time(LocalTime.now());
                        outlet.setUpdated_by_id(userId);

                        return outletRepository.save(outlet);
                    })
                    .orElseThrow(() -> new RuntimeException("Outlet not found"+id ));
        }

    }

    //Delete Outlet
    public Outlet deleteOutlet(Long id){

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasDeleteOutletPermission(userId)){
            throw new RuntimeException("User does not have the permission to delete outlet");
        }
        return outletRepository.findById(id)
                .map(outlet -> {
                    outlet.setDeleted(true);
                    outlet.setDeletedById(userId);
                    outlet.setDeletedDate(LocalDate.now());
                    outlet.setDeletedTime(LocalTime.now());

                    return outletRepository.save(outlet);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }
}
