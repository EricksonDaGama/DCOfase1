package leiphotos.domain.albums;

import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

import java.util.List;
import java.util.Set;

/**
 * Interface representing albums with photos from a MainLibrary.
 * Each album has a unique name and does not have repeated photos.
 */
public interface IAlbum extends Listener<LibraryEvent> {

    /**
     * Returns the number of photos in the album.
     *
     * @return the number of photos
     */
    int numberOfPhotos();

    /**
     * Returns the name of the album.
     *
     * @return the album name
     */
    String getName();

    /**
     * Returns the photos in the album in a specified order.
     *
     * @return a list of photos
     */
    List<IPhoto> getPhotos();

    /**
     * Adds a set of selected photos from the library to the album.
     *
     * @param selectedPhotos the set of photos to add
     * @return true if the operation was successful
     */
    boolean addPhotos(Set<IPhoto> selectedPhotos);

    /**
     * Removes a set of selected photos from the album.
     *
     * @param selectedPhotos the set of photos to remove
     * @return true if the operation was successful; if a photo does not exist, it is ignored
     */
    boolean removePhotos(Set<IPhoto> selectedPhotos);
}
