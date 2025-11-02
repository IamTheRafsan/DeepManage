package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.OutletDto;
import com.digitalrangersbd.DeepManage.Dto.OutletUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseDto;
import com.digitalrangersbd.DeepManage.Dto.WarehouseUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.Repository.OutletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OutletService {

    private final OutletRepository outletRepository;
    private final RoleAuthorization roleAuthorization;

    public OutletService(OutletRepository outletRepository, RoleAuthorization roleAuthorization) {
        this.outletRepository = outletRepository;
        this.roleAuthorization = roleAuthorization;
    }

    //Create outlet
    public Outlet createOutlet(String userId, OutletDto dto){

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

            outlet.setCreated_date(LocalDate.now());
            outlet.setCreated_time(LocalTime.now());
            outlet.setUpdated_date(LocalDate.now());
            outlet.setUpdated_time(LocalTime.now());

            return outletRepository.save(outlet);

        }
    }

    //View outlet
    public List<Outlet> getOutlet(String userId){

        if(!roleAuthorization.hasViewOutletPermission(userId)){
            throw new SecurityException("User does not have permission to view outlet");
        }
        else{
            return outletRepository.findAll();
        }
    }

    //View outlet by id
    public Optional<Outlet> getOutletById(String userId, Long id){

        if(!roleAuthorization.hasViewOutletPermission(userId)){
            throw new SecurityException("User does not have permission to view outlet");
        }
        else{
            return outletRepository.findById(id);
        }
    }

    //Update Outlet
    public Outlet updateOutlet(String userId, Long id, OutletUpdateDto dto){

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

                        outlet.setUpdated_date(LocalDate.now());
                        outlet.setUpdated_time(LocalTime.now());

                        return outletRepository.save(outlet);
                    })
                    .orElseThrow(() -> new RuntimeException("Outlet not found"+id ));
        }

    }

    //Delete Outlet
    public Boolean deleteOutlet(String userId, Long id){

        if (!roleAuthorization.hasDeleteOutletPermission(userId)){
            throw new RuntimeException("User does not have the permission to delete outlet");
        }
        else{
            if (outletRepository.existsById(id)){
                outletRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
