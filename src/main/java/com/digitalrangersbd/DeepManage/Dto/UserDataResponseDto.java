package com.digitalrangersbd.DeepManage.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserDataResponseDto {
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
    private boolean deleted;
    private LocalDate created_date;
    private LocalTime created_time;
    private LocalDate updated_date;
    private LocalTime updated_time;


}