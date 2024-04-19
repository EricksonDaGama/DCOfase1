//package leiphotos.domain.controllers;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import leiphotos.domain.core.*;
//import leiphotos.domain.facade.IPhoto;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//public class LibrariesControllerTest {
//    private LibrariesController controller;
//    private MainLibrary mainLibrary;
//    private TrashLibrary trashLibrary;
//
//    @BeforeEach
//    void setUp() {
//        mainLibrary = new MainLibrary();
//        trashLibrary = new TrashLibrary();
//        controller = new LibrariesController(mainLibrary, trashLibrary);
//    }
//
//    @Test
//    void testImportPhotoExists() {
//        // Assuming createDefaultPhotoMetadata and Photo constructor work as expected
//        String path = "path/to/existing/photo.jpg";
//        File photoFile = new File(path);
//        photoFile.createNewFile();  // Ensure the file exists
//        Optional<IPhoto> result = controller.importPhoto("Test Photo", path);
//        assertTrue(result.isPresent(), "Photo should be imported successfully");
//        photoFile.delete();  // Clean up the created file
//    }
//
//    @Test
//    void testImportPhotoDoesNotExist() {
//        String path = "path/to/nonexistent/photo.jpg";
//        Optional<IPhoto> result = controller.importPhoto("Test Photo", path);
//        assertTrue(result.isEmpty(), "Non-existent photo should not be imported");
//    }
//
//    @Test
//    void testDeletePhotos() {
//        Set<IPhoto> photos = new HashSet<>();
//        IPhoto photo = new Photo("Test Photo", LocalDateTime.now(), null, new File("test.jpg"));
//        mainLibrary.addPhoto(photo);  // Add photo to the library first
//        photos.add(photo);
//        controller.deletePhotos(photos);
//        assertFalse(mainLibrary.getPhotos().contains(photo), "Photo should be deleted from the main library");
//        assertTrue(trashLibrary.getPhotos().contains(photo), "Deleted photo should be in the trash library");
//    }
//
//    @Test
//    void testEmptyTrash() {
//        IPhoto photo = new Photo("Trash Photo", LocalDateTime.now(), null, new File("trash.jpg"));
//        trashLibrary.addPhoto(photo);
//        controller.emptyTrash();
//        assertTrue(trashLibrary.getPhotos().isEmpty(), "Trash should be emptied");
//    }
//
//    @Test
//    void testToggleFavourite() {
//        IPhoto photo = new Photo("Favorite Photo", LocalDateTime.now(), null, new File("favorite.jpg"));
//        mainLibrary.addPhoto(photo);  // Ensure the photo is in the main library
//        Set<IPhoto> photos = new HashSet<>();
//        photos.add(photo);
//        assertFalse(photo.isFavourite(), "Photo should initially not be favorite");
//        controller.toggleFavourite(photos);
//        assertTrue(photo.isFavourite(), "Photo should be toggled to favorite");
//    }
//
//    @Test
//    void testGetMatches() {
//        IPhoto photo1 = new Photo("Match Photo", LocalDateTime.now(), null, new File("match1.jpg"));
//        IPhoto photo2 = new Photo("Non-Match Photo", LocalDateTime.now(), null, new File("nonmatch.jpg"));
//        mainLibrary.addPhoto(photo1);
//        mainLibrary.addPhoto(photo2);
//        Iterable<IPhoto> matches = controller.getMatches("Match");
//        assertTrue(matches.contains(photo1), "Should contain matching photo");
//        assertFalse(matches.contains(photo2), "Should not contain non-matching photo");
//    }
//}
