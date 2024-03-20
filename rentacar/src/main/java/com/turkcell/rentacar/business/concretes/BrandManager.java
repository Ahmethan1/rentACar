package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.brands.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.brands.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.brands.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.DeletedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.GotBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.UpdatedBrandResponse;
import com.turkcell.rentacar.business.rules.BrandBusinessRules;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
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
    private BrandBusinessRules brandBusinessRules;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {
        brandBusinessRules.brandNameCanNotBeDuplicated(createBrandRequest.getName());
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = brandRepository.save(brand);
        CreatedBrandResponse createdBrandResponse = this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public GotBrandResponse getById(int id) {
        brandBusinessRules.brandIdCanNotFound(id);
        Brand brand = this.brandRepository.findById(id).orElse(null);
        GotBrandResponse gotBrandResponse = this.modelMapperService.forResponse().map(brand, GotBrandResponse.class);
        return gotBrandResponse;
    }

    @Override
    public List<GotBrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream().map(brand -> this.modelMapperService.forResponse().map(brand, GotBrandResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(int id) throws RuntimeException {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            brandRepository.deleteById(id);
            return true;
        } else {
            throw new BusinessException("Id is not found");
        }
    }

    @Override
    public UpdatedBrandResponse editBrand(int id, UpdateBrandRequest updateBrandRequest) {
        brandBusinessRules.brandIdCanNotFound(id);
        brandBusinessRules.brandNameCanNotBeDuplicated(updateBrandRequest.getName());
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        brandRepository.save(brand);
        return this.modelMapperService.forResponse().map(brand,UpdatedBrandResponse.class);


    }
}

