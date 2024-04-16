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

    // Método para corresponder descrições com expressões regulares
    @Override
    public boolean matches(String regexp) {
        return description != null && description.matches(regexp);
    }

    @Override
    public String toString() {
        return "GPSLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                '}';
    }
}
