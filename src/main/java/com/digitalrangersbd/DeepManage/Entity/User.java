package com.digitalrangersbd.DeepManage.Entity;

import com.digitalrangersbd.DeepManage.Controller.OutletController;
import com.digitalrangersbd.DeepManage.Controller.WarehouseController;
import com.digitalrangersbd.DeepManage.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.EmbeddableInstantiator;
import org.hibernate.annotations.GenericGenerator;

import javax.lang.model.element.NestingKind;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "user-id-generator")
    @GenericGenerator(name = "user-id-generator", strategy = "com.digitalrangersbd.DeepManage.CustomFields.CustomUserID")
    private String user_id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(unique = true)
    private Integer mobile;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> role = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_warehouse",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private Set<Warehouse> warehouse = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_outlet",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_outlet")
    )
    private Set<Outlet> outlet = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expense = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deposit> deposit = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchase_supplier = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "purchasedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchasedBy = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sale_customer = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "soldBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sold_by = new ArrayList<>();


    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    @Column
    private LocalDate updated_date;

    @Column
    private LocalTime updated_time;




}