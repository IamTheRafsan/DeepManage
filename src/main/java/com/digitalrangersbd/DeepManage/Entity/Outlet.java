package com.digitalrangersbd.DeepManage.Entity;

import com.digitalrangersbd.DeepManage.Enum.ActiveStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "outlet")
@Setter
@Getter
public class Outlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Number mobile;

    @Column()
    private String country;

    @Column()
    private String city;

    @Column(nullable = false)
    private String area;

    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expense = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    @Column
    private LocalDate updated_date;

    @Column
    private LocalTime updated_time;

    public Outlet(){}

    public Outlet(String name, String email, Number mobile, String country, String city, String area, ActiveStatus status){
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.country = country;
        this.city = city;
        this.area = area;
        this.status = status;
    }
}
