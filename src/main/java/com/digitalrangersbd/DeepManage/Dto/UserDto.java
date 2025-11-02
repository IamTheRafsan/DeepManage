package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserDto {

    private String user_id;

    @NotNull(message = "First name not found")
    @Size(min=2, max=100, message = "Fist name must be minimum 2 words and maximum 100 words")
    private String firstName;

    @NotNull(message = "Last name not found")
    @Size(min=2, max=100, message = "Fist name must be minimum 2 words and maximum 100 words")
    private String lastName;

    @NotNull(message = "Email not found")
    @Email
    private String email;

    @NotNull(message = "Password not found.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least 8 characters, one uppercase, one lowercase, and one number")
    private String password;

    //@NotBlank(message = "Role not found")
    private String role_name;

    @NotNull(message = "Role not found")
    private String role_id;

    @NotNull
    private Gender gender;

    private Integer mobile;

    private String country;

    private String city;

    private String address;

    private String warehouse_name;

    private String warehouse_id;

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;

    public UserDto(){}

    public UserDto(String firstName, String lastName, String email,String password,String role_id, String role_name, Gender gender, Integer mobile, String country, String city, String address, String warehouse_id, String warehouse_name){

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