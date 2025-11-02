package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.BrandDto;
import com.digitalrangersbd.DeepManage.Dto.BrandUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Brand;
import com.digitalrangersbd.DeepManage.Service.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //Create new brand
    @PostMapping("/add/{userId}")
    public ResponseEntity<Brand> createBrand(@PathVariable String userId, @Valid @RequestBody BrandDto dto){

        try{
            Brand createdBrand = brandService.createBrand(userId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //View all brand
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<Brand>> getBrand(@PathVariable String userId){

        try{
            return ResponseEntity.ok(brandService.getBrand(userId));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View brand by id
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable String userId, @PathVariable Long id){

        try{
            return ResponseEntity.of(brandService.getBrandById(userId,id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update brand
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable String userId, @PathVariable Long id, @Valid @RequestBody BrandUpdateDto dto){

        try{
            Brand updatedBrand = brandService.updateBrand(userId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedBrand);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Brand
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String userId, @PathVariable Long id){
        try{
            boolean deletedBrand = brandService.deleteBrand(userId, id);

            if(deletedBrand){
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
