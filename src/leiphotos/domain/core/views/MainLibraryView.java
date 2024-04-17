package leiphotos.domain.core.views;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.LibraryEvent;
import leiphotos.utils.Listener;
import leiphotos.domain.facade.IPhoto;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainLibraryView extends ALibraryView implements Listener<LibraryEvent> {
    private List<IPhoto> cachedPhotos;
    private MainLibrary mainLibrary;

    public MainLibraryView(MainLibrary mainLibrary, Predicate<IPhoto> belongsToView, Comparator<IPhoto> comparator) {
        super(mainLibrary, belongsToView);
        this.mainLibrary = mainLibrary;
        this.comparator = comparator;
        this.cachedPhotos = new ArrayList<>();
        this.mainLibrary.addListener(this); // Correção: usar addListener
        refreshCache();
    }

    private void refreshCache() {
        cachedPhotos = mainLibrary.getPhotos().stream()
                .filter(belongsToView)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public void processEvent(LibraryEvent event) {
        // Refresh cache on any event that changes the library content
        refreshCache();
    }

    @Override
    public List<IPhoto> getPhotos() {
        return new ArrayList<>(cachedPhotos);
    }

    @Override
    public List<IPhoto> getMatches(String regexp) {
        return cachedPhotos.stream()
                .filter(photo -> photo.matches(regexp))
                .collect(Collectors.toList());
    }

    @Override
    public int numberOfPhotos() {
        return cachedPhotos.size();
    }
}
