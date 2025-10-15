package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Enum.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
public class ProductDto {

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Product code is required")
    @Size(min = 2, max = 50, message = "Product code must be between 2 and 50 characters")
    private String code;

    private Long brandId;

    private Long categoryId;

    @Size(max = 500, message = "Description can’t be longer than 500 characters")
    private String description;

    @NotNull(message = "Status is required")
    private ProductStatus status;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;


    public ProductDto(){}

    public ProductDto(String name, String code, Long brandId, Long categoryId, String description, ProductStatus status){
        this.name = name;
        this.code = code;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.description = description;
        this.status = status;
    }
}
