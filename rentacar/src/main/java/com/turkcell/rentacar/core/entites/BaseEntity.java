package com.turkcell.rentacar.core.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass

public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
    @Column(name = "updateDate")
    private LocalDateTime updateDate;
    @Column(name = "deleteDate")
    private LocalDateTime deleteDate;
}
