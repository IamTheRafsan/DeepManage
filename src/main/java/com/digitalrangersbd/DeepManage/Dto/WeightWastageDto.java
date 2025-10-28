package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.WeightLessItem;
import com.digitalrangersbd.DeepManage.Entity.WeightWastageItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeightWastageDto {

    private Long id;

    @NotNull(message = "Select atleast one product")
    private List<WeightWastageItem> weightWastageItem;

    @NotNull
    private Long purchaseId;

    private String reason;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;
}
