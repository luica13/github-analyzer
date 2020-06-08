package com.analyzer.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.analyzer.core.dto.Bookmark;
import com.analyzer.db.entity.BookmarkEntity;
import com.analyzer.db.service.BookmarkService;

@Service("SearchBookmarkService")
public class SearchBookmarkService implements com.analyzer.core.service.BookmarkService {

	private final BookmarkService bookmarkService;

	public SearchBookmarkService(BookmarkService bookmarkService) {
		super();
		this.bookmarkService = bookmarkService;
	}

	@Override
	@CacheEvict(key = "#userUUId", cacheNames = "bookmarks")
	public void add(Bookmark model, String userUUId) {

		Assert.notNull(userUUId, "userUUId should not be null");
		Assert.notNull(model, "model should not be null");
		Assert.notNull(model.getUrl(), "url should not be null");

		BookmarkEntity bookmark = new BookmarkEntity();
		bookmark.setUrl(model.getUrl());
		bookmark.setUserUUId(userUUId);
		bookmark.setName(model.getName());

		bookmarkService.save(bookmark);
	}

	@Override
	@Cacheable(value = "bookmarks", key = "#userUUId")
	public List<Bookmark> list(String userUUId) {
		Assert.notNull(userUUId, "userUUId should not be null");

		List<BookmarkEntity> bookmarks = bookmarkService.findByUserUUId(userUUId);
		return bookmarks.stream().map(e -> convertToModel(e)).collect(Collectors.toList());
	}

	private Bookmark convertToModel(BookmarkEntity e) {
		Bookmark bookmark = new Bookmark();
		bookmark.setUrl(e.getUrl());
		bookmark.setName(e.getName());
		bookmark.setDateAdded(e.getCreatedDate());
		return bookmark;
	}
}
