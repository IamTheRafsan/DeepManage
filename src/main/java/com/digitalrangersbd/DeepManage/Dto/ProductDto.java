package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Enum.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.spi.ManagedEntity;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotNull(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Product code is required")
    @Size(min = 2, max = 50, message = "Product code must be between 2 and 50 characters")
    private String code;

    private Long brandId;

    @NotNull
    private Long categoryId;

    @Size(max = 500, message = "Description can’t be longer than 500 characters")
    private String description;

    @NotNull(message = "Status is required")
    private ProductStatus status;

    @NotNull(message = "Price is required.")
    private Double price;

    @NotNull(message = "Quantity is required.")
    private Float stock;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

}
