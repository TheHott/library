package com.evgensoft.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evgensoft.dto.requests.CountryRequestDTO;
import com.evgensoft.entities.Country;
import com.evgensoft.services.CountryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping(value = "api/country")
public class CountryController {
	
	private final CountryService countryService;
	
	private final float PAGE_SIZE = 10;
	
	/* Старая версия index, где нет пагинации */
//	@GetMapping("/index")
//	public String index(Model model) {
//		model.addAttribute("countries", countryService.getAll());
//		return "country/index";
//	}
	
	@GetMapping(value = "/index")
	public String findPaginated(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		int maxPages = (int) Math.ceil(countryService.getCount()/PAGE_SIZE);
		int pages[] = new int[maxPages];
		
		if(page > maxPages) // если введена страница больше возможной
			page = maxPages;
		else if(page < 1) // если введена страница меньше минимальной
			page = 1;
		
		Page<Country> resultPage = countryService.getByPage(PageRequest.of(page-1, (int) PAGE_SIZE));
		
		// создает массив страниц от первой до maxPages
		for(int i=0; i<maxPages; i++)
			pages[i] = i+1;
			
		
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", pages);
		
		return "country/index2";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") Long id) {
		model.addAttribute("country", countryService.readById(id));
		return "country/edit";
	}
	
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute("country") Country country, BindingResult bindingResult, @PathVariable("id") Long id) {
		if(bindingResult.hasErrors())
			return "api/country/edit";
		
		countryService.updateCountry(id, country);
		return "redirect:/api/country/{id}";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model, 
			@RequestParam(name = "fromPage", required = false, defaultValue="1") int fromPage) {
		model.addAttribute("country", countryService.readById(id));
		model.addAttribute("fromPage", fromPage);
		return "country/show";
	}
	
//	@PostMapping("/get")
//	public String showById(@ModelAttribute("search") String id) {
//		System.out.println(id);
//
//		return "redirect:/api/country/{id}";
//	}
	@PostMapping("/get")
	public String showById(@RequestParam Long id) {
		System.out.println("id = " + id);
		
		return "redirect:/api/country/" + id;
	}
	
	@GetMapping("/create")
	public String newCountry(@ModelAttribute("country") CountryRequestDTO countryReq) {
		return "country/create";
	}
	
	@PostMapping("/new")
	public String create(@ModelAttribute("country") CountryRequestDTO countryReq) {
		countryService.createCountry(countryReq);
		return "redirect:/api/country/index";
	}
	
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		countryService.deleteCountry(id);
		return "redirect:/api/country/index";
	}
	

}
