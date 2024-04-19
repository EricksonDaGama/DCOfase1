package leiphotos.domain.core;

import leiphotos.utils.RegExpMatchable;

public class PhotoMetadata implements RegExpMatchable {
    private final GPSLocation location;
    private final String cameraMake;
    private final String cameraModel;
    private final String dateTimeOriginal;
    private final String fileSize;
    private final String description;

    public PhotoMetadata(GPSLocation location, String cameraMake, String cameraModel,
                         String dateTimeOriginal, String fileSize, String description) {
        this.location = location;
        this.cameraMake = cameraMake;
        this.cameraModel = cameraModel;
        this.dateTimeOriginal = dateTimeOriginal;
        this.fileSize = fileSize;
        this.description = description;
    }

    public GPSLocation location() {
        return location;
    }

    public String dateTimeOriginal() {
        return dateTimeOriginal;
    }

    @Override
    public boolean matches(String regexp) {
        return description.matches(regexp) ||
                cameraMake.matches(regexp) ||
                cameraModel.matches(regexp) ||
                dateTimeOriginal.matches(regexp) ||
                fileSize.matches(regexp) ||
                location.matches(regexp);
    }
}
