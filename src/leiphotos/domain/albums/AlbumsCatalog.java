package leiphotos.domain.albums;

import leiphotos.domain.core.MainLibrary;

//Class automatically generated so the code compiles
//CHANGE ME
public class AlbumsCatalog implements IAlbumsCatalog {
    private MainLibrary mainLibrary;
    private HashMap<String, List<IPhoto>> albums;

    public AlbumsCatalog(MainLibrary mainLibrary) {
        this.mainLibrary = mainLibrary;
        this.albums = new HashMap<>();
    }

    @Override
    public void createAlbum(String albumName) {
        if (!albums.containsKey(albumName)) {
            albums.put(albumName, new ArrayList<>());
        }
    }

    @Override
    public void deleteAlbum(String albumName) {
        if (albums.containsKey(albumName)) {
            albums.remove(albumName);
        }
    }

    @Override
    public void addPhotoToAlbum(String albumName, IPhoto photo) {
        if (albums.containsKey(albumName) && !albums.get(albumName).contains(photo)) {
            albums.get(albumName).add(photo);
        }
    }

    @Override
    public void removePhotoFromAlbum(String albumName, IPhoto photo) {
        if (albums.containsKey(albumName)) {
            albums.get(albumName).remove(photo);
        }
    }

    @Override
    public List<IPhoto> getPhotosInAlbum(String albumName) {
        return albums.getOrDefault(albumName, new ArrayList<>());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        albums.forEach((name, photos) -> {
            sb.append("Album: ").append(name).append("\n");
            photos.forEach(photo -> sb.append(photo.toString()).append("\n"));
        });
        return sb.toString();
    }
}

