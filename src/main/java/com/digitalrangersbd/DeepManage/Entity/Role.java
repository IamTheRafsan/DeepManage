package com.digitalrangersbd.DeepManage.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(generator = "role_id_generator")
    @GenericGenerator(name= "role_id_generator", strategy = "com.digitalrangersbd.DeepManage.CustomFields.CustomRoleID")
    private String id;



}
