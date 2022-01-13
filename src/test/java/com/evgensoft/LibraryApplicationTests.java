package com.evgensoft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evgensoft.entities.Country;
import com.evgensoft.repositories.AuthorRepository;
import com.evgensoft.repositories.BookRepository;
import com.evgensoft.repositories.CountryRepository;
import com.evgensoft.services.CountryService;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	CountryRepository countryRepo;

	@Autowired
	CountryService countryService;
	@Autowired
	AuthorRepository authorRepo;
	@Autowired
	BookRepository bookRepo;

	// @Test
//	void contextLoads() {
//		Country c1 = new Country();
//		c1.setName("Россия");
//		
//		Country c2 = new Country(); c2.setName("ПШПШИК");
//		c2.setName("США"); 
//		// c2.setId((long) 4);
//		
//		countryRepo.save(c2); // save также заменяет запись, если айди совпадает с существующим
//		// countryRepo.delete(c2);
//	}

	@Test
	void testFindByName() {
		String name = "Россия";
		System.out.println(countryService.getByName(name));
	}

	@Test
	void deleteChildren() throws InterruptedException {
		Country c1 = countryRepo.getById((long) 40);
//		Country c1 = new Country();
//		c1.setName("Lol");
////		countryRepo.save(c1);
//
//		Author a1 = new Author();
//		a1.setBirthCountry(c1);
//		a1.setBirthday(LocalDate.now());
//		a1.setFullName("Test Author");
//		a1.setDeathDate(LocalDate.now());
////		authorRepo.save(a1);
//
//		Book b1 = new Book();
//		b1.setAuthor(a1);
//		b1.setInStock(1231);
//		b1.setName("Test book");
//		b1.setReleaseDate(LocalDate.now());
////		bookRepo.save(b1);

		countryRepo.delete(c1);

	}

}
