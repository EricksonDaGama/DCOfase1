package leiphotos.domain.albums;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.core.LibraryEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a normal album which can contain a unique set of photos.
 */
public class Album extends AAlbum {

    public Album(String name, MainLibrary library) {
        super(name, library);
    }

    @Override
    public boolean addPhotos(Set<IPhoto> selectedPhotos) {
        // Only add photos that are not already in the album
        boolean added = false;
        for (IPhoto photo : selectedPhotos) {
            if (!this.photos.contains(photo) && this.library.getPhotos().contains(photo)) {
                this.photos.add(photo);
                added = true;
            }
        }
        return added;
    }

    @Override
    public boolean removePhotos(Set<IPhoto> selectedPhotos) {
        return photos.removeAll(selectedPhotos);
    }

    @Override
    public void processEvent(LibraryEvent event) {
        // Automatically remove photos from the album if they are deleted from the library
        if (event instanceof PhotoDeletedLibraryEvent && photos.contains(event.getPhoto())) {
            photos.remove(event.getPhoto());
        }
    }

    @Override
    public List<IPhoto> getPhotos() {
        return photos.stream().collect(Collectors.toList());
    }
}
