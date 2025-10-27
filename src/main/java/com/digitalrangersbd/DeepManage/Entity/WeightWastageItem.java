package com.digitalrangersbd.DeepManage.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weight_wastage_item")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeightWastageItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @JoinColumn(name = "product_id")
    private  Product product;

    private Float quantity;

}
