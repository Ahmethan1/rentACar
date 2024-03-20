package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.models.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.models.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.models.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.DeletedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.GotModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.UpdatedModelResponse;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        modelBusinessRules.fuelNameCanNotBeDuplicated(createModelRequest.getName());
        Model model1 = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
        model1.setCreatedDate(LocalDateTime.now());
        Model saveModel = modelRepository.save(model1);
        CreatedModelResponse createdFuelResponse = this.modelMapperService.forResponse().map(saveModel, CreatedModelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public GotModelResponse getById(int id) {
        modelBusinessRules.fuelIdCanNotFound(id);
        Model model = modelRepository.findById(id).orElse(null);
        GotModelResponse gotModelResponse = this.modelMapperService.forResponse().map(model, GotModelResponse.class);
        return gotModelResponse;
    }

    @Override
    public List<GotModelResponse> getAll() {
        List<Model> modelList = modelRepository.findAll();
        return modelList.stream().map(model -> this.modelMapperService.forResponse().map(model, GotModelResponse.class)).collect(Collectors.toList());
    }


    @Override
    public Boolean delete(int id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            modelRepository.deleteById(id);
            return true;
        } else {
            throw new BusinessException("Id is not found");
        }

    }

    @Override
    public UpdatedModelResponse editModel(int id, UpdateModelRequest updateModelRequest) {
       modelBusinessRules.fuelIdCanNotFound(id);
       modelBusinessRules.fuelNameCanNotBeDuplicated(updateModelRequest.getName());
       Model model = this.modelMapperService.forRequest().map(updateModelRequest,Model.class);
       model.setUpdateDate(LocalDateTime.now());
       modelRepository.save(model);
       return this.modelMapperService.forResponse().map(model,UpdatedModelResponse.class);
    }
}
