package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.WeightLessDto;
import com.digitalrangersbd.DeepManage.Dto.WeightLessItemDto;
import com.digitalrangersbd.DeepManage.Dto.WeightLessUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
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
    private final UserRepository userRepository;

    public WeightLessService(WeightLessRepository weightLessRepository, RoleAuthorization roleAuthorization, PurchaseRepository purchaseRepository, ProductRepository productRepository, WeightLessItemRepository weightLessItemRepository, PurchaseItemRepository purchaseItemRepository, UserRepository userRepository) {
        this.weightLessRepository = weightLessRepository;
        this.roleAuthorization = roleAuthorization;
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.weightLessItemRepository = weightLessItemRepository;
        this.purchaseItemRepository = purchaseItemRepository;
        this.userRepository = userRepository;
    }

    //Create Weight Less
    public WeightLess createWeightLess(WeightLessDto dto) {

        String userId = UserContext.getUserId();
        if (!roleAuthorization.hasCreateWeightLessPermission(userId)) {
            throw new SecurityException("User does not have the permission to create weightless record");
        }
//        if(purchaseRepository.existsById(dto.getPurchaseId())){
//            throw new RuntimeException("Weightless entry already exists");
//        }
        else {
            WeightLess weightLess = new WeightLess();

            weightLess.setReason(dto.getReason());
            weightLess.setCreated_date(LocalDate.now());
            weightLess.setCreated_time(LocalTime.now());
            weightLess.setUpdated_date(LocalDate.now());
            weightLess.setUpdated_time(LocalTime.now());

            if(dto.getPurchaseId() != null){
                Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                        .orElseThrow(() -> new RuntimeException("PUrcahse ID not found"));
                weightLess.setPurchase(purchase);
            }

            if(dto.getUserId() != null){
                User user = userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                weightLess.setUser(user);
            }

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

                    PurchaseItem purchaseItem = purchaseItemRepository.findById(purchaseItemId)
                            .orElseThrow(() -> new RuntimeException("Purchase item not found: " + purchaseItemId));

                    if(!purchaseItem.getPurchase().getId().equals(dto.getPurchaseId())) {
                        throw new RuntimeException("Purchase item ID " + purchaseItemId +
                                " does not belong to purchase ID " + dto.getPurchaseId());
                    }

                    item.setPurchaseItem(purchaseItem);
                    item.setWeightLess(weightLess);
                    weightLess.getWeightLessItem().add(item);
                }
            }

            return weightLessRepository.save(weightLess);
        }
    }

    //View all weight less
    public List<WeightLess> getAllWeightLess(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewWeightLessPermission(userId)){
            throw new SecurityException("User does not have the permission to view weightless.");
        }
        else{
            return weightLessRepository.findByDeletedFalse();
        }
    }

    //View weight less by id
    public Optional<WeightLess> getWeightLessById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewWeightLessPermission(userId)){
            throw new SecurityException("User does not have the permission to view weightless.");
        }
        else{
            return weightLessRepository.findById(id);
        }
    }

    //Update weight less
    public WeightLess updateWeightLess(Long id, WeightLessUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateWeightlessPermission(userId)){
            throw new SecurityException("User does not have the permission to update weight less");
        }
        //Check if Weight less entry exists
        return weightLessRepository.findById(id)
                .map(weightLess -> {
                    if (dto.getReason() != null) weightLess.setReason(dto.getReason());
                    weightLess.setUpdated_date(LocalDate.now());
                    weightLess.setUpdated_time(LocalTime.now());

                    if(dto.getPurchaseId() != null){
                        Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                                .orElseThrow(() -> new RuntimeException("PUrcahse ID not found"));
                        weightLess.setPurchase(purchase);
                    }

                    if(dto.getUserId() != null){
                        User user = userRepository.findById(dto.getUserId())
                                .orElseThrow(() -> new RuntimeException("User not found"));
                        weightLess.setUser(user);
                    }

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

                            PurchaseItem purchaseItem = purchaseItemRepository.findById(purchaseItemId)
                                    .orElseThrow(() -> new RuntimeException("Purchase item not found: " + purchaseItemId));

                            if(!purchaseItem.getPurchase().getId().equals(dto.getPurchaseId())) {
                                throw new RuntimeException("Purchase item ID " + purchaseItemId +
                                        " does not belong to purchase ID " + dto.getPurchaseId());
                            }

                            item.setPurchaseItem(purchaseItem);
                            item.setWeightLess(weightLess);
                            weightLess.getWeightLessItem().add(item);
                        }
                    }

                    return weightLessRepository.save(weightLess);

                })
                .orElseThrow(() -> new RuntimeException("Weightless record not found!"));
    }

    //Delete Weight less
    public WeightLess deleteWeightLess(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteWeightLessPermission(userId)){
            throw new SecurityException("User does not have the permission to delete weight less entry.");
        }
        return weightLessRepository.findById(id)
                .map(weightLess -> {
                    weightLess.setDeleted(true);
                    weightLess.setDeletedById(userId);
                    weightLess.setDeletedDate(LocalDate.now());
                    weightLess.setDeletedTime(LocalTime.now());

                    return weightLessRepository.save(weightLess);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    }
}