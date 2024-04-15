package leiphotos.domain.core;


//Class automatically generated so the code compiles
//CHANGE ME
public class MainLibrary implements Library {
    private ArrayList<IPhoto> photos;

    public MainLibrary() {
        photos = new ArrayList<>();
    }

    @Override
    public int getNumberOfPhotos() {
        return photos.size();
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        if (photo != null && !photos.contains(photo)) {
            photos.add(photo);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        return photos.remove(photo);
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        return new ArrayList<>(photos);
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        ArrayList<IPhoto> matchedPhotos = new ArrayList<>();
        for (IPhoto photo : photos) {
            if (photo.toString().matches(regexp)) {
                matchedPhotos.add(photo);
            }
        }
        return matchedPhotos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Library Contents:\n");
        for (IPhoto photo : photos) {
            sb.append(photo.toString()).append("\n");
        }
        return sb.toString();
    }
}

