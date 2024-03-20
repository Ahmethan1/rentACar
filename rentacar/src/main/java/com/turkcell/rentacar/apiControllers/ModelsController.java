package com.turkcell.rentacar.apiControllers;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.models.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.models.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.models.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.DeletedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.GotModelResponse;
import com.turkcell.rentacar.business.dtos.responses.models.UpdatedModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/models")

public class ModelsController {
    private ModelService modelService;

    @PostMapping("/createModel")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@Valid  @RequestBody CreateModelRequest createModelRequest) {
        return modelService.add(createModelRequest);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GotModelResponse getById(@PathVariable int id) {
        return modelService.getById(id);
    }

    @GetMapping("/allModels")
    @ResponseStatus(HttpStatus.OK)
    public List<GotModelResponse> getAll() {
        return modelService.getAll();
    }

    @DeleteMapping("/removeModel/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Boolean model = modelService.delete(id);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/editModel/{id}")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<UpdatedModelResponse> editFuel(@Valid @PathVariable int id,@Valid @RequestBody UpdateModelRequest updateModelRequest) {
        UpdatedModelResponse updatedModelResponse = modelService.editModel(id, updateModelRequest);
        return ResponseEntity.ok(updatedModelResponse);
    }
}
