package com.analyzer.api.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.analyzer.api.exception.GitHubAnalysisException;
import com.analyzer.api.facade.BookmarkFacade;
import com.analyzer.api.model.BookmarkModel;
import com.analyzer.core.dto.Bookmark;
import com.analyzer.core.service.BookmarkService;

import ma.glasnost.orika.MapperFacade;

@Component
public class BookmarkFacadeImpl implements BookmarkFacade {

	private final BookmarkService bookmarkService;
	private final MapperFacade bookmarkModelMapper;

	@Autowired
	public BookmarkFacadeImpl(@Qualifier("SearchBookmarkService")BookmarkService bookmarkService,
							  @Qualifier("bookmarkModelMapper") MapperFacade bookmarkModelMapper) {
		this.bookmarkService = bookmarkService;
		this.bookmarkModelMapper = bookmarkModelMapper;
	}

	@Override
	public List<BookmarkModel> getUserBookmarks(String userId) {
		if (StringUtils.isEmpty(userId)) {
			throw new GitHubAnalysisException("Invalid input data: userID should not be empty");
		}
		List<Bookmark> bookmarks = bookmarkService.list(userId);
		
		return bookmarkModelMapper.mapAsList(bookmarks, BookmarkModel.class);
	}

	@Override
	public void add(BookmarkModel model, String userUUId) {
		Bookmark bookmark = bookmarkModelMapper.map(model, Bookmark.class);
		bookmarkService.add(bookmark, userUUId);
	}

}
