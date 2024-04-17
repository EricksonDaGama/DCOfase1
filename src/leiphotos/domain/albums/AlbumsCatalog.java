package leiphotos.domain.albums;

import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.core.MainLibrary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of IAlbumsCatalog that manages a collection of albums.
 */
public class AlbumsCatalog implements IAlbumsCatalog {
	private MainLibrary library;
	private Map<String, IAlbum> albums;

	public AlbumsCatalog(MainLibrary library) {
		this.library = library;
		this.albums = new HashMap<>();
	}

	@Override
	public boolean createAlbum(String albumName) {
		if (albums.containsKey(albumName)) {
			return false;
		}
		albums.put(albumName, new Album(albumName, library));
		return true;
	}

	@Override
	public boolean deleteAlbum(String albumName) {
		if (!albums.containsKey(albumName)) {
			return false;
		}
		albums.remove(albumName);
		return true;
	}

	@Override
	public boolean containsAlbum(String albumName) {
		return albums.containsKey(albumName);
	}

	@Override
	public boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos) {
		IAlbum album = albums.get(albumName);
		if (album == null) {
			return false;
		}
		return album.addPhotos(selectedPhotos);
	}

	@Override
	public boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos) {
		IAlbum album = albums.get(albumName);
		if (album == null) {
			return false;
		}
		return album.removePhotos(selectedPhotos);
	}

	@Override
	public List<IPhoto> getPhotos(String albumName) {
		IAlbum album = albums.get(albumName);
		if (album == null) {
			return List.of();
		}
		return album.getPhotos();
	}

	@Override
	public Set<String> getAlbumsNames() {
		return new HashSet<>(albums.keySet());
	}
}
