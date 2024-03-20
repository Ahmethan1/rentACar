package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entites.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transmissions")

public class Transmission extends BaseEntity {
    @Column(name = "name")
    private String name;
}
