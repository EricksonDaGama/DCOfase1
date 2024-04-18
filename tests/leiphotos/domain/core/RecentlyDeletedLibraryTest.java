package leiphotos.domain.core;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import leiphotos.domain.facade.IPhoto;

class RecentlyDeletedLibraryTest {

	private static final int SECONDS_IN_TRASH = 300; // 5 minutes in the trash before deletion
	private static final int SECONDS_TO_CHECK = 299;  // 1 second less than the trash time to check no deletion happens

	private RecentlyDeletedLibrary library;

	@BeforeEach
	void setUp() {
		library = new RecentlyDeletedLibrary();
	}

	@Test
	void testAddPhoto() {
		MockPhoto photo = new MockPhoto(new File("Test.jpg"));

		assertTrue(library.addPhoto(photo));
		assertTrue(library.getPhotos().contains(photo));
		assertEquals(1, library.getNumberOfPhotos());
	}

	@Test
	void testAddExistingPhoto() {
		MockPhoto photo = new MockPhoto(new File("Test.jpg"));
		assertTrue(library.addPhoto(photo));
		assertFalse(library.addPhoto(photo));
		assertEquals(1, library.getNumberOfPhotos());
	}

	@Test
	void testDeletePhoto() {
		MockPhoto photo = new MockPhoto(new File("Test.jpg"));
		library.addPhoto(photo);

		assertTrue(library.deletePhoto(photo));
		assertFalse(library.getPhotos().contains(photo));
	}

	@Test
	void testDeleteNotExistingPhoto() {
		MockPhoto photo1 = new MockPhoto(new File("One.jpg"));
		MockPhoto photo2 = new MockPhoto(new File("Two.jpg"));
		library.addPhoto(photo1);

		assertFalse(library.deletePhoto(photo2));
	}

	@Test
	void testDeleteAll() {
		MockPhoto photo1 = new MockPhoto(new File("One.jpg"));
		MockPhoto photo2 = new MockPhoto(new File("Two.jpg"));
		library.addPhoto(photo1);
		library.addPhoto(photo2);

		library.deleteAll();
		assertTrue(library.getPhotos().isEmpty());
	}

	@Test
	void testGetMatchesEmpty() {
		Collection<IPhoto> matches = library.getMatches(".*");
		assertNotNull(matches);
		assertTrue(matches.isEmpty());
	}

	@Test
	void testGetMatchesNotEmpty() {
		MockPhoto photoY = new MockPhoto(new File("Y.jpg"), true);
		MockPhoto photoN = new MockPhoto(new File("N.jpg"), false);
		library.addPhoto(photoY);
		library.addPhoto(photoN);
		Collection<IPhoto> matches = library.getMatches(".*");

		assertTrue(matches.contains(photoY));
		assertFalse(matches.contains(photoN));
	}

	@Test
	void testAutomaticDelete() throws InterruptedException {
		MockPhoto photo1 = new MockPhoto(new File("One.jpg"));
		MockPhoto photo2 = new MockPhoto(new File("Two.jpg"));
		library.addPhoto(photo1);
		library.addPhoto(photo2);
		Thread.sleep(SECONDS_IN_TRASH * 1000);

		library.clean();
		assertTrue(library.getPhotos().isEmpty());
	}

	@Test
	void testAutomaticDeleteNoEffectTooSoon() {
		MockPhoto photo1 = new MockPhoto(new File("One.jpg"));
		MockPhoto photo2 = new MockPhoto(new File("Two.jpg"));
		library.addPhoto(photo1);
		library.addPhoto(photo2);
		library.clean();
		assertEquals(2, library.getNumberOfPhotos());
	}

	@Test
	void testAutomaticDeleteNoEffectCheckedJustBefore() throws InterruptedException {
		MockPhoto photo1 = new MockPhoto(new File("One.jpg"));
		MockPhoto photo2 = new MockPhoto(new File("Two.jpg"));
		library.addPhoto(photo1);
		library.addPhoto(photo2);
		Thread.sleep(SECONDS_TO_CHECK * 1000);

		library.clean();
		assertEquals(2, library.getNumberOfPhotos());
	}


}
