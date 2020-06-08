package service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.analyzer.db.entity.BookmarkEntity;
import com.analyzer.db.repository.BookmarkRepository;
import com.analyzer.db.service.BookmarkService;

@RunWith(SpringRunner.class)
public class SearchBookmarkServiceTest {

	@Mock
	private BookmarkRepository bookmarkRepository;

	private BookmarkService bookmarkService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bookmarkService = new BookmarkService(bookmarkRepository);
	}

	@Test(expected = IllegalArgumentException.class)
	public void saveBookmarkWhenIsNull() {
		BookmarkEntity bookmark = null;
		bookmarkService.save(bookmark);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addBookmarkWhenURLIsNull() {
		BookmarkEntity bookmark = new BookmarkEntity();
		bookmark.setUrl(null);
		bookmarkService.save(bookmark);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addBookmarkWhenUserIDIsNull() {
		BookmarkEntity bookmark = new BookmarkEntity();
		bookmark.setUrl("sss");
		bookmark.setUserUUId(null);
		bookmarkService.save(bookmark);
	}

	@Test
	public void addBookmark() {
		BookmarkEntity bookmark = new BookmarkEntity();
		bookmark.setUrl("myurl");
		bookmark.setUserUUId("myuuId");
		bookmarkService.save(bookmark);

		verify(bookmarkRepository, times(1)).save(any());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBookmarkWhenUserIDIsNull() {
		String userUUId = null;
		bookmarkService.findByUserUUId(userUUId);
	}


	@Test
	public void getBookmarks() {
		String userUUId = "myuuId";
		bookmarkService.findByUserUUId(userUUId);

		verify(bookmarkRepository, times(1)).findByUserUUId(userUUId);
	}

}
