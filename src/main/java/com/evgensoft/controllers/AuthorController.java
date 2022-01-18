package com.evgensoft.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evgensoft.dto.requests.AuthorRequestDTO;
import com.evgensoft.entities.Author;
import com.evgensoft.entities.Book;
import com.evgensoft.entities.Country;
import com.evgensoft.services.AuthorService;
import com.evgensoft.services.CountryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/api/author")
public class AuthorController {
	private final CountryService countryService;
	private final AuthorService authorService;

	private final float PAGE_SIZE = 10;

	@GetMapping
	public String indexPaginated(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		int maxPages = (int) Math.ceil(authorService.getCount() / PAGE_SIZE);
		int pages[] = new int[maxPages];

		if (maxPages == 0)
			return "author/emptyIndex";

		if (page > maxPages) // если введена страница больше возможной
			page = maxPages;
		else if (page < 1) // если введена страница меньше минимальной
			page = 1;

		Page<Author> resultPage = authorService
				.getByPage(PageRequest.of(page - 1, (int) PAGE_SIZE, Sort.by("fullName").ascending()));

		// создает массив страниц от первой до maxPages
		for (int i = 0; i < maxPages; i++)
			pages[i] = i + 1;

		model.addAttribute("resultPage", resultPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", pages);

		return "author/index";
	}

	@GetMapping("/create")
	public String createAuthor(Model model, @ModelAttribute("author") AuthorRequestDTO authorReq) {
		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);
		return "author/create";
	}

	@PostMapping("/new")
	public String create(Model model, @ModelAttribute("author") AuthorRequestDTO authorReq) {
		Country country = countryService.getByName(authorReq.getBirthCountryName());
		if (country == null) {
			model.addAttribute("countryError", "Неправильное название страны");
		}

		authorReq.setBirthCountry(country);
		authorService.createAuthor(authorReq);

		return "redirect:/api/author";
	}

	@PostMapping("/{id}/delete/confirm")
	public String confirmDelete(Model model, @PathVariable("id") Long id) {
		model.addAttribute(authorService.getAuthorById(id));

		return "author/confirmDelete";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") Long id) {
		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);
		model.addAttribute("author", authorService.getAuthorById(id));
		return "author/edit";
	}

	@PostMapping("/{id}/update")
	public String update(@ModelAttribute("author") Author author, BindingResult bindingResult,
			@PathVariable("id") Long id) {
		Country country = countryService.getByName(author.getBirthCountry().getName());
		if (country == null || bindingResult.hasErrors()) {
			// TODO сделать что-нибудь чтоб возвращало текст ошибки
			return "redirect:/api/author/edit";
		} else
			author.setBirthCountry(country);

		authorService.updateAuthor(id, author);
		return "redirect:/api/author/{id}";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model,
			@RequestParam(name = "fromPage", required = false, defaultValue = "1") int fromPage) {
		model.addAttribute("author", authorService.getAuthorById(id));
		model.addAttribute("fromPage", fromPage);
		return "author/show";
	}

	@PostMapping("/get")
	public String showById(@RequestParam Long id) {
		return "redirect:/api/author/" + id;
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		authorService.deleteAuthor(id);
		return "redirect:/api/author";
	}

	@GetMapping("/{id}/books")
	public String getBooksFromAuthor(@PathVariable("id") Long id, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page) {
		Author author = authorService.getAuthorById(id);
		int maxPages = (int) Math.ceil(authorService.getBooksCount(id) / PAGE_SIZE);
		int pages[] = new int[maxPages];

		if (maxPages == 0) {
			model.addAttribute("author", author);
			return "author/emptyBooks";
		}

		if (page > maxPages)
			page = maxPages;
		else if (page < 1)
			page = 1;

		Page<Book> resultPage = authorService.getBooksByPage(id, PageRequest.of(page - 1, (int) PAGE_SIZE));

		/* TODO вынести этот цикл в отдельную функцию */
		for (int i = 0; i < maxPages; i++) {
			pages[i] = i + 1;
		}

		model.addAttribute("resultPage", resultPage);
		model.addAttribute("author", author);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", pages);

		return "author/books";
	}

	@RequestMapping("/search")
	public String search(Model model, @Param("keyword") String keyword) {
		List<Author> listAuthors = authorService.listAll(keyword);

		model.addAttribute("resultPage", listAuthors);
		model.addAttribute("keyword", keyword);

		return "author/index";
	}

}
