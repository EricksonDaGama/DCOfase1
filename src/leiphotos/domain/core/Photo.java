package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.RegExpMatchable;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class Photo implements IPhoto, RegExpMatchable {
    private String title;
    private LocalDateTime dateAddedLib;
    private PhotoMetadata metadata;
    private File pathToFile;
    private boolean isFavorite;

    public Photo(String title, LocalDateTime dateAddedLib, PhotoMetadata metadata, File pathToFile) {
        this.title = title;
        this.dateAddedLib = dateAddedLib;
        this.metadata = metadata;
        this.pathToFile = pathToFile;
        this.isFavorite = false;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public LocalDateTime capturedDate() {
        return LocalDateTime.parse(metadata.dateTimeOriginal()); // Assume this is the capture date
    }

    @Override
    public LocalDateTime addedDate() {
        return dateAddedLib;
    }

    @Override
    public boolean isFavourite() {
        return isFavorite;
    }

    @Override
    public void toggleFavourite() {
        isFavorite = !isFavorite;
    }

    @Override
    public Optional<? extends GPSCoordinates> getPlace() {
        return Optional.ofNullable((GPSCoordinates) metadata.location());
    }

    @Override
    public long size() {
        return pathToFile.length();
    }

    @Override
    public File file() {
        return pathToFile;
    }

    @Override
    public boolean matches(String regexp) {
        return (metadata != null && metadata.matches(regexp)) ||
                title.matches(regexp) ||
                pathToFile.getPath().matches(regexp);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title, dateAddedLib, metadata, pathToFile, isFavorite);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Photo other = (Photo) obj;
        return Objects.equals(title, other.title) &&
                Objects.equals(dateAddedLib, other.dateAddedLib) &&
                Objects.equals(metadata, other.metadata) &&
                Objects.equals(pathToFile, other.pathToFile) &&
                isFavorite == other.isFavorite;
    }









}
