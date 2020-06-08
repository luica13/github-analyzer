package com.analyzer.db.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.analyzer.db.entity.BookmarkEntity;
import com.analyzer.db.repository.BookmarkRepository;

@Service
public class BookmarkService {

	private final BookmarkRepository repository;

	public BookmarkService(BookmarkRepository repository) {
		this.repository = repository;
	}

	public void save(BookmarkEntity entity) {
		Assert.notNull(entity, "model should not be null");
		Assert.notNull(entity.getUrl(), "url should not be null");
		Assert.notNull(entity.getUserUUId(), "userUUId should not be null");

		repository.save(entity);
	}

	public List<BookmarkEntity> findByUserUUId(String userUUId) {
		Assert.notNull(userUUId, "userUUId should not be null");

		return repository.findByUserUUId(userUUId);
	}
}
