package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);

    CreatedBrandResponse getById(int id);

    List<CreatedBrandResponse> getAll();

    Boolean delete(int id);

    UpdatedBrandResponse editBrand(int id, UpdateBrandRequest updateBrandRequest);
}
