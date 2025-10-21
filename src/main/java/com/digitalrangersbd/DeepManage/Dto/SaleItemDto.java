package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.Purchase;
import com.digitalrangersbd.DeepManage.Entity.Sale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SaleItemDto {

    private Long id;

    private Sale sale;

    private Product product;

    private double price;

    private float quantity;

    public SaleItemDto() {}

    public SaleItemDto( Product product, Integer quantity, Double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
