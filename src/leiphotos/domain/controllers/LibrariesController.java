package leiphotos.domain.controllers;

import leiphotos.domain.core.*;
import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class LibrariesController implements ILibrariesController {
	private MainLibrary mainLibrary;
	private TrashLibrary trashLibrary;

	public LibrariesController(MainLibrary mainLib, TrashLibrary trashLib) {
		this.mainLibrary = mainLib;
		this.trashLibrary = trashLib;
	}

	@Override
	public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
		File photoFile = new File(pathToPhotoFile);
		if (photoFile.exists()) {
			LocalDateTime now = LocalDateTime.now();
			// Example of creating photo metadata, adjust as necessary for your project
			PhotoMetadata metadata = new PhotoMetadata(new GPSLocation(0.0, 0.0, "Default location"),
					"Camera Brand", "Camera Model", now.toString(),
					"File Size", "Description");
			IPhoto photo = new Photo(title, now, metadata, photoFile);
			if (mainLibrary.addPhoto(photo)) {
				return Optional.of(photo);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deletePhotos(Set<IPhoto> selectedPhotos) {
		for (IPhoto photo : selectedPhotos) {
			if (mainLibrary.deletePhoto(photo)) {
				trashLibrary.addPhoto(photo);
			}
		}
	}

	@Override
	public void emptyTrash() {
		trashLibrary.deleteAll();
	}

	@Override
	public void toggleFavourite(Set<IPhoto> selectedPhotos) {
		for (IPhoto photo : selectedPhotos) {
			if (mainLibrary.getPhotos().contains(photo)) {
				photo.toggleFavourite();
			}
		}
	}

	@Override
	public Iterable<IPhoto> getMatches(String regExp) {
		return mainLibrary.getMatches(regExp);
	}

	@Override
	public String toString() {
		return "Libraries Controller State:\n" +
				"Main Library: " + mainLibrary +
				"\nTrash Library: " + trashLibrary;
	}
}
