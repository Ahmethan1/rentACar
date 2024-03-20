package com.turkcell.rentacar.apiControllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedBrandResponse;
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
    public CreatedBrandResponse add(@RequestBody CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }

    @GetMapping("/allBrand")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CreatedBrandResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreatedBrandResponse getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Boolean> deleteBrand(@PathVariable("id") int id) {
        Boolean brand = brandService.delete(id);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("/editBrand/{id}")
    public ResponseEntity<UpdatedBrandResponse> editBrand(@PathVariable int id, @RequestBody UpdateBrandRequest updateBrandRequest) {
        UpdatedBrandResponse updatedBrandResponse = brandService.editBrand(id, updateBrandRequest);
        return ResponseEntity.ok(updatedBrandResponse);
    }
}
