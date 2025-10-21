package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.ProductDto;
import com.digitalrangersbd.DeepManage.Dto.ProductUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Brand;
import com.digitalrangersbd.DeepManage.Entity.Category;
import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Repository.BrandRepository;
import com.digitalrangersbd.DeepManage.Repository.CategoryRepository;
import com.digitalrangersbd.DeepManage.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RoleAuthorization roleAuthorization;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, RoleAuthorization roleAuthorization, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.roleAuthorization = roleAuthorization;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    //Create new product
    public Product createProduct(String roleId, ProductDto dto){

        if(!roleAuthorization.hasCreateProductPermission(roleId)){
            throw new SecurityException("User do not have the permission to create product");
        }
        if (productRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("The Product code already exits:"+ dto.getCode());
        }
        if (dto.getBrandId() != null && !brandRepository.existsById(dto.getBrandId())) {
            throw new RuntimeException("Brand does not exist");
        }
        if (!categoryRepository.existsById(dto.getCategoryId())) {
            throw new RuntimeException("Category does not exits");
        }
        else{

            Product product = new Product();
            product.setName(dto.getName());
            product.setCode(dto.getCode());
            product.setDescription(dto.getDescription());
            product.setStatus(dto.getStatus());
            product.setPrice(dto.getPrice());

            if(dto.getBrandId() != null){
                Brand brand = brandRepository.findById(dto.getBrandId())
                        .orElseThrow(() -> new RuntimeException("Brand not found"));
                product.setBrand(brand);
            }

            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);

            product.setCreated_date(LocalDate.now());
            product.setCreated_time(LocalTime.now());
            product.setUpdated_date(LocalDate.now());
            product.setUpdated_time(LocalTime.now());

            return productRepository.save(product);
        }
    }

    //Get All Products
    public List<Product> getAllProduct(String roleId){
        if(!roleAuthorization.hasViewProductPermission(roleId)){
            throw new SecurityException("User does not have permission to view products.");
        }
        else {
            return productRepository.findAll();
        }
    }

    //Get products by id
    public Optional<Product> getProductById(String roleId, Long id){
        if(!roleAuthorization.hasViewProductPermission(roleId)){
            throw new SecurityException("User does not have permission to view products.");
        }
        else {
            return productRepository.findById(id);
        }
    }

    //Update product
    public Product updateProduct(String roleId, Long id, ProductUpdateDto dto){
        if(!roleAuthorization.hasUpdateProductPermission(roleId)){
            throw new SecurityException("User does not have the permission to update product.");
        }
        else{
            return productRepository.findById(id)
                    .map(product ->{
                        if(dto.getName() != null) product.setName(dto.getName());

                        //check if product code already exists before updating
                        if(dto.getCode() != null && productRepository.existsByCode(dto.getCode())) {
                            throw new RuntimeException("The Product code already exits:" + dto.getCode());
                        }else{
                            product.setCode(dto.getCode());
                        }

                        //check if brand exists or not before updating
                        if(dto.getBrandId() != null){
                            Brand brand = brandRepository.findById(dto.getBrandId())
                                    .orElseThrow(() -> new RuntimeException("Brand not found"));
                            product.setBrand(brand);
                        }

                        //check if category exists or not before updating
                        if(dto.getCategoryId() != null){
                            Category category = categoryRepository.findById(dto.getCategoryId())
                                    .orElseThrow(() -> new RuntimeException("Category not found"));
                            product.setCategory(category);
                        }

                        if(dto.getDescription() != null) product.setDescription(dto.getDescription());
                        if(dto.getStatus() != null) product.setStatus(dto.getStatus());
                        if(dto.getPrice() != null) product.setPrice(dto.getPrice());

                        return productRepository.save(product);

                    })
                    .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        }
    }

    //Delete Product
    public boolean deleteProduct(String roleId, Long id){

        if(!roleAuthorization.hasDeleteProductPermission(roleId)){
            throw new SecurityException("User does not have the permission to delete product.");
        }
        else{
            if(productRepository.existsById(id)){
                productRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}