package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.Enum.ActiveStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutletDto {

    private Long id;

    @NotNull(message = "Name cannot be blank")
    @Size(min = 2, max = 200, message = "Outlet name should be more than 2 letters and less than 200")
    private String name;

    @NotNull
    @Email
    private String email;

    //@NotNull
    private Number mobile;

    private String country;

    private String city;

    @NotNull(message = "Outlet must have and area")
    private String area;

    @NotNull(message = "Outlet cannot be empty")
    private ActiveStatus status;

    private Long warehouse_id;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;


}
