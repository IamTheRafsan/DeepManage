package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Enum.ActiveStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public class OutletResponseDto {

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

    private long warehouse_id;

    @NotNull(message = "Status cannot be empty")
    private ActiveStatus status;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

    private boolean deleted;


}
