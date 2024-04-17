package leiphotos.domain.metadatareader;

/**
 * Custom exception class for handling errors related to JPEG metadata processing.
 */
public class JpegMetadataException extends Exception {

    /**
     * Constructs a new JpegMetadataException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public JpegMetadataException(String message) {
        super(message);
    }
}
