package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.WeightLessDto;
import com.digitalrangersbd.DeepManage.Dto.WeightLessUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Entity.WeightLess;
import com.digitalrangersbd.DeepManage.Repository.ProductRepository;
import com.digitalrangersbd.DeepManage.Repository.PurchaseRepository;
import com.digitalrangersbd.DeepManage.Repository.WeightLessRepository;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WeightLessService {

    private final WeightLessRepository weightLessRepository;
    private final RoleAuthorization roleAuthorization;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    public WeightLessService(WeightLessRepository weightLessRepository, RoleAuthorization roleAuthorization, PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        this.weightLessRepository = weightLessRepository;
        this.roleAuthorization = roleAuthorization;
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    //Create Weight Less
    public WeightLess createWeightLess(String roleId, WeightLessDto dto){

        if(!roleAuthorization.hasCreateWeightLessPermission(roleId))
        {
            throw new SecurityException("User do not have the permission to create Weight Less product");
        }
        //Purchase id check in database
        Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                .orElseThrow(() -> new RuntimeException("Purchase Id not found"));

        //Product Id check in database
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Id not found"));


        //Else just add the new weightless record
        WeightLess weightLess =  new WeightLess();
        weightLess.setPurchaseId(purchase);
        weightLess.setProductId(product);
        weightLess.setWeightLess(dto.getWeightLess());

        weightLess.setCreated_date(LocalDate.now());
        weightLess.setCreated_time(LocalTime.now());
        weightLess.setUpdated_date(LocalDate.now());
        weightLess.setUpdated_time(LocalTime.now());

        return weightLessRepository.save(weightLess);
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
        WeightLess weightLess = weightLessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Weight Less does not exists"));

        //Purchase id check in database
        if(dto.getPurchaseId() != null){
            Purchase purchase = purchaseRepository.findById(dto.getPurchaseId())
                    .orElseThrow(() -> new RuntimeException("Purchase Id not found"));

            weightLess.setPurchaseId(purchase);
        }

        //Product Id check in database
        if(dto.getProductId() != null){
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product Id not found"));
            weightLess.setProductId(product);
        }

        if(dto.getWeightLess() != null){

            weightLess.setWeightLess(dto.getWeightLess());
        }

        return weightLessRepository.save(weightLess);
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