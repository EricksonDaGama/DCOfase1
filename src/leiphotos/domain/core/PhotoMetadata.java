package leiphotos.domain.core;

import leiphotos.utils.RegExpMatchable;

public record PhotoMetadata(GPSLocation location, String cameraMake, String cameraModel,
                            String dateTimeOriginal, String fileSize, String description)
        implements RegExpMatchable {

    @Override
    public boolean matches(String regexp) {
        return description.matches(regexp) ||
                (cameraMake != null && cameraMake.matches(regexp)) ||
                (cameraModel != null && cameraModel.matches(regexp)) ||
                (dateTimeOriginal != null && dateTimeOriginal.matches(regexp)) ||
                (fileSize != null && fileSize.matches(regexp)) ||
                location.matches(regexp);
    }
}
