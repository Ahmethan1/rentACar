package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.fuels.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.fuels.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.fuels.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.DeletedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.GotFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.UpdatedFuelResponse;

import java.util.List;

public interface FuelServie {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);

    GotFuelResponse getById(int id);

    List<GotFuelResponse> getAll();

    Boolean delete(int id);

    UpdatedFuelResponse editFuel(int id, UpdateFuelRequest updateFuelRequest);
}
