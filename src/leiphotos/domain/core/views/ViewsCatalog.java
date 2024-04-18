package leiphotos.domain.core.views;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.ViewsType;
import leiphotos.domain.core.views.ILibraryView;

import java.util.HashMap;
import java.util.Map;

public class ViewsCatalog implements IViewsCatalog {
	private MainLibrary mainLibrary;
	private TrashLibrary trashLibrary;
	private Map<ViewsType, ILibraryView> viewsMap;

	public ViewsCatalog(MainLibrary mainLibrary, TrashLibrary trashLibrary) {
		this.mainLibrary = mainLibrary;
		this.trashLibrary = trashLibrary;
		initializeViews();
	}

	private void initializeViews() {
		viewsMap = new HashMap<>();
		// Aqui, inicializamos as diferentes vistas.
		// Por exemplo:
		viewsMap.put(ViewsType.RECENT, new RecentPhotosView(mainLibrary));
		viewsMap.put(ViewsType.FAVORITE, new FavoritePhotosView(mainLibrary));
		viewsMap.put(ViewsType.TRASH, new TrashPhotosView(trashLibrary));
	}

	@Override
	public ILibraryView getView(ViewsType type) {
		return viewsMap.get(type);
	}
}
