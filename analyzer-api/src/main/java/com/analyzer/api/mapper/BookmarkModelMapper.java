package com.analyzer.api.mapper;

import org.springframework.stereotype.Component;

import com.analyzer.api.model.BookmarkModel;
import com.analyzer.core.dto.Bookmark;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component("bookmarkModelMapper")
public class BookmarkModelMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Bookmark.class, BookmarkModel.class)
				.byDefault()
				.register();

		factory.classMap(BookmarkModel.class, Bookmark.class)
				.byDefault()
				.register();
	}
}
