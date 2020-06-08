package com.analyzer.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analyzer.api.facade.BookmarkFacade;
import com.analyzer.api.model.BookmarkModel;

@RestController
@RequestMapping(value = "/api/v1/bookmarks")
public class BookmarkController {

	private final BookmarkFacade bookmarkFacade;

	@Autowired
	public BookmarkController(BookmarkFacade bookmarkService) {
		super();
		this.bookmarkFacade = bookmarkService;
	}

	@PostMapping("/{userUUId}" )
	public void addBookmark(@RequestBody BookmarkModel model, @PathVariable("userUUId") String userUUId) {
		bookmarkFacade.add(model, userUUId);
	}

	@GetMapping(params = { "userUUId" })
	public ResponseEntity<List<BookmarkModel>> getBookmarks(@RequestParam("userUUId") String userUUId) {
		return ResponseEntity.ok()
                .body(bookmarkFacade.getUserBookmarks(userUUId));
	}

}
