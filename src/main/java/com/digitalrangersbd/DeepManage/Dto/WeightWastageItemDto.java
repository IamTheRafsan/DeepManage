package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Purchase;
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
public class WeightWastageItemDto {

    private Long id;

    //@NotNull(message = "Weight less value is required")
    private WeightLess weightLess;

    @NotNull(message = "Select atleast one purchase product")
    private PurchaseItem purchaseItem;

    private Float quantity;

}
