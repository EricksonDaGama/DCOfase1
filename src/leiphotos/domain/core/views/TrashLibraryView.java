package leiphotos.domain.core.views;

import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TrashLibraryView extends ALibraryView to provide specific view functionalities
 * for TrashLibrary. It represents views on a library where deleted photos are managed.
 */
public class TrashLibraryView extends ALibraryView {
    private TrashLibrary trashLibrary;

    public TrashLibraryView(TrashLibrary trashLibrary, Predicate<IPhoto> belongsToView, Comparator<IPhoto> comparator) {
        super(trashLibrary, belongsToView);
        this.trashLibrary = trashLibrary;
        this.comparator = comparator;
    }

    @Override
    public List<IPhoto> getPhotos() {
        return trashLibrary.getPhotos().stream()
                .filter(belongsToView)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return trashLibrary.getPhotos().stream()
                .filter(belongsToView)
                .filter(photo -> photo.matches(regexp))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public int numberOfPhotos() {
        return (int) trashLibrary.getPhotos().stream()
                .filter(belongsToView)
                .count();
    }
}
