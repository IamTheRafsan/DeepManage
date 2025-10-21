package com.digitalrangersbd.DeepManage.Entity;

import com.digitalrangersbd.DeepManage.Enum.StockAdjustmentStatus;
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
@Table(name = "stock_adjustment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outlet_id")
    private Outlet outlet;

    @OneToMany(mappedBy = "stockAdjustment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockAdjustItem> stockItems =  new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adjusted_by")
    private User adjustedBy;

    @Column
    private String reason;

    @Enumerated(EnumType.STRING)
    private StockAdjustmentStatus adjustmentType;

    @Column(updatable = false)
    private LocalDate createdDate;

    @Column(updatable = false)
    private LocalTime createdTime;

    @Column
    private LocalDate updatedDate;

    @Column
    private LocalTime updatedTime;


}
