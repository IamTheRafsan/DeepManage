package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.UserDto;
import com.digitalrangersbd.DeepManage.Dto.UserUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //Create a new user
    @PostMapping("/add/{userId}")
    public ResponseEntity<?> createUser(@PathVariable String userId,@Valid @RequestBody UserDto dto){

        try{
            User createUser = userService.createUser(userId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Get all users
    @GetMapping("/view/{userId}")
    public ResponseEntity<List<User>> getAllUser(@PathVariable String userId){
        try {
            return ResponseEntity.ok(userService.getAllUser(userId));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    //Get users by id
    @GetMapping("/view/{userId}/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String userId,@PathVariable String id){
        try {
            return ResponseEntity.of(userService.getUserById(userId,id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update User
    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @PathVariable String id, @Valid @RequestBody UserUpdateDto dto){

        try{
            User updatedUser = userService.updateUser(userId, id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //Delete User
    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId, @PathVariable String id){

        try{
            boolean deletedUser = userService.deleteUser(userId, id);
            if(deletedUser){
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