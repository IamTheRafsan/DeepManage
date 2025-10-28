package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.WeightWastageDto;
import com.digitalrangersbd.DeepManage.Dto.WeightWastageUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.Repository.*;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WeightWastageService {

    private final WeightWastageRepository weightWastageRepository;
    private final WeightWastageItemRepository weightWastageItemRepository;
    private final RoleAuthorization roleAuthorization;
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseRepository purchaseRepository;

    public WeightWastageService(WeightWastageRepository weightWastageRepository, WeightWastageItemRepository weightWastageItemRepository, RoleAuthorization roleAuthorization, PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository) {
        this.weightWastageRepository = weightWastageRepository;
        this.weightWastageItemRepository = weightWastageItemRepository;
        this.roleAuthorization = roleAuthorization;
        this.purchaseItemRepository = purchaseItemRepository;
        this.purchaseRepository = purchaseRepository;
    }

    //Create new Weight Wastage
    public WeightWastage createWeightWastage(String roleId, WeightWastageDto dto){

        if(!roleAuthorization.hasCreateWeightWastagePermission(roleId)){
            throw new SecurityException("User does not have the permission to create weight wastage");
        }
        if(!purchaseItemRepository.existsById(dto.getPurchaseId())){
            throw new RuntimeException("Purchase Id does not exists");
        }
        else{
            WeightWastage weightWastage = new WeightWastage();
            weightWastage.setPurchaseId(dto.getPurchaseId());
            weightWastage.setReason(dto.getReason());
            weightWastage.setCreated_date(LocalDate.now());
            weightWastage.setCreated_time(LocalTime.now());
            weightWastage.setUpdated_date(LocalDate.now());
            weightWastage.setUpdated_time(LocalTime.now());

            List<WeightWastageItem> weightWastageItems = dto.getWeightWastageItem();
            if(weightWastageItems !=null && !weightWastageItems.isEmpty()){

                // Check for duplicate purchase item ID
                Set<Long> purchaseItemIds = new HashSet<>();
                for(WeightWastageItem item : weightWastageItems){
                    Long purchaseItemId = item.getPurchaseItem().getId();
                    if(purchaseItemIds.contains(purchaseItemId)){
                        throw new RuntimeException("Duplicate purchase item ID found: " + purchaseItemId + ". Each purchase item can only be added once.");
                    }
                    purchaseItemIds.add(purchaseItemId);
                }


                for(WeightWastageItem item: weightWastageItems){
                    item.setWeightWastage(weightWastage);
                    weightWastage.getWeightWastageItem().add(item);
                }
            }
            return weightWastageRepository.save(weightWastage);
        }
    }

    //View all weight wastage
    public List<WeightWastage> getAllWeightWastage(String roleId){
        if(!roleAuthorization.hasViewWeightWastagePermission(roleId)){
            throw new SecurityException("User does not have the permission to view weight wastage.");
        }
        else{
            return weightWastageRepository.findAll();
        }
    }

    //View weight wastage by id
    public Optional<WeightWastage> getWeightWastageById(String roleId, Long id){
        if(!roleAuthorization.hasViewWeightWastagePermission(roleId)){
            throw new SecurityException("User does not have the permission to view weight wastage.");
        }
        else{
            return weightWastageRepository.findById(id);
        }
    }

    //Update weight wastage
    public WeightWastage updateWeightWastage(String roleId, Long id, WeightWastageUpdateDto dto){
        if(!roleAuthorization.hasUpdateWeightWastagePermission(roleId)){
            throw new SecurityException("User does not have the permission to update the weight wastage record");
        }
        else{
            return weightWastageRepository.findById(id)
                    .map(weightWastage -> {
                        if(dto.getReason() != null) weightWastage.setReason(dto.getReason());
                        weightWastage.setUpdated_date(LocalDate.now());
                        weightWastage.setUpdated_time(LocalTime.now());

                        if(dto.getWeightWastageItem() != null && !dto.getWeightWastageItem().isEmpty()){
                            weightWastage.getWeightWastageItem().clear();

                            for(WeightWastageItem itemDto: dto.getWeightWastageItem()){

                                WeightWastageItem item = new WeightWastageItem();
                                item.setWeightWastage(weightWastage);

                                PurchaseItem purchaseItem = purchaseItemRepository.findById(itemDto.getPurchaseItem().getId())
                                        .orElseThrow(() -> new RuntimeException("PurchaseItem not found with id: "));
                                item.setPurchaseItem(purchaseItem);

                                item.setQuantity(itemDto.getQuantity());

                                weightWastage.getWeightWastageItem().add(item);

                            }
                        }

                        return weightWastageRepository.save(weightWastage);
                    })
                    .orElseThrow(() -> new RuntimeException("Weight Wastage record not found!"));
        }
    }

    //Delete Weight wastage
    public Boolean deleteWeightWastage(String roleId, Long id){

        if(!roleAuthorization.hasDeleteWeightWastagePermission(roleId)){
            throw new SecurityException("User does not have the permission to delete weight wastage entry.");
        }
        else{
            if(weightWastageRepository.existsById(id)){
                weightWastageRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }

}
