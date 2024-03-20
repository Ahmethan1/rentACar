package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.entities.concretes.Model;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreateModelRequest createModelRequest);

    CreatedModelResponse getById(int id);

    List<CreatedModelResponse> getAll();

    Boolean delete(int id);

    UpdatedModelResponse editModel(int id, UpdateModelRequest updateModelRequest);
}
