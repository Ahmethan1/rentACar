package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.brands.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.brands.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.brands.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.DeletedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.GotBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.UpdatedBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);

    GotBrandResponse getById(int id);

    List<GotBrandResponse> getAll();

    Boolean delete(int id);

    UpdatedBrandResponse editBrand(int id, UpdateBrandRequest updateBrandRequest);
}
