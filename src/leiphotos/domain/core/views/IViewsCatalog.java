package leiphotos.domain.core.views;

import leiphotos.domain.facade.IPhoto;
import java.util.List;

/**
 * Interface for accessing different types of photo views.
 * This interface provides methods to get recent, favorite, and deleted photos.
 */
public interface IViewsCatalog {
    /**
     * Gets a list of recent photos.
     *
     * @return a list of IPhoto representing recent photos.
     */
    List<IPhoto> getRecentPhotos();

    /**
     * Gets a list of favorite photos.
     *
     * @return a list of IPhoto representing favorite photos.
     */
    List<IPhoto> getFavoritePhotos();

    /**
     * Gets a list of deleted photos.
     *
     * @return a list of IPhoto representing deleted photos.
     */
    List<IPhoto> getDeletedPhotos();
}
