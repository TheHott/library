package com.evgensoft.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evgensoft.dto.requests.ReaderRequestDTO;
import com.evgensoft.entities.Reader;
import com.evgensoft.entities.TakenBook;
import com.evgensoft.services.ReaderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/api/reader")
public class ReaderController {
	private final ReaderService readerService;

	private final float PAGE_SIZE = 10;

	@GetMapping
	public String indexPaginated(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		int maxPages = (int) Math.ceil(readerService.getCount() / PAGE_SIZE);
		int pages[] = new int[maxPages];

		if (maxPages == 0)
			return "reader/emptyIndex";

		if (page > maxPages) // если введена страница больше возможной
			page = maxPages;
		else if (page < 1) // если введена страница меньше минимальной
			page = 1;

		Page<Reader> resultPage = readerService
				.getByPage(PageRequest.of(page - 1, (int) PAGE_SIZE, Sort.by("fullName").ascending()));

		// создает массив страниц от первой до maxPages
		for (int i = 0; i < maxPages; i++)
			pages[i] = i + 1;

		model.addAttribute("resultPage", resultPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", pages);

		return "reader/index";
	}

	@GetMapping("/create")
	public String create(@ModelAttribute("reader") ReaderRequestDTO readerReq) {
		return "reader/create";
	}

	@PostMapping("/new")
	public String newReader(@ModelAttribute("reader") ReaderRequestDTO readerReq) {
		readerService.createReader(readerReq);

		return "redirect:/api/reader";
	}

	@PostMapping("/get")
	public String showById(@RequestParam Long id) {
		return "redirect:/api/reader/" + id;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model,
			@RequestParam(name = "fromPage", required = false, defaultValue = "1") int fromPage) {
		model.addAttribute("reader", readerService.getReaderById(id));
		model.addAttribute("fromPage", fromPage);
		return "reader/show";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") Long id) {
		model.addAttribute("reader", readerService.getReaderById(id));
		return "reader/edit";
	}

	@PostMapping("/{id}/update")
	public String update(@ModelAttribute("reader") Reader reader, BindingResult bindingResult,
			@PathVariable("id") Long id) {
		readerService.updateReader(id, reader);
		return "redirect:/api/reader/{id}";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		readerService.deleteReader(id);
		return "redirect:/api/reader";
	}

	@GetMapping("/{id}/books")
	public String showTakenBooks(@PathVariable("id") Long id, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page) {
		int maxPages = (int) Math.ceil(readerService.getTakenBooksCount(id) / PAGE_SIZE);
		int pages[] = new int[maxPages];

		if (maxPages == 0)
			return "reader/emptyTakenBooks";

		if (page > maxPages)
			page = maxPages;
		else if (page < 1)
			page = 1;

		Page<TakenBook> resultPage = readerService.getTakenBooksByPage(id, PageRequest.of(page - 1, (int) PAGE_SIZE));

		/* TODO вынести этот цикл в отдельную функцию */
		for (int i = 0; i < maxPages; i++) {
			pages[i] = i + 1;
		}

		model.addAttribute("resultPage", resultPage);
		model.addAttribute("reader", readerService.getReaderById(id));
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", pages);

		return "reader/takenBooks";
	}

}
