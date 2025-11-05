package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.BrandDto;
import com.digitalrangersbd.DeepManage.Dto.BrandUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Brand;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final RoleAuthorization roleAuthorization;

    public BrandService(BrandRepository brandRepository, RoleAuthorization roleAuthorization) {
        this.brandRepository = brandRepository;
        this.roleAuthorization = roleAuthorization;
    }

    //Add new brand
    public Brand createBrand(BrandDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateBrandPermission(userId)){
            throw new SecurityException("User does not have permission to create Brand");
        }
        else{

            if(brandRepository.existsByCode(dto.getCode())){
                throw new RuntimeException("Brand code already exists: " + dto.getCode());
            } else if (brandRepository.existsByName(dto.getName())) {
                throw new RuntimeException("Brand name already exists: " + dto.getName());
            }
            else {
                Brand brand = new Brand();
                brand.setCode(dto.getCode());
                brand.setName(dto.getName());

                brand.setCreated_date(LocalDate.now());
                brand.setCreated_time(LocalTime.now());
                brand.setUpdated_date(LocalDate.now());
                brand.setUpdated_time(LocalTime.now());

                return brandRepository.save(brand);
            }

        }
    }

    //Get brand
    public List<Brand> getBrand(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewBrandPermission(userId)){
            throw new SecurityException("User does not have permission to view brand");
        }
        else {
            return brandRepository.findAll();
        }
    }

    //Get brand by id
    public Optional<Brand> getBrandById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewBrandPermission(userId)){
            throw new SecurityException("User does not have permission to view Brand");
        }
        else {
            return brandRepository.findById(id);
        }
    }


    //Update Brand
    public Brand updateBrand(Long id, BrandUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateBrandPermission(userId)){
            throw new SecurityException("User does not have permission to update Brand");
        }
        else{
            return brandRepository.findById(id)
                    .map(brand -> {
                        if(dto.getCode() != null){
                            if(brandRepository.existsByCode(dto.getCode())){
                                throw new RuntimeException("Brand code already exists: " + dto.getCode());
                            }
                            else brand.setCode(dto.getCode());
                        }
                        if(dto.getName() != null){
                            if(brandRepository.existsByName(dto.getName())){
                                throw new RuntimeException("Brand code already exists: " + dto.getCode());
                            }
                            else brand.setName(dto.getName());
                        }

                        brand.setUpdated_time(LocalTime.now());
                        brand.setUpdated_date(LocalDate.now());

                        return brandRepository.save(brand);
                    })
                    .orElseThrow(() -> new RuntimeException("Brand not found with id " + id));
        }
    }

    //Delete Brand
    public boolean deleteBrand(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteBrandPermission(userId)){
            throw new SecurityException("User does not have permission to delete brand");
        }
        else {
            if(brandRepository.existsById(id)){
                brandRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
