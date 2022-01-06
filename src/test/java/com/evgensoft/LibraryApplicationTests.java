package com.evgensoft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evgensoft.entities.Country;
import com.evgensoft.repositories.CountryRepository;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	CountryRepository countryRepo;
	
	@Test
	void contextLoads() {
		Country c1 = new Country();
		c1.setName("Россия");
		
		Country c2 = new Country(); c2.setName("ПШПШИК");
		c2.setName("США"); 
		// c2.setId((long) 4);
		
		countryRepo.save(c2); // save также заменяет запись, если айди совпадает с существующим
		// countryRepo.delete(c2);
	}

}
