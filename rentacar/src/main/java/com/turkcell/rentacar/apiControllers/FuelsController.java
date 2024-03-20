package com.turkcell.rentacar.apiControllers;

import com.turkcell.rentacar.business.abstracts.FuelServie;
import com.turkcell.rentacar.business.dtos.requests.fuels.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.fuels.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.fuels.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.DeletedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.GotFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.UpdatedFuelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/fuels")

public class FuelsController {
    private FuelServie fuelServie;

    @PostMapping("/createFuel")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@Valid @RequestBody CreateFuelRequest createFuelRequest) {
        return fuelServie.add(createFuelRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GotFuelResponse getById(@Valid @PathVariable int id) {
        return fuelServie.getById(id);
    }

    @GetMapping("/allFuels")
    @ResponseStatus(HttpStatus.OK)
    public List<GotFuelResponse> getAll() {
        return fuelServie.getAll();
    }

    @DeleteMapping("/removeId/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        Boolean fuel = fuelServie.delete(id);
        return ResponseEntity.ok(fuel);
    }


    @PutMapping("/editFuels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdatedFuelResponse> editFuel(@Valid @PathVariable int id, @Valid @RequestBody UpdateFuelRequest updateFuelRequest) {
        UpdatedFuelResponse updatedFuelResponse = fuelServie.editFuel(id, updateFuelRequest);
        return ResponseEntity.ok(updatedFuelResponse);
    }
}
