package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.FuelServie;
import com.turkcell.rentacar.business.dtos.requests.fuels.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.fuels.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.fuels.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.DeletedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.GotFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.fuels.UpdatedFuelResponse;
import com.turkcell.rentacar.business.rules.FuelBusinessRules;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
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
    private FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(createFuelRequest.getName());
        Fuel fuel = this.modelMapperService.forRequest().map(createFuelRequest, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createFuel = fuelRepository.save(fuel);

        CreatedFuelResponse createdFuelResponse = this.modelMapperService.forResponse().map(createFuel, CreatedFuelResponse.class);

        return createdFuelResponse;
    }

    @Override
    public GotFuelResponse getById(int id) {
        fuelBusinessRules.fuelIdCanNotFound(id);
        Fuel fuel = fuelRepository.findById(id).orElse(null);
        GotFuelResponse gotFuelResponse = this.modelMapperService.forResponse().map(fuel, GotFuelResponse.class);
        return gotFuelResponse;
    }

    @Override
    public List<GotFuelResponse> getAll() {
        List<Fuel> fuelList = fuelRepository.findAll();
        return fuelList.stream().map(fuel -> this.modelMapperService.forResponse().map(fuel, GotFuelResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(int id) {
        Optional<Fuel> fuel = fuelRepository.findById(id);
        if (fuel.isPresent()) {
            fuelRepository.deleteById(id);
            return true;
        } else {
            throw new BusinessException("Id is not found");
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
