package leiphotos.domain.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import leiphotos.domain.facade.GPSCoordinates;

class PhotoTest {

	@Test
	void testCreatePhotoWithoutGPS() {
		LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
		File expectedFile = new File("test.jpg");
		String expectedTitle = "Test Photo";
		LocalDateTime expectedAddedDate = LocalDateTime.now();
		//COMPLETE ME

	}

	@Test
	void testCreatePhotoWithGPS() {
		//COMPLETE ME
	}

	@Test
	void testToggleFavourite() {
		//COMPLETE ME
	}

	@Test
	void testSize() { //requires the use of a mock file class
		long expectedSize = 1024;
		MockFile expectedFile = new MockFile("test.jpg",expectedSize);
		String expectedTitle = "Test Photo";
		LocalDateTime expectedAddedDate = LocalDateTime.now();
		//COMPLETE ME
	}

	@Test
	void testNoMatches() {
		String regexp = "Exp.*";
		//COMPLETE ME
	}


	@Test
	void testMatchesTitle() {
		String regexp = "Test.*";
		//COMPLETE ME

	}


	@Test
	void testMatchesFile() {
		String regexp = "Test.*";
		//COMPLETE ME
	}

	@Test
	void testEquals() {
		File file1 = new File("test1.jpg");
		File file2 = new File("test2.jpg");
		File file3 = new File("test1.jpg");

		//COMPLETE ME
	}

	@Test
	void testHashCode() {
		File file1 = new File("test1.jpg");
		File file2 = new File("test1.jpg");

		//COMPLETE ME
	}

	//COMPLETE ME

}