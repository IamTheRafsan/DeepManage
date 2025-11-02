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
    @PostMapping("/add/{userId}")
    public ResponseEntity<Outlet> createOutlet(@PathVariable String userId, @Valid @RequestBody OutletDto dto){

        try {
            Outlet createdOutlet = outletService.createOutlet(userId, dto);
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
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<Outlet>> getOutlet(@PathVariable String userId){

        try{
            return ResponseEntity.ok(outletService.getOutlet(userId));
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
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<Outlet> getOutletById(@PathVariable String userId, @PathVariable Long id){

        try{
            return ResponseEntity.of(outletService.getOutletById(userId, id));
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

    //Update outlet date
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Outlet> updateOutlet(@PathVariable String userId, @PathVariable Long id, @Valid @RequestBody OutletUpdateDto dto){
        try{
            Outlet updatedOutlet = outletService.updateOutlet(userId, id, dto);
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
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Void> deleteOutlet(@PathVariable String userId, @PathVariable Long id){
        try{
            boolean deletedOutlet = outletService.deleteOutlet(userId, id);

            if(deletedOutlet){
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
