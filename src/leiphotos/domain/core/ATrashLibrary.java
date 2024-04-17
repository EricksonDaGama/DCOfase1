package leiphotos.domain.core;

import leiphotos.domain.facade.IPhoto;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

public abstract class ATrashLibrary implements TrashLibrary {
    private Collection<IPhoto> photos = new ArrayList<>();

    @Override
    public Collection<IPhoto> getPhotos() {
        if (cleaningTime()) {
            clean();
        }
        return new ArrayList<>(photos);
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
