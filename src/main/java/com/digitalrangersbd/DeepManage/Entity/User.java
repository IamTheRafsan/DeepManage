package com.digitalrangersbd.DeepManage.Entity;

import com.digitalrangersbd.DeepManage.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddableInstantiator;
import org.hibernate.annotations.GenericGenerator;

import javax.lang.model.element.NestingKind;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
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

    @Column(nullable = false)
    private String role_name;

    @Column(nullable = false)
    private String role_id;

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

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    @Column
    private LocalDate updated_date;

    @Column
    private LocalTime updated_time;

    @Column
    private String warehouse_name;

    @Column
    private String warehouse_id;

//    @JsonIgnore
//    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Expense> expense = new ArrayList<>();


    public User(){}

    public User(String firstName, String lastName, String email,String password, String role_id, String role_name, Gender gender, Integer mobile, String country, String city, String address, String warehouse_id, String warehouse_name){

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.role_name = role_name;
        this.gender = gender;
        this.mobile = mobile;
        this.country = country;
        this.city = city;
        this.address = address;
        this.warehouse_id = warehouse_id;
        this.warehouse_name = warehouse_name;

    }

    //Setters and Getters
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_time(LocalTime created_time) {
        this.created_time = created_time;
    }

    public LocalTime getCreated_time() {
        return created_time;
    }

    public void setUpdated_date(LocalDate updated_date) {
        this.updated_date = updated_date;
    }

    public LocalDate getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_time(LocalTime updated_time) {
        this.updated_time = updated_time;
    }

    public LocalTime getUpdated_time() {
        return updated_time;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getWarehouse_name(){
        return warehouse_name;
    }

}