package com.digitalrangersbd.DeepManage.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weight_wastage")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeightWastage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
