
package leiphotos.domain.core;

import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;
import leiphotos.domain.core.PhotoAddedLibraryEvent;
import leiphotos.domain.core.PhotoDeletedLibraryEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainLibrary implements Library {
    private List<IPhoto> photos;
    private List<Listener<LibraryEvent>> listeners;

    public MainLibrary() {
        photos = new ArrayList<>();
        listeners = new ArrayList<>();
    }


        @Override
    public int getNumberOfPhotos() {
        return photos.size();
    }

    public void addListener(Listener<LibraryEvent> listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener<LibraryEvent> listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(LibraryEvent event) {
        for (Listener<LibraryEvent> listener : listeners) {
            listener.processEvent(event);
        }
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        if (photo != null && !photos.contains(photo)) {
            photos.add(photo);
            notifyListeners(new PhotoAddedLibraryEvent(photo, this));
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        if (photos.remove(photo)) {
            notifyListeners(new PhotoDeletedLibraryEvent(photo, this));
            return true;
        }
        return false;
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        return new ArrayList<>(photos);
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        ArrayList<IPhoto> matchedPhotos = new ArrayList<>();
        for (IPhoto photo : photos) {
            if (photo.matches(regexp)) {
                matchedPhotos.add(photo);
            }
        }
        return matchedPhotos;
    }
}
