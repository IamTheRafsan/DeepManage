package com.digitalrangersbd.DeepManage.Controller;

import com.digitalrangersbd.DeepManage.Dto.UserDataResponseDto;
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
    @PostMapping("/add")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto dto){

        try{
            User createUser = userService.createUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Get all users
    @GetMapping("/view")
    public ResponseEntity<List<UserDataResponseDto>> getAllUser() {
        try {
            return ResponseEntity.ok(userService.getAllUser());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Get users by id
    @GetMapping("/view/{id}")
    public ResponseEntity<UserDataResponseDto> getUserById(@PathVariable String id){
        try {
            return ResponseEntity.of(userService.getUserById(id));
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateDto dto){

        try{
            User updatedUser = userService.updateUser(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){

        try{
            User deletedUser = userService.deleteUser(id);

            if (deletedUser != null) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}