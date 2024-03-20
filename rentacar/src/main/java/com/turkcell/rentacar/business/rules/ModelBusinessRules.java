package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class ModelBusinessRules {
    private ModelRepository modelRepository;

    public void fuelNameCanNotBeDuplicated(String modelName) {
        Optional<Model> model = modelRepository.findByNameIgnoreCase(modelName);
        if (model.isPresent()) {
            throw new BusinessException("Brand Exists");
        }
    }
    public void fuelIdCanNotFound(int id){
        Optional<Model> model = modelRepository.findById(id);
        if (!model.isPresent()){
            throw new BusinessException("Brand Not Exist");
        }

    }
}
