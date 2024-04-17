package leiphotos.domain.albums;

import leiphotos.domain.facade.IPhoto;

import java.util.List;
import java.util.Set;

/**
 * Interface for managing a catalog of albums.
 */
public interface IAlbumsCatalog {

    /**
     * Creates a new album with the given name and adds it to the catalog.
     * @param albumName The name of the album to create.
     * @return true if the album was created successfully, false if an album with this name already exists.
     */
    boolean createAlbum(String albumName);

    /**
     * Deletes an album with the given name from the catalog.
     * @param albumName The name of the album to delete.
     * @return true if the album was successfully deleted, false if no album with this name exists.
     */
    boolean deleteAlbum(String albumName);

    /**
     * Checks if the catalog contains an album with the given name.
     * @param albumName The name of the album to check.
     * @return true if an album with this name exists in the catalog.
     */
    boolean containsAlbum(String albumName);

    /**
     * Adds a set of photos to the album with the given name.
     * @param albumName The name of the album to which photos will be added.
     * @param selectedPhotos The set of photos to add.
     * @return true if the operation was successful and the album exists.
     */
    boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos);

    /**
     * Removes a set of photos from the album with the given name.
     * @param albumName The name of the album from which photos will be removed.
     * @param selectedPhotos The set of photos to remove.
     * @return true if the operation was successful and the album exists.
     */
    boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos);

    /**
     * Retrieves the photos from the album with the given name.
     * @param albumName The name of the album whose photos are to be retrieved.
     * @return a list of photos from the specified album, or an empty list if no such album exists.
     */
    List<IPhoto> getPhotos(String albumName);

    /**
     * Gets the names of all albums in the catalog.
     * @return a set of album names.
     */
    Set<String> getAlbumsNames();
}
