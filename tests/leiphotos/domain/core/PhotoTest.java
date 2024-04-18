package leiphotos.domain.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;
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
        PhotoMetadata metadata = new PhotoMetadata(null, "Unknown make", "Unknown model", expectedCapturedDate.toString(), "Unknown size", "No description");
        Photo photo = new Photo(expectedTitle, expectedAddedDate, metadata, expectedFile);

        assertEquals(expectedTitle, photo.title());
        assertEquals(expectedAddedDate, photo.addedDate());
        assertEquals(expectedFile, photo.file());
    }

    @Test
    void testCreatePhotoWithGPS() {
        LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        File expectedFile = new File("test.jpg");
        String expectedTitle = "Test Photo";
        LocalDateTime expectedAddedDate = LocalDateTime.now();
        GPSLocation location = new GPSLocation(30.0, -90.0, "Test location");
        PhotoMetadata metadata = new PhotoMetadata(location, "Unknown make", "Unknown model", expectedCapturedDate.toString(), "Unknown size", "No description");
        Photo photo = new Photo(expectedTitle, expectedAddedDate, metadata, expectedFile);

        assertEquals(expectedTitle, photo.title());
        assertEquals(expectedFile, photo.file());
        assertTrue(photo.getPlace().isPresent());
        assertEquals(location, photo.getPlace().get());
    }

    @Test
    void testToggleFavourite() {
        PhotoMetadata metadata = new PhotoMetadata(null, "Unknown make", "Unknown model", "2024-01-01T00:00", "1MB", "No description");
        File file = new File("test.jpg");
        Photo photo = new Photo("Test", LocalDateTime.now(), metadata, file);
        assertFalse(photo.isFavourite());
        photo.toggleFavourite();
        assertTrue(photo.isFavourite());
        photo.toggleFavourite();
        assertFalse(photo.isFavourite());
    }


    @Test
    void testSize() {
        long expectedSize = 1024;
        MockFile file = new MockFile("test.jpg", expectedSize);
        Photo photo = new Photo("Test Photo", LocalDateTime.now(), null, file);
        assertEquals(expectedSize, photo.size());
    }

    @Test
    void testNoMatches() {
        String regexp = "Exp.*";
        Photo photo = new Photo("Test Photo", LocalDateTime.now(), null, new File("test.jpg"));
        assertFalse(photo.matches(regexp));
    }

    @Test
    void testMatchesTitle() {
        String regexp = "Test.*";
        Photo photo = new Photo("Test Photo", LocalDateTime.now(), null, new File("test.jpg"));
        assertTrue(photo.matches(regexp));
    }

    @Test
    void testMatchesFile() {
        String regexp = ".*test.*";
        Photo photo = new Photo("Test Photo", LocalDateTime.now(), null, new File("test.jpg"));
        assertTrue(photo.matches(regexp));
    }

    @Test
    void testEquals() {
        LocalDateTime now = LocalDateTime.now();  // Ensure this is the same for all instances
        File file1 = new File("test1.jpg");
        File file2 = new File("test2.jpg");
        File file3 = new File("test1.jpg");

        Photo photo1 = new Photo("Test", now, null, file1);
        Photo photo2 = new Photo("Test", now, null, file2);
        Photo photo3 = new Photo("Test", now, null, file3);

        System.out.println("photo1.equals(photo2): " + photo1.equals(photo2));  // Expected false
        System.out.println("photo1.equals(photo3): " + photo1.equals(photo3));  // Expected true

        assertFalse(photo1.equals(photo2));
        assertTrue(photo1.equals(photo3));
    }


    @Test
    void testHashCode() {
        LocalDateTime now = LocalDateTime.now();  // Capture the current time once
        File file1 = new File("test1.jpg");
        File file2 = new File("test1.jpg");
        Photo photo1 = new Photo("Test", now, null, file1);
        Photo photo2 = new Photo("Test", now, null, file2);

        assertEquals(photo1.hashCode(), photo2.hashCode());
    }



    // Additional test cases can be added here if needed
}
