package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.WeightLessDto;
import com.digitalrangersbd.DeepManage.Dto.WeightLessItemDto;
import com.digitalrangersbd.DeepManage.Dto.WeightLessUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WeightLessService {

    private final WeightLessRepository weightLessRepository;
    private final RoleAuthorization roleAuthorization;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final WeightLessItemRepository weightLessItemRepository;
    private final PurchaseItemRepository purchaseItemRepository;

    public WeightLessService(WeightLessRepository weightLessRepository, RoleAuthorization roleAuthorization, PurchaseRepository purchaseRepository, ProductRepository productRepository, WeightLessItemRepository weightLessItemRepository, PurchaseItemRepository purchaseItemRepository) {
        this.weightLessRepository = weightLessRepository;
        this.roleAuthorization = roleAuthorization;
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.weightLessItemRepository = weightLessItemRepository;
        this.purchaseItemRepository = purchaseItemRepository;
    }

    //Create Weight Less
    public WeightLess createWeightLess(String roleId, WeightLessDto dto) {
        if (!roleAuthorization.hasCreateWeightLessPermission(roleId)) {
            throw new SecurityException("User does not have the permission to create weightless record");
        }
        if(!purchaseItemRepository.existsById(dto.getPurchaseId())){
            throw new RuntimeException("Purchase Id does not exists");
        }
        else {
            WeightLess weightLess = new WeightLess();

            weightLess.setPurchaseId(dto.getPurchaseId());
            weightLess.setReason(dto.getReason());
            weightLess.setCreated_date(LocalDate.now());
            weightLess.setCreated_time(LocalTime.now());
            weightLess.setUpdated_date(LocalDate.now());
            weightLess.setUpdated_time(LocalTime.now());

            List<WeightLessItem> weightLessItems = dto.getWeightLessItem();
            if (weightLessItems != null && !weightLessItems.isEmpty()) {
                // Check for duplicate purchase item ID
                Set<Long> purchaseItemIds = new HashSet<>();
                for(WeightLessItem item : weightLessItems){
                    Long purchaseItemId = item.getPurchaseItem().getId();
                    if(purchaseItemIds.contains(purchaseItemId)){
                        throw new RuntimeException("Duplicate purchase item ID found: " + purchaseItemId + ". Each purchase item can only be added once.");
                    }
                    purchaseItemIds.add(purchaseItemId);
                }

                for (WeightLessItem item : weightLessItems) {
                    item.setWeightLess(weightLess);
                    weightLess.getWeightLessItem().add(item);
                }
            }

            return weightLessRepository.save(weightLess);
        }
    }

    //View all weight less
    public List<WeightLess> getAllWeightLess(String roleId){
        if(!roleAuthorization.hasViewWeightLessPermission(roleId)){
            throw new SecurityException("User does not have the permission to view weightless.");
        }
        else{
            return weightLessRepository.findAll();
        }
    }

    //View weight less by id
    public Optional<WeightLess> getWeightLessById(String roleId, Long id){
        if(!roleAuthorization.hasViewWeightLessPermission(roleId)){
            throw new SecurityException("User does not have the permission to view weightless.");
        }
        else{
            return weightLessRepository.findById(id);
        }
    }

    //Update weight less
    public WeightLess updateWeightLess(String roleId, Long id, WeightLessUpdateDto dto){
        if(!roleAuthorization.hasUpdateWeightlessPermission(roleId)){
            throw new SecurityException("User does not have the permission to update weight less");
        }
        //Check if Weight less entry exists
        return weightLessRepository.findById(id)
                .map(weightLess -> {
                    if (dto.getReason() != null) weightLess.setReason(dto.getReason());
                    weightLess.setUpdated_date(LocalDate.now());
                    weightLess.setUpdated_time(LocalTime.now());

                    if(dto.getWeightLessItem() != null && !dto.getWeightLessItem().isEmpty()){
                        weightLess.getWeightLessItem().clear();

                        for(WeightLessItem itemDto: dto.getWeightLessItem()){

                            WeightLessItem item = new WeightLessItem();

                            item.setWeightLess(weightLess);

                            PurchaseItem purchaseItem = purchaseItemRepository.findById(itemDto.getPurchaseItem().getId())
                                    .orElseThrow(() -> new RuntimeException("PurchaseItem not found with id: "));
                            item.setPurchaseItem(purchaseItem);

                            item.setQuantity(itemDto.getQuantity());

                            weightLess.getWeightLessItem().add(item);

                        }
                    }

                    return weightLessRepository.save(weightLess);

                })
                .orElseThrow(() -> new RuntimeException("Weightless record not found!"));
    }

    //Delete Weight less
    public Boolean deleteWeightLess(String roleId, Long id){

        if(!roleAuthorization.hasDeleteWeightLessPermission(roleId)){
            throw new SecurityException("User does not have the permission to delete weight less entry.");
        }
        else{
            if(weightLessRepository.existsById(id)){
                weightLessRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}