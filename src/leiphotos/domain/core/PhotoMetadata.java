package leiphotos.domain.core;

import leiphotos.utils.RegExpMatchable;

public class PhotoMetadata implements RegExpMatchable {
    private GPSLocation location;
    private String cameraMake;
    private String cameraModel;
    private String dateTimeOriginal;
    private String fileSize;
    private String description;

    public PhotoMetadata(double latitude, double longitude, String description,
                         String cameraMake, String cameraModel, String dateTimeOriginal, String fileSize) {
        this.location = new GPSLocation(latitude, longitude, description);
        this.cameraMake = cameraMake;
        this.cameraModel = cameraModel;
        this.dateTimeOriginal = dateTimeOriginal;
        this.fileSize = fileSize;
        this.description = description;
    }

    public GPSLocation getLocation() {
        return location;
    }

    public String getCameraMake() {
        return cameraMake;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public String getDateTimeOriginal() {
        return dateTimeOriginal;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getDescription() {
        return description;
    }

    public boolean matches(String regexp) {
        // Ajustando para usar o novo método getDescription de GPSLocation
        return location.getDescription().matches(regexp) ||
                cameraMake != null && cameraMake.matches(regexp) ||
                cameraModel != null && cameraModel.matches(regexp) ||
                dateTimeOriginal != null && dateTimeOriginal.matches(regexp);
    }

    @Override
    public String toString() {
        return "PhotoMetadata{" +
                "location=" + location +
                ", cameraMake='" + cameraMake + '\'' +
                ", cameraModel='" + cameraModel + '\'' +
                ", dateTimeOriginal='" + dateTimeOriginal + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
