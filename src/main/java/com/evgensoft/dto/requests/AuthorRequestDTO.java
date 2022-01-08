package com.evgensoft.dto.requests;

import java.time.LocalDate;

import com.evgensoft.entities.Author;
import com.evgensoft.entities.Country;

import lombok.Data;

@Data
public class AuthorRequestDTO {
	private Long id;
	private String fullName;
	private Country birthCountry;
	private String birthday;
	private String deathDate;
	/* костыль чтобы мог передавать название страны, не делая поиск всех стран перед открытием формы create и не вводя ID
	 * Может быть всё-таки стоит сначала получать список стран? */
	private String birthCountryName;
	
	public static Author toEntity(AuthorRequestDTO authorReq) {
		Author author = new Author();
		
		author.setId(authorReq.getId());
		author.setFullName(authorReq.getFullName());
		author.setBirthCountry(authorReq.getBirthCountry());
		author.setBirthday(LocalDate.parse(authorReq.getBirthday()));
		author.setDeathDate(LocalDate.parse(authorReq.getDeathDate()));
//		author.setBirthday(authorReq.getBirthday());
//		author.setDeathDate(authorReq.getDeathDate());
		
		return author;
	}
}
