package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionsRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor

public class TransmissionBusinessRules {
    private TransmissionsRepository transmissionsRepository;
    public void transNameCanNotBeDuplicated(String brandName) {
        Optional<Transmission> transmission = transmissionsRepository.findByNameIgnoreCase(brandName);
        if (transmission.isPresent()) {
            throw new BusinessException("Brand Exists");
        }
    }
    public void transIdCanNotFound(int id){
        Optional<Transmission> transmission = transmissionsRepository.findById(id);
        if (!transmission.isPresent()){
            throw new BusinessException("Brand Not Exist");
        }

    }
}
