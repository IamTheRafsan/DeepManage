package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.ExpenseCategory;
import com.digitalrangersbd.DeepManage.Entity.PaymentType;
import com.digitalrangersbd.DeepManage.Entity.User;
import com.digitalrangersbd.DeepManage.Entity.Warehouse;
import com.digitalrangersbd.DeepManage.Enum.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class ExpenseDto {
    private Long id;

    @NotNull
    private String name;

    private Long category;

    @NotNull
    private PaymentStatus status;

    private Long paymentType;

    private Double amount;

    private String description;

    private Long warehouseId;

    private Long outletId;

    private String userId;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;
}
