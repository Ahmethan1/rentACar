package com.turkcell.rentacar.apiControllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.brands.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.brands.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.brands.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.DeletedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.GotBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.brands.UpdatedBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandsController {
    private BrandService brandService;// IoC

    @PostMapping("/createBrand")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }

    @GetMapping("/allBrand")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<GotBrandResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GotBrandResponse getById(@Valid @PathVariable int id) {
        return brandService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Boolean> deleteBrand(@Valid @PathVariable("id") int id) {
        Boolean brand = brandService.delete(id);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("/editBrand/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdatedBrandResponse> editBrand(@Valid @PathVariable int id, @Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
        UpdatedBrandResponse updatedBrandResponse = brandService.editBrand(id, updateBrandRequest);
        return ResponseEntity.ok(updatedBrandResponse);
    }
}
