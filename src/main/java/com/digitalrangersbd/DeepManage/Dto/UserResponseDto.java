package com.digitalrangersbd.DeepManage.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponseDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String mobile;
    private String country;
    private String city;
    private String address;
    private List<String> roles;

}