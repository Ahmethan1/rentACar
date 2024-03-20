package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.transmissions.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.transmissions.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.transmissions.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.DeletedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.GotTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.UpdatedTransmissionResponse;

import java.util.List;

public interface TransmissionsService {
    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);

    GotTransmissionResponse getById(int id);

    List<GotTransmissionResponse> getAll();

    Boolean delete(int id);

    UpdatedTransmissionResponse editTransmissions(int id, UpdateTransmissionRequest updateTransmissionRequest);
}
