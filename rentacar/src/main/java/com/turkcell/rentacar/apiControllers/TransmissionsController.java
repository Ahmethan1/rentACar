package com.turkcell.rentacar.apiControllers;


import com.turkcell.rentacar.business.abstracts.TransmissionsService;
import com.turkcell.rentacar.business.dtos.requests.transmissions.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.transmissions.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.transmissions.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.DeletedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.GotTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.UpdatedTransmissionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transmissions")

public class TransmissionsController {
    private TransmissionsService transmissionsService;

    @PostMapping("/createTransmissiions")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@Valid @RequestBody CreateTransmissionRequest createTransmissionRequest) {
        return transmissionsService.add(createTransmissionRequest);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GotTransmissionResponse getById(@Valid @PathVariable int id) {
        return transmissionsService.getById(id);
    }

    @GetMapping("/allTransmissions")
    @ResponseStatus(HttpStatus.OK)
    public List<GotTransmissionResponse> getAll() {
        return transmissionsService.getAll();
    }

    @DeleteMapping("/removeTransmissions/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Boolean transmissions = transmissionsService.delete(id);
        return ResponseEntity.ok(transmissions);
    }

    @PutMapping("/editTransmission/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdatedTransmissionResponse> editTransmissions(@Valid @PathVariable int id,@Valid @RequestBody UpdateTransmissionRequest updateTransmissionRequest) {
        UpdatedTransmissionResponse updatedTransmissionResponse = transmissionsService.editTransmissions(id, updateTransmissionRequest);
        return ResponseEntity.ok(updatedTransmissionResponse);
    }
}
