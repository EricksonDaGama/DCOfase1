package leiphotos.domain.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public enum PhotoFactory {
    INSTANCE;

    public Photo createPhoto(String title, String pathToPhotoFile) throws FileNotFoundException {
        File photoFile = new File(pathToPhotoFile);
        if (!photoFile.exists()) {
            throw new FileNotFoundException("File " + pathToPhotoFile + " not found.");
        }

        // Suponha que os metadados sejam gerados de alguma forma (dummy para exemplo)
        GPSLocation location = new GPSLocation(0.0, 0.0, "Unknown Location"); // Localização genérica
        PhotoMetadata metadata = new PhotoMetadata(
                location,
                "Unknown", // Fabricante da câmera
                "Unknown", // Modelo da câmera
                LocalDateTime.now().toString(), // Data e hora originais
                String.valueOf(photoFile.length()), // Tamanho do arquivo
                "No description" // Descrição
        );

        return new Photo(title, LocalDateTime.now(), metadata, photoFile);
    }
}
