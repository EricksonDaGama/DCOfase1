package leiphotos.domain.core.views;

import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.core.Library;  // interface Library
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Abstract class ALibraryView provides a skeleton implementation of the ILibraryView interface.
 * This class uses a predicate to determine if a photo belongs to the view and interacts directly
 * with the library to access photos. It uses a sorting criterion, defaulting to photo size.
 */
public abstract class ALibraryView implements ILibraryView {
    protected Library library;  // Reference to the library
    protected Predicate<IPhoto> belongsToView;
    protected Comparator<IPhoto> comparator = Comparator.comparingLong(IPhoto::size);

    public ALibraryView(Library library, Predicate<IPhoto> belongsToView) {
        this.library = library;
        this.belongsToView = belongsToView;
    }

    @Override
    public void setComparator(Comparator<IPhoto> c) {
        this.comparator = c;
    }

    @Override
    public int numberOfPhotos() {
        return (int) library.getPhotos().stream().filter(belongsToView).count();
    }

    @Override
    public List<IPhoto> getPhotos() {
        return library.getPhotos().stream()
                .filter(belongsToView)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return library.getPhotos().stream()
                .filter(belongsToView)
                .filter(photo -> photo.matches(regexp))
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
