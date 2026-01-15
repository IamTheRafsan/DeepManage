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
    @PostMapping("/add")
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody BrandDto dto){

        try{
            Brand createdBrand = brandService.createBrand(dto);
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
    @GetMapping("/view")
    public ResponseEntity<List<Brand>> getBrand(){

        try{
            return ResponseEntity.ok(brandService.getBrand());
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View brand by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id){

        try{
            return ResponseEntity.of(brandService.getBrandById(id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update brand
    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandUpdateDto dto){

        try{
            Brand updatedBrand = brandService.updateBrand(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedBrand);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete Brand
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id){
        try{
            Brand deletedBrand = brandService.deleteBrand(id);

            if(deletedBrand != null){
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