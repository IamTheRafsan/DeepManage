package com.digitalrangersbd.DeepManage.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sale")
@Setter
@Getter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column
    private String soldBy;

    @Column
    private LocalDate saleDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outlet_id")
    private Outlet outlet;

    @JsonIgnore
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItem = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    @Column(nullable = false)
    private LocalDate updated_date;

    @Column(nullable = false)
    private LocalTime updated_time;

    public Sale(){}

    public Sale(String reference, String soldBy, LocalDate saleDate, Outlet outlet, List<SaleItem> saleItem){
        this.reference = reference;
        this.soldBy = soldBy;
        this.saleDate = saleDate;
        this.outlet = outlet;
        this.saleItem = saleItem;

    }
}
