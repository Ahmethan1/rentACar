package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedBrandResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = brandRepository.save(brand);
        CreatedBrandResponse createdBrandResponse = this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public CreatedBrandResponse getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElse(null);
        CreatedBrandResponse createdBrandResponse = this.modelMapperService.forResponse().map(brand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public List<CreatedBrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream().map(brand -> this.modelMapperService.forResponse().map(brand, CreatedBrandResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(int id) throws RuntimeException {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            brandRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Id is not found");
        }
    }

    @Override
    public UpdatedBrandResponse editBrand(int id, UpdateBrandRequest updateBrandRequest) {
        Brand updatedBrand = brandRepository.findById(id).orElse(null);
        if (updatedBrand == null) {
            return null;
        }
        updatedBrand.setName(updateBrandRequest.getName());
        Brand saveBrand = brandRepository.save(updatedBrand);
        UpdatedBrandResponse updatedBrandResponse = this.modelMapperService.forResponse().map(saveBrand, UpdatedBrandResponse.class);
        return updatedBrandResponse;


    }
}

