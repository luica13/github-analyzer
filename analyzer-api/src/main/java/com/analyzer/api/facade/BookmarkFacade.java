package com.analyzer.api.facade;

import java.util.List;

import com.analyzer.api.model.BookmarkModel;

public interface BookmarkFacade {
	
	List<BookmarkModel> getUserBookmarks(String userId);

	void add(BookmarkModel model, String userUUId);
}
