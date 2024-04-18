package leiphotos.domain.core;

import static org.junit.jupiter.api.Assertions.*;

import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;

class MainLibraryTest {

    private MainLibrary library;

    @BeforeEach
    void setUp() {
        library = new MainLibrary();
    }

    @Test
    void testAddPhoto() {
        IPhoto photo = new MockPhoto(new MockFile("dummy.jpg", 1024));
        assertTrue(library.addPhoto(photo));
        assertEquals(1, library.getNumberOfPhotos());
        assertTrue(library.getPhotos().contains(photo));
    }

    @Test
    void testAddDuplicatePhoto() {
        IPhoto photo = new MockPhoto(new MockFile("duplicate.jpg", 1024));
        library.addPhoto(photo);
        assertFalse(library.addPhoto(photo)); // Adding the same photo should fail
        assertEquals(1, library.getNumberOfPhotos()); // Size should not increase
    }

    @Test
    void testDeletePhoto() {
        IPhoto photo = new MockPhoto(new MockFile("delete.jpg", 1024));
        library.addPhoto(photo);
        assertTrue(library.deletePhoto(photo));
        assertFalse(library.getPhotos().contains(photo));
    }

    @Test
    void testDeleteNonExistentPhoto() {
        IPhoto photo = new MockPhoto(new MockFile("nonexistent.jpg", 1024));
        assertFalse(library.deletePhoto(photo)); // Deleting a non-existent photo should return false
    }

    @Test
    void testGetPhotos() {
        IPhoto photo1 = new MockPhoto(new MockFile("photo1.jpg", 1024));
        IPhoto photo2 = new MockPhoto(new MockFile("photo2.jpg", 2048));
        library.addPhoto(photo1);
        library.addPhoto(photo2);
        Collection<IPhoto> photos = library.getPhotos();
        assertTrue(photos.contains(photo1) && photos.contains(photo2) && photos.size() == 2);
    }

    @Test
    void testGetMatches() {
        IPhoto photo1 = new MockPhoto(new MockFile("match1.jpg", 1024), true);
        IPhoto photo2 = new MockPhoto(new MockFile("noMatch.jpg", 2048), false);
        library.addPhoto(photo1);
        library.addPhoto(photo2);
        Collection<IPhoto> matches = library.getMatches(".*match.*");
        assertTrue(matches.contains(photo1) && !matches.contains(photo2));
    }

    @Test
    void testNotificationOnAdd() {
        MockListener mockListener = new MockListener();
        library.addListener(mockListener);
        IPhoto photo = new MockPhoto(new MockFile("event.jpg", 1024));
        library.addPhoto(photo);
        assertTrue(mockListener.isNotified());
    }

    // Helper class for testing notifications
    static class MockListener implements Listener<LibraryEvent> {
        private boolean notified = false;

        @Override
        public void processEvent(LibraryEvent event) {
            notified = true;
        }

        public boolean isNotified() {
            return notified;
        }
    }
}
