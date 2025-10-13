package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Enum.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoleUpdateDto {

    private String role_id;

    private String name;

    @NotNull(message = "Role created by Id not found")
    private String created_by_id;

    @NotNull(message = "Role created by name not found")
    private String created_by_name;

    @NotNull(message = "No permission assigned to role")
    private Set<Permission> permission = new HashSet<>();

    private LocalDate updated_date;

    private LocalTime updated_time;

    public RoleUpdateDto(){}

    public RoleUpdateDto(String name, String created_by_id, String created_by_name, Set<Permission> permission){

        this.name = name;
        this.created_by_id = created_by_id;
        this.created_by_name = created_by_name;
        this.permission = permission;

    }



}