package com.digitalrangersbd.DeepManage.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PaymentTypeDto {

    private Long id;

    @NotNull(message = "Payment type name cannot be empty")
    @Size(min = 2, max = 100, message = "Payment type name must be between 2 and 100 characters")
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

}
