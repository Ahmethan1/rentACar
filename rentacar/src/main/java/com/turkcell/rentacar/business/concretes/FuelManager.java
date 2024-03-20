package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.FuelServie;
import com.turkcell.rentacar.business.dtos.requests.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedFuelResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FuelManager implements FuelServie {
    private FuelRepository fuelRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        Fuel fuel = this.modelMapperService.forRequest().map(createFuelRequest, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createFuel = fuelRepository.save(fuel);

        CreatedFuelResponse createdFuelResponse = this.modelMapperService.forResponse().map(createFuel, CreatedFuelResponse.class);

        return createdFuelResponse;
    }

    @Override
    public CreatedFuelResponse getById(int id) {
        Fuel fuel = fuelRepository.findById(id).orElse(null);
        CreatedFuelResponse createdFuelResponse = this.modelMapperService.forResponse().map(fuel, CreatedFuelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public List<CreatedFuelResponse> getAll() {
        List<Fuel> fuelList = fuelRepository.findAll();
        return fuelList.stream().map(fuel -> this.modelMapperService.forResponse().map(fuel, CreatedFuelResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(int id) {
        Optional<Fuel> fuel = fuelRepository.findById(id);
        if (fuel.isPresent()) {
            fuelRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Id is not found");
        }
    }

    @Override
    public UpdatedFuelResponse editFuel(int id, UpdateFuelRequest updateFuelRequest) {
        Fuel updateFuel = fuelRepository.findById(id).orElse(null);
        if (updateFuel == null) {
            return null;
        }
        updateFuel.setName(updateFuelRequest.getName());
        Fuel saveFuel = fuelRepository.save(updateFuel);
        UpdatedFuelResponse updatedFuelResponse = this.modelMapperService.forResponse().map(saveFuel, UpdatedFuelResponse.class);
        return updatedFuelResponse;
    }
}
