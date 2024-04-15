package leiphotos.domain.core.views;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;

//Class automatically generated so the code compiles
//CHANGE ME
public class ViewsCatalog implements IViewsCatalog {
    private MainLibrary mainLibrary;
    private TrashLibrary trashLibrary;

    public ViewsCatalog(MainLibrary mainLibrary, TrashLibrary trashLibrary) {
        this.mainLibrary = mainLibrary;
        this.trashLibrary = trashLibrary;
    }

    @Override
    public List<IPhoto> getRecentPhotos() {
        return mainLibrary.getPhotos().stream()
            .sorted((p1, p2) -> p2.capturedDate().compareTo(p1.capturedDate()))
            .limit(10)
            .collect(Collectors.toList());
    }

    @Override
    public List<IPhoto> getFavoritePhotos() {
        return mainLibrary.getPhotos().stream()
            .filter(IPhoto::isFavourite)
            .collect(Collectors.toList());
    }

    @Override
    public List<IPhoto> getDeletedPhotos() {
        return new ArrayList<>(trashLibrary.getPhotos());
    }
}

