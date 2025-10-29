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
@Table(name = "payment_type")
@Getter
@Setter
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expense = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    public PaymentType(){}

    public PaymentType(String name){
        this.name = name;
    }

}
