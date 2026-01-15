package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.WeightLessUpdateDto;
import com.digitalrangersbd.DeepManage.Dto.WeightWastageDto;
import com.digitalrangersbd.DeepManage.Dto.WeightWastageUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.WeightLess;
import com.digitalrangersbd.DeepManage.Entity.WeightWastage;
import com.digitalrangersbd.DeepManage.Service.WeightWastageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/weight-wastage")
public class WeightWastageController {

    private final WeightWastageService weightWastageService;

    public WeightWastageController(WeightWastageService weightWastageService) {
        this.weightWastageService = weightWastageService;
    }

    //Create new weight wastage controller
    @PostMapping("/add")
    public ResponseEntity<WeightWastage> createWeightWastage(@Valid @RequestBody WeightWastageDto dto){

        try{
            WeightWastage weightWastage = weightWastageService.createWeightWastage(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(weightWastage);
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //View weight wastage
    @GetMapping("/view")
    public ResponseEntity<List<WeightWastage>> getAllWeightWastage(){
        try{
            return ResponseEntity.ok(weightWastageService.getAllWeightWastage());
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //View weight wastage by id
    @GetMapping("/view/{id}")
    public ResponseEntity<WeightWastage> getWeightWastageById(@PathVariable Long id){
        try{
            return ResponseEntity.of(weightWastageService.getWeightWastageById(id));
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Upate weight wastage
    @PutMapping("/update/{id}")
    public ResponseEntity<WeightWastage> updateWeightWastage(@PathVariable Long id, @Valid @RequestBody WeightWastageUpdateDto dto){
        try {
            WeightWastage updatedWeightWastage = weightWastageService.updateWeightWastage(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedWeightWastage);
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delele Weight wastage entry
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWeigthWastage(@PathVariable Long id){

        try{
            WeightWastage deletedWeightWastage = weightWastageService.deleteWeightWastage(id);
            if(deletedWeightWastage != null)
            {
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}