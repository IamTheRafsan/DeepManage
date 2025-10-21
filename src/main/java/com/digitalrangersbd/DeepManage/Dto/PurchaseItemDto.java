package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseItemDto {

    private Long id;

    private Purchase purchase;

    private Product product;

    private double price;

    private float quantity;

    public PurchaseItemDto() {}

    public PurchaseItemDto( Product product, Integer quantity, Double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
