package leiphotos.domain.controllers;

import leiphotos.domain.core.views.ILibraryView;
import leiphotos.domain.core.views.IViewsCatalog;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.IViewsController;
import leiphotos.domain.facade.ViewsType;
import java.util.Comparator;
import java.util.List;

public class ViewsController implements IViewsController {
    private IViewsCatalog viewsCatalog;

    public ViewsController(IViewsCatalog viewsCatalog) {
        this.viewsCatalog = viewsCatalog;
    }

    @Override
    public List<IPhoto> getPhotos(ViewsType viewType) {
        return viewsCatalog.getView(viewType).getPhotos();
    }

    @Override
    public List<IPhoto> getMatches(ViewsType viewType, String regexp) {
        return viewsCatalog.getView(viewType).getMatches(regexp);
    }

    @Override
    public void setSortingCriteria(ViewsType viewType, Comparator<IPhoto> criteria) {
        viewsCatalog.getView(viewType).setComparator(criteria);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Views Controller State:\n");
        for (ViewsType viewType : ViewsType.values()) {
            try {
                ILibraryView view = viewsCatalog.getView(viewType);
                builder.append(viewType.name())
                        .append(": ")
                        .append(view.toString())
                        .append("\n");
            } catch (IllegalArgumentException e) {
                builder.append(viewType.name())
                        .append(": Unknown view type\n");
            }
        }
        return builder.toString();
    }



}
