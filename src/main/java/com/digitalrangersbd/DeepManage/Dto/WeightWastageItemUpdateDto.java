package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.PurchaseItem;
import com.digitalrangersbd.DeepManage.Entity.WeightLess;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeightWastageItemUpdateDto {

    @NotNull
    private Long id;

    private Float quantity;
}
