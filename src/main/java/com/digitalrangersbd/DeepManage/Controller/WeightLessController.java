package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.WeightLessDto;
import com.digitalrangersbd.DeepManage.Dto.WeightLessUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.WeightLess;
import com.digitalrangersbd.DeepManage.Service.WeightLessService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/weight_less")
public class WeightLessController {

    private final WeightLessService weightLessService;

    public WeightLessController(WeightLessService weightLessService) {
        this.weightLessService = weightLessService;
    }

    //create new weight less
    @PostMapping("/add/{userId}")
    public ResponseEntity<WeightLess> createWeightLess(@PathVariable String userId, @Valid @RequestBody WeightLessDto dto){

        try{
            WeightLess weightLess = weightLessService.createWeightLess(userId, dto) ;
            return ResponseEntity.status(HttpStatus.CREATED).body(weightLess);
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

    //View weight less
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<WeightLess>> getAllWeightLess(@PathVariable String userId){
        try{
            return ResponseEntity.ok(weightLessService.getAllWeightLess(userId));
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

    //View weight less by id
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<WeightLess> getWeightLessById(@PathVariable String userId, @PathVariable Long id){
        try{
            return ResponseEntity.of(weightLessService.getWeightLessById(userId, id));
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

    //Upate weight less
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<WeightLess> updateWeightLess(@PathVariable String userId, @PathVariable Long id, @Valid @RequestBody WeightLessUpdateDto dto){
        try {
            WeightLess updatedWeightLess = weightLessService.updateWeightLess(userId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedWeightLess);
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

    //Delele Weight less entry
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Void> deleteWeigthLess(@PathVariable String userId, @PathVariable Long id){

        try{
            Boolean deletedWeightLes = weightLessService.deleteWeightLess(userId, id);
            if(deletedWeightLes)
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
