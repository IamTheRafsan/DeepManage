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
@RequestMapping("/weight-less")
public class WeightLessController {

    private final WeightLessService weightLessService;

    public WeightLessController(WeightLessService weightLessService) {
        this.weightLessService = weightLessService;
    }

    //create new weight less
    @PostMapping("/add")
    public ResponseEntity<WeightLess> createWeightLess(@Valid @RequestBody WeightLessDto dto){

        try{
            WeightLess weightLess = weightLessService.createWeightLess(dto) ;
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
    @GetMapping("/view")
    public ResponseEntity<List<WeightLess>> getAllWeightLess(){
        try{
            return ResponseEntity.ok(weightLessService.getAllWeightLess());
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
    @GetMapping("/view/{id}")
    public ResponseEntity<WeightLess> getWeightLessById(@PathVariable Long id){
        try{
            return ResponseEntity.of(weightLessService.getWeightLessById(id));
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
    @PutMapping("/update/{id}")
    public ResponseEntity<WeightLess> updateWeightLess(@PathVariable Long id, @Valid @RequestBody WeightLessUpdateDto dto){
        try {
            WeightLess updatedWeightLess = weightLessService.updateWeightLess(id, dto);
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWeigthLess(@PathVariable Long id){

        try{
            WeightLess deletedWeightLes = weightLessService.deleteWeightLess(id);
            if(deletedWeightLes != null)
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
