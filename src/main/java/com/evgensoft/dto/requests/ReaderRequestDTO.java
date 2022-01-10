package com.evgensoft.dto.requests;

import com.evgensoft.entities.Reader;

import lombok.Data;

@Data
public class ReaderRequestDTO {
	private Long id;
	
	private String fullName;
	
	private Long debt = (long) 0;
	
	public static Reader toEntity(ReaderRequestDTO readerReq) {
		Reader reader = new Reader();
		reader.setId(readerReq.getId());
		reader.setFullName(readerReq.getFullName());
		reader.setDebt(readerReq.getDebt());
		
		return reader;
	}
}
