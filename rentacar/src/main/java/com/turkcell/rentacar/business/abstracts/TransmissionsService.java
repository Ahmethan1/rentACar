package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedTransmissionResponse;
import com.turkcell.rentacar.entities.concretes.Transmission;

import java.util.List;

public interface TransmissionsService {
    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    CreatedTransmissionResponse getById(int id);

    List<CreatedTransmissionResponse> getAll();

    Boolean delete(int id);

    UpdatedTransmissionResponse editTransmissions(int id, UpdateTransmissionRequest updateTransmissionRequest);
}
