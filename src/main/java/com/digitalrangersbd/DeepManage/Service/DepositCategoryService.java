package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.DepositCategoryDto;
import com.digitalrangersbd.DeepManage.Dto.DepositCategoryDto;
import com.digitalrangersbd.DeepManage.Dto.DepositCategoryUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.DepositCategory;
import com.digitalrangersbd.DeepManage.Entity.DepositCategory;
import com.digitalrangersbd.DeepManage.Repository.DepositCategoryRepository;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepositCategoryService {

    private final DepositCategoryRepository depositCategoryRepository;
    private final RoleAuthorization roleAuthorization;

    public DepositCategoryService(DepositCategoryRepository depositCategoryRepository, RoleAuthorization roleAuthorization) {
        this.depositCategoryRepository = depositCategoryRepository;
        this.roleAuthorization = roleAuthorization;
    }


    //Create Deposit Category
    public DepositCategory createDepositCategory(String userId, DepositCategoryDto dto){
        if(!roleAuthorization.hasCreateDepositCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to create deposit category");
        }
        else{
            DepositCategory depositCategory = new DepositCategory();
            depositCategory.setName(dto.getName());
            depositCategory.setCreated_date(LocalDate.now());
            depositCategory.setCreated_time(LocalTime.now());
            depositCategory.setUpdated_date(LocalDate.now());
            depositCategory.setUpdated_time(LocalTime.now());

            return depositCategoryRepository.save(depositCategory);
        }
    }

    //View deposit category
    public List<DepositCategory> getDepositCategory(String userId){
        if(!roleAuthorization.hasViewDepositCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to view deposit category");
        }
        else{
            return depositCategoryRepository.findAll();
        }
    }

    //View deposit category by id
    public Optional<DepositCategory> getDepositCategoryById(String userId, Long id){
        if(!roleAuthorization.hasViewDepositCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to view deposit category");
        }
        else{
            if(!depositCategoryRepository.existsById(id)){
                throw new RuntimeException("Deposit Category does not exists");
            }
            else{
                return depositCategoryRepository.findById(id);
            }
        }
    }

    //Update Deposit Category
    public DepositCategory updateDepositCategory(String userId, Long id, DepositCategoryUpdateDto dto){
        if(!roleAuthorization.hasUpdateDepositCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to update deposit category");
        }
        else {
            return depositCategoryRepository.findById(id)
                    .map(depositCategory -> {
                        if(dto.getName() != null) depositCategory.setName(dto.getName());
                        depositCategory.setUpdated_date(LocalDate.now());
                        depositCategory.setUpdated_time(LocalTime.now());

                        return depositCategoryRepository.save(depositCategory);
                    })
                    .orElseThrow(() -> new RuntimeException("Deposit category does not exists."));
        }
    }

    //Delete Deposit Category
    public Boolean deleteDepositCategory(String userId, Long id){
        if(!roleAuthorization.hasDeleteDepositCategoryPermission(userId)){
            throw new SecurityException("User does not have the permission to delete deposit category");
        }
        else {
            if(!depositCategoryRepository.existsById(id)){
                throw new RuntimeException("Deposit category does not exist");
            }
            else{
                depositCategoryRepository.deleteById(id);
                return true;
            }
        }
    }
}
