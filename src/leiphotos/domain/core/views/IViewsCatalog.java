package leiphotos.domain.core.views;

import leiphotos.domain.facade.ViewsType;
import leiphotos.domain.core.views.ILibraryView;

/**
 * Interface for a catalog of library views.
 * Provides methods to retrieve specific views based on the type.
 */
public interface IViewsCatalog {

    /**
     * Retrieves a library view based on the specified type.
     *
     * @param type the type of the view to retrieve, defined by the ViewsType enum.
     * @return the library view corresponding to the specified type.
     */
    ILibraryView getView(ViewsType type);
}
