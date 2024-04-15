package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinate;
import leiphotos.util.RegExprMatchable;

public class GPSLocation implements GPSCoordinate, RegExprMatchable {
    private double latitude;
    private double longitude;
    private String description;

    public GPSLocation(double latitude, double longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean matches(String regexp) {
        return description != null && description.matches(regexp);
    }

    @Override
    public String toString() {
        return "GPSLocation{latitude=" + latitude + ", longitude=" + longitude + ", description='" + description + "'}";
    }
}
