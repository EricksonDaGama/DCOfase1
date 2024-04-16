package leiphotos.domain.core;

public class GPSLocation {
    private double latitude;
    private double longitude;
    private String description;

    // Construtor completo
    public GPSLocation(double latitude, double longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    // Getter para latitude
    public double getLatitude() {
        return latitude;
    }

    // Getter para longitude
    public double getLongitude() {
        return longitude;
    }

    // Getter para descrição
    public String getDescription() {
        return description;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "GPSLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                '}';
    }
}
