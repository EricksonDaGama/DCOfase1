package leiphotos.domain.core.views;


import leiphotos.domain.facade.IPhoto;
import java.util.List;
//Class automatically generated so the code compiles
//CHANGE ME
public interface IViewsCatalog {
    List<IPhoto> getRecentPhotos();
    List<IPhoto> getFavoritePhotos();
    List<IPhoto> getDeletedPhotos();
}