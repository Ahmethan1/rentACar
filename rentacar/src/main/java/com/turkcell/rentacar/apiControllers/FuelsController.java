package com.turkcell.rentacar.apiControllers;

import com.turkcell.rentacar.business.abstracts.FuelServie;
import com.turkcell.rentacar.business.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedFuelResponse;
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
    public CreatedFuelResponse add(@RequestBody CreateFuelRequest createFuelRequest) {
        return fuelServie.add(createFuelRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreatedFuelResponse getById(@PathVariable int id) {
        return fuelServie.getById(id);
    }

    @GetMapping("/allFuels")
    @ResponseStatus(HttpStatus.OK)
    public List<CreatedFuelResponse> getAll() {
        return fuelServie.getAll();
    }

    @DeleteMapping("/removeId/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        Boolean fuel = fuelServie.delete(id);
        return ResponseEntity.ok(fuel);
    }

    @PutMapping("/editFuels/{id}")
    public ResponseEntity<UpdatedFuelResponse> editFuel(@PathVariable int id, @RequestBody UpdateFuelRequest updateFuelRequest) {
        UpdatedFuelResponse updatedFuelResponse = fuelServie.editFuel(id, updateFuelRequest);
        return ResponseEntity.ok(updatedFuelResponse);
    }
}
