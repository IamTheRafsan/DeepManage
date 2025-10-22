package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.StockAdjustment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class StockAdjustItemDto {

    private Long id;

    @NotNull(message = "Product not found")
    private Product product;

    @NotNull
    private StockAdjustment stockAdjustment;

    private Float adjustQuantity;

}
