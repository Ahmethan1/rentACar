package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedFuelResponse;
import com.turkcell.rentacar.entities.concretes.Fuel;

import java.util.List;

public interface FuelServie {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    CreatedFuelResponse getById(int id);

    List<CreatedFuelResponse> getAll();

    Boolean delete(int id);

    UpdatedFuelResponse editFuel(int id, UpdateFuelRequest updateFuelRequest);
}
