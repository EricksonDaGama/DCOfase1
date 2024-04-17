package leiphotos.domain.metadatareader;

import java.io.File;
import java.io.FileNotFoundException;

public enum JpegMetadataReaderFactory {
    INSTANCE;

    public JpegMetadataReader createMetadataReader(File file) throws JpegMetadataException, FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getPath());
        }
        try {
            return new JavaXTMetadataReaderAdapter(file);
        } catch (Exception e) {
            throw new JpegMetadataException("Failed to create JpegMetadataReader: " + e.getMessage());
        }
    }
}
