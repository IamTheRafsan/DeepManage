package com.digitalrangersbd.DeepManage.Dto;


import com.digitalrangersbd.DeepManage.Enum.ActiveStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class WarehouseDto {

    private Long id;

    @NotNull(message = "Name cannot be blank")
    @Size(min = 2, max = 200, message = "Warehouse name should be more than 2 letters and less than 200")
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Number mobile;

    private String country;

    private String city;

    @NotNull(message = "Warehouse must have and area")
    private String area;

    @NotNull(message = "Status cannot be empty")
    private ActiveStatus status;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

    public WarehouseDto(){}

    public WarehouseDto(String name, String email, Number mobile, String country, String city, String area, ActiveStatus status){
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.country = country;
        this.city = city;
        this.area = area;
        this.status = status;
    }
}
