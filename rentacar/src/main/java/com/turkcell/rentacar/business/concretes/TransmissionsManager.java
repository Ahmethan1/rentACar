package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.TransmissionsService;
import com.turkcell.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedTransmissionResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionsRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransmissionsManager implements TransmissionsService {
    private TransmissionsRepository transmissionsRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        Transmission transmission1 = this.modelMapperService.forRequest().map(createTransmissionRequest, Transmission.class);
        transmission1.setCreatedDate(LocalDateTime.now());
        Transmission saveTransmission = transmissionsRepository.save(transmission1);
        CreatedTransmissionResponse createdTransmissionResponse = this.modelMapperService.forResponse().map(saveTransmission, CreatedTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public CreatedTransmissionResponse getById(int id) {
        Transmission transmission = transmissionsRepository.findById(id).orElse(null);
        CreatedTransmissionResponse createdTransmissionResponse = this.modelMapperService.forResponse().map(transmission, CreatedTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public List<CreatedTransmissionResponse> getAll() {
        List<Transmission> transmissions = transmissionsRepository.findAll();
        return transmissions.stream().map(transmission -> this.modelMapperService.forResponse().map(transmission, CreatedTransmissionResponse.class)).collect(Collectors.toList());

    }

    @Override
    public Boolean delete(int id) {
        Optional<Transmission> transmission = transmissionsRepository.findById(id);
        if (transmission.isPresent()) {
            transmissionsRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Id is not found");
        }
    }

    @Override
    public UpdatedTransmissionResponse editTransmissions(int id, UpdateTransmissionRequest updateTransmissionRequest) {
        Transmission updateTransmissions = transmissionsRepository.findById(id).orElse(null);
        if (updateTransmissions == null) {
            return null;
        }
        updateTransmissions.setName(updateTransmissionRequest.getName());
        Transmission saveTransmission = transmissionsRepository.save(updateTransmissions);
        UpdatedTransmissionResponse updatedTransmissionResponse = this.modelMapperService.forResponse().map(saveTransmission, UpdatedTransmissionResponse.class);
        return updatedTransmissionResponse;

    }
}
