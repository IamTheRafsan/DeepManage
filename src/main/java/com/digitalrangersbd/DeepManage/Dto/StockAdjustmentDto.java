package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Outlet;
import com.digitalrangersbd.DeepManage.Entity.StockAdjustItem;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.Enum.StockAdjustmentStatus;
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
public class StockAdjustmentDto {

    private Long id;

    @NotNull
    private String reference;

    private Warehouse warehouse;

    private Outlet outlet;

    @NotNull(message = "Need to add atleast one product")
    private List<StockAdjustItem> stockItems =  new ArrayList<>();

    @NotNull(message = "Adjusted by not found")
    private User adjustedBy;

    @NotNull(message = "Reason of stock adjustment missing.")
    private String reason;

    @NotNull(message = "Put the adjustment type.")
    private StockAdjustmentStatus adjustmentType;

    private LocalDate createdDate;

    private LocalTime createdTime;

    private LocalDate updatedDate;

    private LocalTime updatedTime;
}
