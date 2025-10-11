package com.digitalrangersbd.DeepManage.Entity;

import com.digitalrangersbd.DeepManage.Enum.Permission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(generator = "role_id_generator")
    @GenericGenerator(name= "role_id_generator", strategy = "com.digitalrangersbd.DeepManage.CustomFields.CustomRoleID")
    private String id;

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
    @Column(name = "permission")
    private Set<Permission> permission = new HashSet<>();

    @Column(nullable = false)
    private LocalDate created_date;

    @Column(nullable = false)
    private LocalTime created_time;

    @Column
    private LocalDate updated_date;

    @Column
    private LocalTime updated_time;

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
