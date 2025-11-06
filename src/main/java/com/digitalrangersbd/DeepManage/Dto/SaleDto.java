package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.SaleItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {

    private Long id;

    @NotNull(message = "Reference cannot be empty")
    private String reference;

    private String customer;

    private String soldBy;

    private LocalDate saleDate;

    @NotNull(message = "Outlet cannot be empty")
    private Long outlet;

    private Long paymentType;

    @NotNull(message = "Select products")
    private List<SaleItem> saleItem = new ArrayList<>();

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

}
