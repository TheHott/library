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

import com.evgensoft.dto.requests.BookRequestDTO;
import com.evgensoft.entities.Author;
import com.evgensoft.entities.Book;
import com.evgensoft.services.AuthorService;
import com.evgensoft.services.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/api/book")
public class BookController {
	private final AuthorService authorService;
	private final BookService bookService;
	
	private final float PAGE_SIZE = 10;
	
	@GetMapping("/index")
	public String indexPaginated(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		int maxPages = (int) Math.ceil(bookService.getCount()/PAGE_SIZE);
		int pages[] = new int[maxPages];
		
		if(page > maxPages) // если введена страница больше возможной
			page = maxPages;
		else if(page < 1) // если введена страница меньше минимальной
			page = 1;
		
		Page<Book> resultPage = bookService.getByPage(PageRequest.of(page-1, (int) PAGE_SIZE, Sort.by("name").ascending()));
		
		// создает массив страниц от первой до maxPages
		for(int i=0; i<maxPages; i++)
			pages[i] = i+1;
			
		
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", pages);
		
		return "book/index";
	}
	
	@PostMapping("/{id}/give")
	public String giveBookToReader(@PathVariable("id") Long id) {
		bookService.giveBookToReader(id);

		return "redirect:/api/book/{id}";
	}
	
	@PostMapping("/{id}/receive")
	public String giveBookToLibrary(@PathVariable("id") Long id) {
		bookService.giveBookToLibrary(id);
		
		return "redirect:/api/book/{id}";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute("book") BookRequestDTO bookReq) {
		return "book/create";
	}
	
	@PostMapping("/new")
	public String newBook(@ModelAttribute("book") BookRequestDTO bookReq) {
		Author author = authorService.getAuthorByFullName(bookReq.getAuthorName());
		if(author == null) {
			// TODO сделать что-нибудь чтоб возвращало текст ошибки
			return "redirect:/api/book/create";
		} else
			bookReq.setAuthor(author);
		bookService.createBook(bookReq);
		
		return "redirect:/api/book/index";
	}
	
	@PostMapping("/get")
	public String showById(@RequestParam Long id) {
		return "redirect:/api/book/" + id;
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model, 
			@RequestParam(name = "fromPage", required = false, defaultValue="1") int fromPage) {
		model.addAttribute("book", bookService.getBookById(id));
		model.addAttribute("fromPage", fromPage);
		return "book/show";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") Long id) {
		model.addAttribute("book", bookService.getBookById(id));
		return "book/edit";
	}
	
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute("book") Book book, BindingResult bindingResult, @PathVariable("id") Long id) {
		Author author = authorService.getAuthorByFullName(book.getAuthor().getFullName());
		
		if(author == null || bindingResult.hasErrors()) {
			// TODO сделать что-нибудь чтоб возвращало текст ошибки
			return "redirect:/api/book/edit";
		}
		book.setAuthor(author);
		bookService.updateBook(id, book);
		return "redirect:/api/book/{id}";
	}
	
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		return "redirect:/api/book/index";
	}
	
	
	
}
