package leiphotos.domain.albums;
//Class automatically generated so the code compiles
//CHANGEd


import leiphotos.domain.facade.IPhoto;
import java.util.List;

public interface IAlbumsCatalog {
    void createAlbum(String albumName);
    void deleteAlbum(String albumName);
    void addPhotoToAlbum(String albumName, IPhoto photo);
    void removePhotoFromAlbum(String albumName, IPhoto photo);
    List<IPhoto> getPhotosInAlbum(String albumName);
}