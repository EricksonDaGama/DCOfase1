package leiphotos.domain.albums;

import leiphotos.domain.core.LibraryEvent;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract class representing an album of photos from a MainLibrary.
 * This class provides default implementations for the IAlbum interface methods.
 */
public abstract class AAlbum implements IAlbum {
    protected String name;
    protected Set<IPhoto> photos;
    protected MainLibrary library;

    public AAlbum(String name, MainLibrary library) {
        this.name = name;
        this.library = library;
        this.photos = new HashSet<>();
    }

    @Override
    public int numberOfPhotos() {
        return photos.size();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IPhoto> getPhotos() {
        return photos.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean addPhotos(Set<IPhoto> selectedPhotos) {
        return photos.addAll(selectedPhotos);
    }

    @Override
    public boolean removePhotos(Set<IPhoto> selectedPhotos) {
        return photos.removeAll(selectedPhotos);
    }

    @Override
    public void processEvent(LibraryEvent event) {
        if (event instanceof PhotoDeletedLibraryEvent && photos.contains(event.getPhoto())) {
            photos.remove(event.getPhoto());
        }
    }
}
