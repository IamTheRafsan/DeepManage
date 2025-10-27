package com.digitalrangersbd.DeepManage.Entity;

import com.mysql.cj.xdevapi.WarningImpl;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weight_less_item")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeightLessItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weight_less_id")
    private WeightLess weightLess;

    @ManyToOne
    @JoinColumn(name = "purchase_item_id")
    private PurchaseItem purchaseItem;

    private Float quantity;


}
