package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
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

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        Model model1 = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
        model1.setCreatedDate(LocalDateTime.now());
        Model saveModel = modelRepository.save(model1);
        CreatedModelResponse createdFuelResponse = this.modelMapperService.forResponse().map(saveModel, CreatedModelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public CreatedModelResponse getById(int id) {
        Model model = modelRepository.findById(id).orElse(null);
        CreatedModelResponse createdModelResponse = this.modelMapperService.forResponse().map(model, CreatedModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public List<CreatedModelResponse> getAll() {
        List<Model> modelList = modelRepository.findAll();
        return modelList.stream().map(model -> this.modelMapperService.forResponse().map(model, CreatedModelResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(int id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            modelRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Id is not found");
        }

    }

    @Override
    public UpdatedModelResponse editModel(int id, UpdateModelRequest updateModelRequest) {
        Model updateModel = modelRepository.findById(id).orElse(null);
        if (updateModel == null) {
            return null;
        }
        updateModel.setName(updateModelRequest.getName());
        Model saveModel = modelRepository.save(updateModel);
        UpdatedModelResponse updatedModelResponse = this.modelMapperService.forResponse().map(saveModel, UpdatedModelResponse.class);
        return updatedModelResponse;
    }
}
