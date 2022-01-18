package com.evgensoft.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evgensoft.dto.requests.ReaderRequestDTO;
import com.evgensoft.entities.Reader;
import com.evgensoft.entities.TakenBook;
import com.evgensoft.exceptions.NotFoundException;
import com.evgensoft.repositories.ReaderRepository;
import com.evgensoft.repositories.TakenBookRepository;
import com.evgensoft.services.ReaderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReaderServiceImpl implements ReaderService {

	private final ReaderRepository readerRepo;
	private final TakenBookRepository takenBookRepo;

	@Override
	public Long createReader(ReaderRequestDTO readerReq) {
		Reader reader = ReaderRequestDTO.toEntity(readerReq);
		return readerRepo.save(reader).getId();
	}

	@Override
	public void updateReader(Long id, Reader updatedReader) {
		updatedReader.setId(id);
		readerRepo.save(updatedReader);

	}

	@Override
	public void deleteReader(Long id) {
		// TODO проверить работает ли
		readerRepo.deleteById(id);
	}

	@Override
	public Reader getReaderById(Long id) {
		return readerRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Читатель с id=%d не найден!", id)));
	}

	@Override
	public List<Reader> getAll() {
		return readerRepo.findAll();
	}

	@Override
	public Page<Reader> getByPage(Pageable pageable) {
		return readerRepo.findAll(pageable);
	}

	@Override
	public Long getCount() {
		return readerRepo.count();
	}

	@Override
	public Long getTakenBooksCount(Long id) {
		return takenBookRepo.countByReaderId(id);
	}

	@Override
	public Page<TakenBook> getTakenBooksByPage(Long readerId, Pageable pageable) {
		return takenBookRepo.findAllByReaderId(readerId, pageable);
	}

	@Override
	public List<Reader> listAll(String keyword) {
		if (keyword != null)
			return readerRepo.search(keyword);
		return readerRepo.findAll();
	}

}
