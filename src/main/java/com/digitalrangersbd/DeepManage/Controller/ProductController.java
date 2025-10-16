package com.digitalrangersbd.DeepManage.Controller;


import com.digitalrangersbd.DeepManage.Dto.ProductDto;
import com.digitalrangersbd.DeepManage.Dto.ProductUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Create new product
    @PostMapping("/add/{roleId}")
    public ResponseEntity<Product> createProduct(@PathVariable String roleId, @Valid @RequestBody ProductDto dto){

        try{
            Product createdProduct = productService.createProduct(roleId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Get all products
    @GetMapping("/view/{roleId}")
    public ResponseEntity<List<Product>> getAllProduct(@PathVariable String roleId){
        try {
            return ResponseEntity.ok(productService.getAllProduct(roleId));
        }
        catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //Get Products by id
    @GetMapping("/view/{roleId}/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String roleId, @PathVariable Long id){
        try{
            return ResponseEntity.of(productService.getProductById(roleId, id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update Product
    @PutMapping("/update/{roleId}/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String roleId, @PathVariable Long id, @RequestBody ProductUpdateDto dto){

        try{
            Product updatedProduct = productService.updateProduct(roleId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Delete Product
    @DeleteMapping("/delete/{roleId}/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String roleId, @PathVariable Long id){

        try{
            boolean deletedProduct = productService.deleteProduct(roleId,id);
            return ResponseEntity.noContent().build();
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
