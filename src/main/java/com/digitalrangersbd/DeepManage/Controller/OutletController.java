package com.digitalrangersbd.DeepManage.Controller;
import com.digitalrangersbd.DeepManage.Dto.OutletDto;
import com.digitalrangersbd.DeepManage.Dto.OutletDto;
import com.digitalrangersbd.DeepManage.Dto.OutletUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Service.OutletService;
import com.digitalrangersbd.DeepManage.Service.OutletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/outlet")
public class OutletController {

    private final OutletService outletService;

    public OutletController(OutletService outletService) {
        this.outletService = outletService;
    }


    //Create new Outlet
    @PostMapping("/add")
    public ResponseEntity<Outlet> createOutlet(@Valid @RequestBody OutletDto dto){

        try {
            Outlet createdOutlet = outletService.createOutlet(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOutlet);
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

    //View outlet
    @GetMapping("/view")
    public ResponseEntity<List<Outlet>> getOutlet(){

        try{
            return ResponseEntity.ok(outletService.getOutlet());
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

    //View outlet by id
    @GetMapping("/view/{id}")
    public ResponseEntity<Outlet> getOutletById(@PathVariable Long id){

        try{
            return ResponseEntity.of(outletService.getOutletById(id));
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

    //Update outlet
    @PutMapping("/update/{id}")
    public ResponseEntity<Outlet> updateOutlet(@PathVariable Long id, @Valid @RequestBody OutletUpdateDto dto){
        try{
            Outlet updatedOutlet = outletService.updateOutlet(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedOutlet);
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

    //Delete Outlet
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOutlet(@PathVariable Long id){
        try{
            Outlet deletedOutlet = outletService.deleteOutlet(id);

            if(deletedOutlet != null ){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        }
        catch(SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
