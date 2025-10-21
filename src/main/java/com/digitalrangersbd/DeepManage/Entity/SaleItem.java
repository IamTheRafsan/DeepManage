package com.digitalrangersbd.DeepManage.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sale_item")
@Setter
@Getter
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double price;

    private float quantity;

    public SaleItem() {}

    public SaleItem(Product product, Integer quantity, Double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
