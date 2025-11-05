package com.digitalrangersbd.DeepManage.Dto;

import com.digitalrangersbd.DeepManage.Enum.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private String role_id;

    @NotNull(message = "Role Name not found")
    @Size(min = 2, max = 100, message = "Role name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Role created by Id not found")
    private String created_by_id;

    @NotNull(message = "No permission assigned to role")
    private Set<Permission> permission = new HashSet<>();

    private LocalDate created_date;

    private LocalTime created_time;

    private LocalDate updated_date;

    private LocalTime updated_time;



}