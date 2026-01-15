package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.WeightWastageDto;
import com.digitalrangersbd.DeepManage.Dto.WeightWastageUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
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
    private final UserRepository userRepository;

    public WeightWastageService(WeightWastageRepository weightWastageRepository, WeightWastageItemRepository weightWastageItemRepository, RoleAuthorization roleAuthorization, PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.weightWastageRepository = weightWastageRepository;
        this.weightWastageItemRepository = weightWastageItemRepository;
        this.roleAuthorization = roleAuthorization;
        this.purchaseItemRepository = purchaseItemRepository;
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    //Create new Weight Wastage
    public WeightWastage createWeightWastage(WeightWastageDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateWeightWastagePermission(userId)){
            throw new SecurityException("User does not have the permission to create weight wastage");
        }
//        if(purchaseRepository.existsById(dto.getPurchaseId())){
//            throw new RuntimeException("Weightless entry already exists");
//        }
        else{
            WeightWastage weightWastage = new WeightWastage();
            weightWastage.setReason(dto.getReason());
            weightWastage.setCreated_date(LocalDate.now());
            weightWastage.setCreated_time(LocalTime.now());
            weightWastage.setUpdated_date(LocalDate.now());
            weightWastage.setUpdated_time(LocalTime.now());

            if(dto.getPurchaseId() != null){
                Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                        .orElseThrow(() -> new RuntimeException("PUrcahse ID not found"));
                weightWastage.setPurchase(purchase);
            }

            if(dto.getUserId() != null){
                User user = userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                weightWastage.setUser(user);
            }

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

                    PurchaseItem purchaseItem = purchaseItemRepository.findById(purchaseItemId)
                            .orElseThrow(() -> new RuntimeException("Purchase item not found: " + purchaseItemId));

                    if(!purchaseItem.getPurchase().getId().equals(dto.getPurchaseId())) {
                        throw new RuntimeException("Purchase item ID " + purchaseItemId +
                                " does not belong to purchase ID " + dto.getPurchaseId());
                    }

                    item.setPurchaseItem(purchaseItem);
                    item.setWeightWastage(weightWastage);
                    weightWastage.getWeightWastageItem().add(item);
                }
            }
            return weightWastageRepository.save(weightWastage);
        }
    }

    //View all weight wastage
    public List<WeightWastage> getAllWeightWastage(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewWeightWastagePermission(userId)){
            throw new SecurityException("User does not have the permission to view weight wastage.");
        }
        else{
            return weightWastageRepository.findByDeletedFalse();
        }
    }

    //View weight wastage by id
    public Optional<WeightWastage> getWeightWastageById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewWeightWastagePermission(userId)){
            throw new SecurityException("User does not have the permission to view weight wastage.");
        }
        else{
            return weightWastageRepository.findById(id);
        }
    }

    //Update weight wastage
    public WeightWastage updateWeightWastage(Long id, WeightWastageUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateWeightWastagePermission(userId)){
            throw new SecurityException("User does not have the permission to update the weight wastage record");
        }
        else{
            return weightWastageRepository.findById(id)
                    .map(weightWastage -> {
                        if(dto.getReason() != null) weightWastage.setReason(dto.getReason());
                        weightWastage.setUpdated_date(LocalDate.now());
                        weightWastage.setUpdated_time(LocalTime.now());

                        if(dto.getPurchaseId() != null){
                            Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                                    .orElseThrow(() -> new RuntimeException("PUrcahse ID not found"));
                            weightWastage.setPurchase(purchase);
                        }

                        if(dto.getUserId() != null){
                            User user = userRepository.findById(dto.getUserId())
                                    .orElseThrow(() -> new RuntimeException("User not found"));
                            weightWastage.setUser(user);
                        }

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

                                PurchaseItem purchaseItem = purchaseItemRepository.findById(purchaseItemId)
                                        .orElseThrow(() -> new RuntimeException("Purchase item not found: " + purchaseItemId));

                                if(!purchaseItem.getPurchase().getId().equals(dto.getPurchaseId())) {
                                    throw new RuntimeException("Purchase item ID " + purchaseItemId +
                                            " does not belong to purchase ID " + dto.getPurchaseId());
                                }

                                item.setPurchaseItem(purchaseItem);
                                item.setWeightWastage(weightWastage);
                                weightWastage.getWeightWastageItem().add(item);
                            }
                        }
                        return weightWastageRepository.save(weightWastage);

                    })
                    .orElseThrow(() -> new RuntimeException("Weight Wastage record not found!"));
        }
    }

    //Delete Weight wastage
    public WeightWastage deleteWeightWastage(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteWeightWastagePermission(userId)){
            throw new SecurityException("User does not have the permission to delete weight wastage entry.");
        }
        return weightWastageRepository.findById(id)
                .map(weightWastage -> {
                    weightWastage.setDeleted(true);
                    weightWastage.setDeletedById(userId);
                    weightWastage.setDeletedDate(LocalDate.now());
                    weightWastage.setDeletedTime(LocalTime.now());

                    return weightWastageRepository.save(weightWastage);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    }

}
