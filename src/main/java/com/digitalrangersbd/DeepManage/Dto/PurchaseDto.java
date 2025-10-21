package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.PurchaseItem;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PurchaseDto {

    private Long id;

    private String supplier;

    private String purchasedBy;

    @NotNull(message = "Warehouse name not found.")
    private Warehouse warehouse;

    @NotNull(message = "Please ")
    private String reference;

    private LocalDate purchaseDate;

    @NotNull(message = "No products selected")
    private List<PurchaseItem> purchaseItem = new ArrayList<>();

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

    public PurchaseDto(){}

    public PurchaseDto(String supplier, String purchasedBy, Warehouse warehouse, String reference, LocalDate purchaseDate, List<PurchaseItem> purchaseItem)
    {
        this.supplier = supplier;
        this.purchasedBy = purchasedBy;
        this.warehouse = warehouse;
        this.reference = reference;
        this.purchaseDate = purchaseDate;
        this.purchaseItem = purchaseItem;

    }


}
