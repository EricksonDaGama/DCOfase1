package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

public record GPSLocation(double latitude, double longitude, String description)
        implements GPSCoordinates, RegExpMatchable {

    @Override
    public double latitude() {
        return latitude;
    }

    @Override
    public double longitude() {
        return longitude;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public boolean matches(String regexp) {
        return description.matches(regexp);
    }
}
