package com.digitalrangersbd.DeepManage.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase")
@Setter
@Getter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String supplier;

    @Column
    private String purchasedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column
    private LocalDate purchaseDate;

    @JsonIgnore
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> purchaseItem = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    @Column(nullable = false)
    private LocalDate updated_date;

    @Column(nullable = false)
    private LocalTime updated_time;

    public Purchase(){}

    public Purchase(String supplier, String purchasedBy, Warehouse warehouse, String reference, LocalDate purchaseDate, List<PurchaseItem> purchaseItem)
    {
        this.supplier = supplier;
        this.warehouse = warehouse;
        this.purchasedBy = purchasedBy;
        this.reference = reference;
        this.purchaseDate = purchaseDate;
        this.purchaseItem = purchaseItem;

    }

}
