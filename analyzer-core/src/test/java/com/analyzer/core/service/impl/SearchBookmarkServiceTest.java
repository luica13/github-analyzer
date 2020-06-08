package com.analyzer.core.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.analyzer.core.dto.Bookmark;
import com.analyzer.db.service.BookmarkService;

public class SearchBookmarkServiceTest  extends BaseServiceTest{

	@Mock
	private BookmarkService bookmarkService;

	private SearchBookmarkService searchBookmarkService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		searchBookmarkService = new SearchBookmarkService(bookmarkService);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addBookmarkWhenIsNull() {
		String userUUId = "";
		Bookmark bookmark = null;
		searchBookmarkService.add(bookmark, userUUId);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addBookmarkWhenURLIsNull() {
		String userUUId = "";
		Bookmark bookmark = new Bookmark();
		bookmark.setUrl(null);
		searchBookmarkService.add(bookmark, userUUId);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addBookmarkWhenUserIDIsNull() {
		String userUUId = null;
		Bookmark bookmark = new Bookmark();
		searchBookmarkService.add(bookmark, userUUId);
	}

	@Test
	public void addBookmark() {
		Bookmark bookmark = new Bookmark();
		bookmark.setUrl("myurl");
		String userUUId = "myuuId";
		
		searchBookmarkService.add(bookmark, userUUId);

		verify(bookmarkService, times(1)).save(any());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBookmarkWhenUserIDIsNull() {
		String userUUId = null;
		searchBookmarkService.list(userUUId);
	}


	@Test
	public void getBookmarks() {
		String userUUId = "myuuId";
		searchBookmarkService.list(userUUId);

		verify(bookmarkService, times(1)).findByUserUUId(userUUId);
	}

}
