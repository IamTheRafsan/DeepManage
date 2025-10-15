package com.digitalrangersbd.DeepManage.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Setter
@Getter
public class CategoryUpdateDto {

    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Category code must be between 2 and 50 characters")
    private String code;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

    //Default Constructor
    public CategoryUpdateDto() {}

    public CategoryUpdateDto(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

