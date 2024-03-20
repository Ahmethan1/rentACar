package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.TransmissionsService;
import com.turkcell.rentacar.business.dtos.requests.transmissions.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.transmissions.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.transmissions.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.DeletedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.GotTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.UpdatedTransmissionResponse;
import com.turkcell.rentacar.business.rules.TransmissionBusinessRules;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
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
    private TransmissionBusinessRules transmissionBusinessRules;

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        transmissionBusinessRules.transNameCanNotBeDuplicated(createTransmissionRequest.getName());
        Transmission transmission1 = this.modelMapperService.forRequest().map(createTransmissionRequest, Transmission.class);
        transmission1.setCreatedDate(LocalDateTime.now());
        Transmission saveTransmission = transmissionsRepository.save(transmission1);
        CreatedTransmissionResponse createdTransmissionResponse = this.modelMapperService.forResponse().map(saveTransmission, CreatedTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public GotTransmissionResponse getById(int id) {
        transmissionBusinessRules.transIdCanNotFound(id);
        Transmission transmission = transmissionsRepository.findById(id).orElse(null);
        GotTransmissionResponse gotTransmissionResponse = this.modelMapperService.forResponse().map(transmission, GotTransmissionResponse.class);
        return gotTransmissionResponse;
    }

    @Override
    public List<GotTransmissionResponse> getAll() {
        List<Transmission> transmissions = transmissionsRepository.findAll();
        return transmissions.stream().map(transmission -> this.modelMapperService.forResponse().map(transmission, GotTransmissionResponse.class)).collect(Collectors.toList());

    }

    @Override
    public Boolean delete(int id) {
        Optional<Transmission> transmission = transmissionsRepository.findById(id);
        if (transmission.isPresent()) {
            transmissionsRepository.deleteById(id);
            return true;
        } else {
            throw new BusinessException("Id is not found");
        }
    }

    @Override
    public UpdatedTransmissionResponse editTransmissions(int id, UpdateTransmissionRequest updateTransmissionRequest) {
        transmissionBusinessRules.transIdCanNotFound(id);
        transmissionBusinessRules.transNameCanNotBeDuplicated(updateTransmissionRequest.getName());
        Transmission transmission = this.modelMapperService.forRequest().map(updateTransmissionRequest,Transmission.class);
        transmission.setUpdateDate(LocalDateTime.now());
        transmissionsRepository.save(transmission);
        return this.modelMapperService.forResponse().map(transmission,UpdatedTransmissionResponse.class);

    }
}
