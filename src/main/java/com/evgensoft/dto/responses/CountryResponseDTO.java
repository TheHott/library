package com.evgensoft.dto.responses;

import com.evgensoft.entities.Country;

import lombok.Data;

@Data
public class CountryResponseDTO {
	private String name;
	
	public static CountryResponseDTO fromEntity(Country country) {
		CountryResponseDTO countryResponse = new CountryResponseDTO();
		countryResponse.setName(country.getName());
		return countryResponse;
	}
}
