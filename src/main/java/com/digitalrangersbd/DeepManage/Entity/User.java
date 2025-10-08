package com.digitalrangersbd.DeepManage.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "user-id-generator")
    @GenericGenerator(name = "user-id-generator", strategy = "com.digitalrangersbd.DeepManage.CustomFields.CustomUserID")
    private Long user_id;

    @Column
    private String name;


}
