package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.SaleItem;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class SaleUpdateDto {

    private Long id;

    @NotNull(message = "Reference cannot be empty")
    private String reference;

    private String soldBy;

    private LocalDate saleDate;

    @NotNull(message = "Outlet cannot be empty")
    private Outlet outlet;

    @NotNull(message = "Select products")
    private List<SaleItem> saleItem = new ArrayList<>();

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

    public SaleUpdateDto(){}

    public SaleUpdateDto(String reference, String soldBy, LocalDate saleDate, Outlet outlet, List<SaleItem> saleItem){
        this.reference = reference;
        this.soldBy = soldBy;
        this.saleDate = saleDate;
        this.outlet = outlet;
        this.saleItem = saleItem;

    }
}
