package leiphotos.domain.controllers;

import java.util.Optional;
import java.util.Set;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;

//Class automatically generated so the code compiles
//CHANGE ME
public class LibrariesController implements ILibrariesController {

	public LibrariesController(MainLibrary mainLib, TrashLibrary trashLib) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deletePhotos(Set<IPhoto> selectedPhotos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void emptyTrash() {
		// TODO Auto-generated method stub

	}

	@Override
	public void toggleFavourite(Set<IPhoto> selectedPhotos) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<IPhoto> getMatches(String regExp) {
		// TODO Auto-generated method stub
		return null;
	}

}
