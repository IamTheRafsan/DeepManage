package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.Role;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String user_id;

    @NotNull(message = "First name not found")
    @Size(min=2, max=100, message = "Fist name must be minimum 2 words and maximum 100 words")
    private String firstName;

    @NotNull(message = "Last name not found")
    @Size(min=2, max=100, message = "Fist name must be minimum 2 words and maximum 100 words")
    private String lastName;

    @NotNull(message = "Email not found")
    @Email
    private String email;

    @NotNull(message = "Password not found.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least 8 characters, one uppercase, one lowercase, and one number")
    private String password;

    @NotNull
    private Gender gender;

    private Integer mobile;

    private String country;

    private String city;

    private String address;

    @NotNull(message = "Role not found")
    private Set<String> role_id = new HashSet<>();

    private Set<Long> warehouse = new HashSet<>();

    private Set<Long> outlet = new HashSet<>();

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

}