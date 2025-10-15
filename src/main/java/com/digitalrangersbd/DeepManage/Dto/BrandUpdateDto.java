package com.digitalrangersbd.DeepManage.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BrandUpdateDto {
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Brand code must be between 2 and 50 characters")
    private String code;

    //Default Constructor
    public BrandUpdateDto() {
    }

    public BrandUpdateDto(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
