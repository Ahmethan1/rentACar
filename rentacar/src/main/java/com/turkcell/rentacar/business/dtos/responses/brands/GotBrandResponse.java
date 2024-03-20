package com.turkcell.rentacar.business.dtos.responses.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class GotBrandResponse {
    private int id;
    private String name;
}
