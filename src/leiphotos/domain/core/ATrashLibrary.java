package leiphotos.domain.core;

import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.core.TrashLibrary;

import java.util.Collection;
import java.util.ArrayList;

public abstract class ATrashLibrary implements TrashLibrary {
    protected Collection<IPhoto> photos = new ArrayList<>();

    @Override
    public Collection<IPhoto> getPhotos() {
        if (cleaningTime()) {
            clean();
        }
        return new ArrayList<>(photos);
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        return photos.add(photo);
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        return photos.remove(photo);
    }

    @Override
    public boolean deleteAll() {
        boolean hadPhotos = !photos.isEmpty();
        photos.clear();
        return hadPhotos;
    }

    protected abstract void clean();
    protected abstract boolean cleaningTime();
}
