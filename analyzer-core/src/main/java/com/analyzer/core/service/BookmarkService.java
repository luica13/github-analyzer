package com.analyzer.core.service;

import java.util.List;

import com.analyzer.core.dto.Bookmark;

public interface BookmarkService {

	List<Bookmark> list(String userUUId);

	void add(Bookmark model, String userUUId);
}
