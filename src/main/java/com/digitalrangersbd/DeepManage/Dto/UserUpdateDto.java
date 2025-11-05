package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Enum.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private String user_id;

    @Size(min=2, max=100, message = "Fist name must be minimum 2 words and maximum 100 words")
    private String firstName;

    @Size(min=2, max=100, message = "Fist name must be minimum 2 words and maximum 100 words")
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least 8 characters, one uppercase, one lowercase, and one number")
    private String password;

    private Gender gender;

    private Integer mobile;

    private String country;

    private String city;

    private String address;

    private Set<String> role_id = new HashSet<>();

    private Set<Long> warehouse = new HashSet<>();

    private Set<Long> outlet = new HashSet<>();

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;


}
