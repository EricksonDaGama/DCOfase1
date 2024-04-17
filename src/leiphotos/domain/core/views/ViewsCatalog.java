package leiphotos.domain.core.views;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Catalog for managing different types of photo views in a library system.
 */
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
				.sorted(Comparator.comparing(IPhoto::capturedDate).reversed())
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
