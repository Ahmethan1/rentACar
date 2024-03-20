package com.turkcell.rentacar.apiControllers;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
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
    public CreatedModelResponse add(@RequestBody CreateModelRequest createModelRequest) {
        return modelService.add(createModelRequest);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreatedModelResponse getById(@PathVariable int id) {
        return modelService.getById(id);
    }

    @GetMapping("/allModels")
    @ResponseStatus(HttpStatus.OK)
    public List<CreatedModelResponse> getAll() {
        return modelService.getAll();
    }

    @DeleteMapping("/removeModel/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Boolean model = modelService.delete(id);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/editModel/{id}")
    public ResponseEntity<UpdatedModelResponse> editFuel(@PathVariable int id, @RequestBody UpdateModelRequest updateModelRequest) {
        UpdatedModelResponse updatedModelResponse = modelService.editModel(id, updateModelRequest);
        return ResponseEntity.ok(updatedModelResponse);
    }
}
