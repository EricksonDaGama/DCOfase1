package leiphotos.domain.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import leiphotos.domain.albums.IAlbumsCatalog;
import leiphotos.domain.facade.IAlbumsController;
import leiphotos.domain.facade.IPhoto;


public class AlbumsController implements IAlbumsController {

	private final IAlbumsCatalog albumsCatalog;
	private String selectedAlbumName;

	public AlbumsController(IAlbumsCatalog albumsCatalog) {
		this.albumsCatalog = albumsCatalog;
		this.selectedAlbumName = null;
	}

	@Override
	public boolean createAlbum(String name) {
		return albumsCatalog.createAlbum(name);
	}

	@Override
	public void removeAlbum() {
		if (selectedAlbumName != null) {
			albumsCatalog.deleteAlbum(selectedAlbumName);
			selectedAlbumName = null;
		}
	}

	@Override
	public void selectAlbum(String name) {
		if (albumsCatalog.containsAlbum(name)) {
			selectedAlbumName = name;
		}
	}

	@Override
	public void addPhotos(Set<IPhoto> selectedPhotos) {
		if (selectedAlbumName != null) {
			albumsCatalog.addPhotos(selectedAlbumName, selectedPhotos);
		}
	}

	@Override
	public void removePhotos(Set<IPhoto> selectedPhotos) {
		if (selectedAlbumName != null) {
			albumsCatalog.removePhotos(selectedAlbumName, selectedPhotos);
		}
	}

	@Override
	public List<IPhoto> getPhotos() {
		if (selectedAlbumName != null) {
			return albumsCatalog.getPhotos(selectedAlbumName);
		} else {
			return List.of();
		}
	}

	@Override
	public Optional<String> getSelectedAlbum() {
		return Optional.ofNullable(selectedAlbumName);
	}

	@Override
	public boolean createSmartAlbum(String name, Predicate<IPhoto> criteria) {
		if (albumsCatalog.containsAlbum(name)){
			return false;
		}

		boolean created = albumsCatalog.createAlbum(name);
		if (created) {
			albumsCatalog.addPhotos(name, getPhotos().stream()
					.filter(criteria)
					.collect(Collectors.toSet()));

		}

		return created;
	}

	@Override
	public Set<String> getAlbumNames() {
		return albumsCatalog.getAlbumsNames();
	}

	public String toString() {
		int numberOfAlbums = albumsCatalog.getAlbumsNames().size();
		return String.format("Albums Controller [Selected Album: %s, Total Albums: %d]",
				selectedAlbumName != null ? selectedAlbumName : "None",
				numberOfAlbums);
	}

}
