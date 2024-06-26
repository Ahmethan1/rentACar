package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransmissionsRepository extends JpaRepository<Transmission,Integer> {
    Optional<Transmission> findByNameIgnoreCase(String name);
}
