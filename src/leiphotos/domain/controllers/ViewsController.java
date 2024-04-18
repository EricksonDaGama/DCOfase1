package leiphotos.domain.controllers;

import java.util.Comparator;
import java.util.List;
import leiphotos.domain.core.views.IViewsCatalog;
import leiphotos.domain.core.views.ILibraryView;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.IViewsController;
import leiphotos.domain.facade.ViewsType;

public class ViewsController implements IViewsController {
    private IViewsCatalog viewsCatalog;

    public ViewsController(IViewsCatalog viewsCatalog) {
        this.viewsCatalog = viewsCatalog;
    }

    @Override
    public List<IPhoto> getPhotos(ViewsType viewType) {
        ILibraryView view = viewsCatalog.getView(viewType);
        return view.getPhotos();
    }

    @Override
    public List<IPhoto> getMatches(ViewsType viewType, String regexp) {
        ILibraryView view = viewsCatalog.getView(viewType);
        return view.getMatches(regexp);
    }

    @Override
    public void setSortingCriteria(ViewsType viewType, Comparator<IPhoto> criteria) {
        ILibraryView view = viewsCatalog.getView(viewType);
        view.setComparator(criteria);
    }
}
