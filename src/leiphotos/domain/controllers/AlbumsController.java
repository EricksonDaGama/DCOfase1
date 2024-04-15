package leiphotos.domain.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import leiphotos.domain.albums.IAlbumsCatalog;
import leiphotos.domain.facade.IAlbumsController;
import leiphotos.domain.facade.IPhoto;


//Class automatically generated so the code compiles
//CHANGE ME

public class AlbumsController implements IAlbumsController {

	public AlbumsController(IAlbumsCatalog albumsCatalog) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createAlbum(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAlbum() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectAlbum(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPhotos(Set<IPhoto> selectedPhotos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePhotos(Set<IPhoto> selectedPhotos) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IPhoto> getPhotos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<String> getSelectedAlbum() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean createSmartAlbum(String name, Predicate<IPhoto> criteria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getAlbumNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
