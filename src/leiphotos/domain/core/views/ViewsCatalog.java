package leiphotos.domain.core.views;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.ViewsType;

import java.util.Comparator;
import java.util.function.Predicate;

public class ViewsCatalog implements IViewsCatalog {
    private MainLibrary mainLibrary;
    private TrashLibrary trashLibrary;

    public ViewsCatalog(MainLibrary mainLibrary, TrashLibrary trashLibrary) {
        this.mainLibrary = mainLibrary;
        this.trashLibrary = trashLibrary;
    }

    @Override
    public ILibraryView getView(ViewsType type) {
        switch (type) {
            case ALL_MAIN:
                return new AllMainView(mainLibrary);
            case ALL_TRASH:
                return new AllTrashView(trashLibrary);
            case FAVOURITES_MAIN:
                return new FavouritesMainView(mainLibrary);
            case MOST_RECENT:
                return new MostRecentView(mainLibrary);
            default:
                throw new IllegalArgumentException("Unknown view type");
        }
    }

    // Subclasses of ALibraryView for each view type
    class AllMainView extends ALibraryView {
        public AllMainView(MainLibrary library) {
            super(library, p -> true); // All photos are included
            setComparator(Comparator.comparing(IPhoto::capturedDate).reversed());
        }
    }

    class AllTrashView extends ALibraryView {
        public AllTrashView(TrashLibrary library) {
            super(library, p -> true); // All photos are included
            setComparator(Comparator.comparing(IPhoto::capturedDate).reversed());
        }
    }

    class FavouritesMainView extends ALibraryView {
        public FavouritesMainView(MainLibrary library) {
            super(library, IPhoto::isFavourite); // Only favourite photos
            setComparator(Comparator.comparing(IPhoto::capturedDate).reversed());
        }
    }

    class MostRecentView extends ALibraryView {
        public MostRecentView(MainLibrary library) {
            super(library, p -> true);
            setComparator(Comparator.comparing(IPhoto::capturedDate).reversed());
        }
    }
}
