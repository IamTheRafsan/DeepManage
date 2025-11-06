package com.digitalrangersbd.DeepManage.Dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {

    private Long id;

    @NotNull(message = "Brand name is required")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Brand code is required")
    @Size(min = 2, max = 50, message = "Brand code must be between 2 and 50 characters")
    private String code;

}
