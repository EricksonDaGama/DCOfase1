package leiphotos.domain.core;

import leiphotos.domain.facade.IPhoto;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
public class RecentlyDeletedLibrary extends ATrashLibrary {
    private LocalDateTime lastCleanTime = LocalDateTime.now();
    private static final Duration CLEAN_INTERVAL = Duration.ofSeconds(5);  // Consider making this configurable if needed
    private HashSet<String> photoPaths = new HashSet<>();  // Tracking unique file paths

    @Override
    public boolean addPhoto(IPhoto photo) {
        // Use file path as a unique identifier
        String filePath = photo.file().getPath();
        if (photoPaths.contains(filePath)) {
            return false;  // This photo is a duplicate, not adding
        }
        photoPaths.add(filePath);
        photos.add(photo);
        return true;
    }

    @Override
    protected void clean() {
        LocalDateTime now = LocalDateTime.now();
        photos.removeIf(photo -> {
            LocalDateTime added = photo.addedDate();
            if (added == null) {
                return false;
            }
            long seconds = Duration.between(added, now).toSeconds();
            if (seconds > 10) {
                photoPaths.remove(photo.file().getPath());  // Remove path from the set when photo is deleted
                return true;
            }
            return false;
        });
        lastCleanTime = now;
    }

    @Override
    protected boolean cleaningTime() {
        return Duration.between(lastCleanTime, LocalDateTime.now()).compareTo(CLEAN_INTERVAL) > 0;
    }

    @Override
    public int getNumberOfPhotos() {
        return photos.size();
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        return photos.stream()
                .filter(photo -> photo.matches(regexp))
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "***** MAIN PHOTO LIBRARY: " + photos.size() + " photos *****\n" +
                photos.stream()
                        .map(photo -> photo.toString())  // Uso explícito de toString via lambda
                        .collect(Collectors.joining("\n"));
    }

}