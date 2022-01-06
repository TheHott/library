package com.evgensoft.dto.requests;

import com.evgensoft.entities.Country;

import lombok.Data;

@Data
public class CountryRequestDTO {
	private Long id;
	private String name;
	
	public static Country toEntity(CountryRequestDTO countryReq) {
		Country country = new Country();
		country.setId(countryReq.getId());
		country.setName(countryReq.getName());
		
		return country;
	}
}
