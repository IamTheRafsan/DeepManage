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
    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto dto){

        try{
            Product createdProduct = productService.createProduct(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Get all products
    @GetMapping("/view")
    public ResponseEntity<List<Product>> getAllProduct(){
        try {
            return ResponseEntity.ok(productService.getAllProduct());
        }
        catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //Get Products by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try{
            return ResponseEntity.of(productService.getProductById(id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update Product
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto dto){

        try{
            Product updatedProduct = productService.updateProduct(id, dto);
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        try{
            Product deletedProduct = productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
