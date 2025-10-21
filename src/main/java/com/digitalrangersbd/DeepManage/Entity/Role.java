package com.digitalrangersbd.DeepManage.Entity;

import com.digitalrangersbd.DeepManage.Enum.Permission;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(generator = "role_id_generator")
    @GenericGenerator(name= "role_id_generator", strategy = "com.digitalrangersbd.DeepManage.CustomFields.CustomRoleID")
    private String role_id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String created_by_id;

    @Column
    private String created_by_name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "permission", length = 255)
    private Set<Permission> permission = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDate created_date;

    @Column(nullable = false, updatable = false)
    private LocalTime created_time;

    @Column
    private LocalDate updated_date;

    @Column
    private LocalTime updated_time;

    public Role(){}

    public Role(String name, String created_by_id, String created_by_name, Set<Permission> permission){

        this.name = name;
        this.created_by_id = created_by_id;
        this.created_by_name = created_by_name;
        this.permission = permission;

    }

    private void addPermission(Permission permission){
        this.permission.add(permission);
    }

    private void removePermission(Permission permission){
        this.permission.remove(permission);
    }

    private boolean hasPermission(Permission permission){
        return this.permission.contains(permission);
    }



}