package leiphotos.domain.controllers;

import leiphotos.domain.core.*;
import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LibrariesController implements ILibrariesController {
	private MainLibrary mainLibrary;
	private TrashLibrary trashLibrary;

	public LibrariesController(MainLibrary mainLibrary, TrashLibrary trashLibrary) {
		this.mainLibrary = mainLibrary;
		this.trashLibrary = trashLibrary;
	}

	@Override
	public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
		File photoFile = new File(pathToPhotoFile);
		if (photoFile.exists()) {
			// Criação da data atual
			LocalDateTime now = LocalDateTime.now();

			// Supondo que você tenha uma maneira de criar ou obter PhotoMetadata apropriado
			PhotoMetadata metadata = createDefaultPhotoMetadata(now);

			// Criação da foto com todos parâmetros necessários
			IPhoto photo = new Photo(title, now, metadata, photoFile);
			if (mainLibrary.addPhoto(photo)) {
				return Optional.of(photo);
			}
		}
		return Optional.empty();
	}


	private PhotoMetadata createDefaultPhotoMetadata(LocalDateTime dateAdded) {
		// Exemplo de criação de metadados padrão, precisa ser ajustado conforme a realidade do seu projeto
		GPSLocation location = new GPSLocation(0.0, 0.0, "Default description");
		return new PhotoMetadata(location, "Unknown make", "Unknown model", dateAdded.toString(), "Unknown size", "No additional description");
	}



	@Override
	public void deletePhotos(Set<IPhoto> selectedPhotos) {
		selectedPhotos.forEach(photo -> {
			if (mainLibrary.deletePhoto(photo)) {
				trashLibrary.addPhoto(photo);
			}
		});
	}

	@Override
	public void emptyTrash() {
		trashLibrary.deleteAll();
	}

	@Override
	public void toggleFavourite(Set<IPhoto> selectedPhotos) {
		selectedPhotos.forEach(photo -> {
			if (mainLibrary.getPhotos().contains(photo)) {
				photo.toggleFavourite();
			}
		});
	}

	@Override
	public Iterable<IPhoto> getMatches(String regExp) {
		return mainLibrary.getPhotos().stream()
				.filter(photo -> photo.matches(regExp))
				.collect(Collectors.toList());
	}
}
