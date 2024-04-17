package leiphotos.domain.metadatareader;

import leiphotos.services.JavaXTJpegMetadataReader;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaXTMetadataReaderAdapter implements JpegMetadataReader {
    private JavaXTJpegMetadataReader reader;

    public JavaXTMetadataReaderAdapter(File file) throws JpegMetadataException {
        try {
            this.reader = new JavaXTJpegMetadataReader(file);
        } catch (Exception e) {
            throw new JpegMetadataException("Error creating JavaXTJpegMetadataReader: " + e.getMessage());
        }
    }

    @Override
    public String getCamera() {
        return reader.getCamara(); // Corrigido conforme a classe JavaXTJpegMetadataReader
    }

    @Override
    public String getManufacturer() {
        return reader.getManufacturer(); // Corrigido conforme a classe JavaXTJpegMetadataReader
    }

    @Override
    public LocalDateTime getDate() {
        try {
            String dateTimeStr = reader.getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve date information: " + e.getMessage());
        }
    }

    @Override
    public String getAperture() {
        return reader.getAperture(); // Corrigido conforme a classe JavaXTJpegMetadataReader
    }

    @Override
    public double[] getGpsLocation() {
        return reader.getGPS(); // Corrigido conforme a classe JavaXTJpegMetadataReader
    }
}
