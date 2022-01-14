package com.evgensoft.dto.requests;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.evgensoft.entities.Author;
import com.evgensoft.entities.Country;

import lombok.Data;

@Data
public class AuthorRequestDTO {
	private Long id;
	private String fullName;
	private Country birthCountry;
	@DateTimeFormat(pattern = "dd MMMM yyyy")
	private LocalDate birthday;
	@DateTimeFormat(pattern = "dd MMMM yyyy")
	private LocalDate deathDate;
	private String birthCountryName;

	public static Author toEntity(AuthorRequestDTO authorReq) {
		Author author = new Author();

		author.setId(authorReq.getId());
		author.setFullName(authorReq.getFullName());
		author.setBirthCountry(authorReq.getBirthCountry());
		author.setBirthday(authorReq.getBirthday());
		author.setDeathDate(authorReq.getDeathDate());

		return author;
	}
}
