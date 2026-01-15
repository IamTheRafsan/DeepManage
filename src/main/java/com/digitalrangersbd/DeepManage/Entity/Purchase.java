package com.digitalrangersbd.DeepManage.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier")
    private User supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchasedBy")
    private User purchasedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column
    private LocalDate purchaseDate;


    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> purchaseItem = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @JsonIgnore
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeightWastage> purchase = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    private String created_by_id;

    @Column(nullable = false)
    private LocalDate updated_date;

    @Column(nullable = false)
    private LocalTime updated_time;

    private String updated_by_id;

    private boolean deleted = false;

    private String deletedById;

    private String deletedByName;

    private LocalDate deletedDate;

    private LocalTime deletedTime;

}
