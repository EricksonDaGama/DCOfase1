package leiphotos.domain.core.views;

import leiphotos.domain.facade.IPhoto;
import java.util.Comparator;
import java.util.List;

/**
 * Interface ILibraryView represents views of libraries, allowing customization of photo sorting
 * and filtering based on various criteria.
 */
public interface ILibraryView {
    /**
     * Sets the comparator used to define the order of photos in the view.
     *
     * @param c the comparator that defines the photo ordering
     */
    void setComparator(Comparator<IPhoto> c);

    /**
     * Returns the number of photos that belong to the view.
     *
     * @return the number of photos in the view
     */
    int numberOfPhotos();

    /**
     * Returns a list of photos in the view ordered by the current sorting criterion.
     *
     * @return a list of ordered photos in the view
     */
    List<IPhoto> getPhotos();

    /**
     * Returns a list of photos in the view that match a given regular expression, ordered by the current sorting criterion.
     *
     * @param regexp the regular expression to match photos against
     * @return a list of photos matching the regular expression, ordered according to the current comparator
     */
    List<IPhoto> getMatches(String regexp);
}
