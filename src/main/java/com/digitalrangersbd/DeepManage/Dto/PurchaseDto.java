package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.PurchaseItem;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
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
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

    private Long id;

    private String supplier;

    private String purchasedBy;

    @NotNull(message = "Warehouse name not found.")
    private Long warehouse;

    @NotNull(message = "Please ")
    private String reference;

    private LocalDate purchaseDate;

    private Long paymentType;

    @NotNull(message = "No products selected")
    private List<PurchaseItem> purchaseItem = new ArrayList<>();

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;


}
