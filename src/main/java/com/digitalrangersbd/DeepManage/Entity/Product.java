package com.digitalrangersbd.DeepManage.Entity;

import com.digitalrangersbd.DeepManage.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column()
    private Double price;

    @Column()
    private Float stock;

    //@Column(nullable` = false, updatable = false)
    private LocalDate created_date;

    //@Column(nullable = false, updatable = false)
    private LocalTime created_time;

    private String created_by_id;

    private LocalDate updated_date;

    private LocalTime updated_time;

    private String updated_by_id;

    private boolean deleted = false;

    private String deletedById;

    private String deletedByName;

    private LocalDate deletedDate;

    private LocalTime deletedTime;


}
