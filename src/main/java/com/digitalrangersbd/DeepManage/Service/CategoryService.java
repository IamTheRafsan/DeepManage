package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.CategoryDto;
import com.digitalrangersbd.DeepManage.Dto.CategoryUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Category;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final RoleAuthorization roleAuthorization;

    public CategoryService(CategoryRepository categoryRepository, RoleAuthorization roleAuthorization) {
        this.categoryRepository = categoryRepository;
        this.roleAuthorization = roleAuthorization;
    }

    //Add new category
    public Category createCategory(CategoryDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateCategoryPermission(userId)){
            throw new SecurityException("User does not have permission to create Category");
        }
        else{

            if(categoryRepository.existsByCode(dto.getCode())){
                throw new RuntimeException("Category code already exists: " + dto.getCode());
            } else if (categoryRepository.existsByName(dto.getName())) {
                throw new RuntimeException("Category name already exists: " + dto.getName());
            }
            else {
                Category category = new Category();
                category.setName(dto.getName());
                category.setCode(dto.getCode());

                category.setCreated_date(LocalDate.now());
                category.setCreated_time(LocalTime.now());
                category.setUpdated_date(LocalDate.now());
                category.setUpdated_time(LocalTime.now());

                return categoryRepository.save(category);
            }

        }
    }

    //Get category
    public List<Category> getCategory(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewCategoryPermission(userId)){
            throw new SecurityException("User does not have permission to view category");
        }
        else {
            return categoryRepository.findAll();
        }
    }

    //Get category by id
    public Optional<Category> getCategoryById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewCategoryPermission(userId)){
            throw new SecurityException("User does not have permission to view category");
        }
        else {
            return categoryRepository.findById(id);
        }
    }


    //Update Category
    public Category updateCategory(Long id, CategoryUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateCategoryPermission(userId)){
            throw new SecurityException("User does not have permission to update category");
        }
        else{
            return categoryRepository.findById(id)
                    .map(category -> {
                        if(dto.getCode() != null){
                            if(categoryRepository.existsByCode(dto.getCode())){
                                throw new RuntimeException("Category code already exists: " + dto.getCode());
                            }
                            else category.setCode(dto.getCode());
                        }
                        if(dto.getName() != null){
                            if(categoryRepository.existsByName(dto.getName())){
                                throw new RuntimeException("Category code already exists: " + dto.getCode());
                            }
                            else category.setName(dto.getName());
                        }

                        category.setUpdated_time(LocalTime.now());
                        category.setUpdated_date(LocalDate.now());

                        return categoryRepository.save(category);
                    })
                    .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        }
    }

    //Delete Category
    public boolean deleteCategory(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteCategoryPermission(userId)){
            throw new SecurityException("User does not have permission to delete category");
        }
        else {
            if(categoryRepository.existsById(id)){
                categoryRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
