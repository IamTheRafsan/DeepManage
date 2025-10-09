package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Dto.UserDto;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    //Create a user in the database
    public User addUser(UserDto dto){

        User user = new User(
                dto.getUser_id(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRole_id(),
                dto.getRole_name(),
                dto.getGender(),
                dto.getMobile(),
                dto.getCountry(),
                dto.getCity(),
                dto.getAddress(),
                dto.getWarehouse_name(),
                dto.getWarehouse_id()
        );
        user.setCreated_date(LocalDate.now());
        user.setCreated_time(LocalTime.now());
        user.setUpdated_date(LocalDate.now());
        user.setUpdated_time(LocalTime.now());

        return userRepository.save(user);
    }



}
