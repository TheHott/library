package com.evgensoft.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.evgensoft.dto.requests.ReaderRequestDTO;
import com.evgensoft.entities.Reader;

public interface ReaderService {
	Long createReader(ReaderRequestDTO readerReq);
	void updateReader(Long id, Reader updatedReader);
	void deleteReader(Long id);
	Reader getReaderById(Long id);
	List<Reader> getAll();
	Page<Reader> getByPage(Pageable pageable);
	Long getCount();
	// Reader getReaderByFullName(String name);
}
