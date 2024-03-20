package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.models.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.models.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.models.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.DeletedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.GotModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.UpdatedModelResponse;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreateModelRequest createModelRequest);

    GotModelResponse getById(int id);

    List<GotModelResponse> getAll();

    Boolean delete(int id);

    UpdatedModelResponse editModel(int id, UpdateModelRequest updateModelRequest);
}
