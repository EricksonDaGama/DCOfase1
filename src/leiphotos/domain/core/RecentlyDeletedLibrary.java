package leiphotos.domain.core;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import leiphotos.domain.facade.IPhoto;

public class RecentlyDeletedLibrary implements Library, TrashLibrary {
    private ConcurrentHashMap<IPhoto, Long> photoMap;
    private DelayQueue<DelayedPhoto> trashQueue;

    public RecentlyDeletedLibrary() {
        photoMap = new ConcurrentHashMap<>();
        trashQueue = new DelayQueue<>();
    }

    @Override
    public int getNumberOfPhotos() {
        return photoMap.size();
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        if (photo != null && !photoMap.containsKey(photo)) {
            photoMap.put(photo, System.currentTimeMillis());
            trashQueue.add(new DelayedPhoto(photo, 15000));  // 15 seconds delay
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        if (photo != null && photoMap.containsKey(photo)) {
            photoMap.remove(photo);
            return true;
        }
        return false;
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        expirePhotos();
        return photoMap.keySet();
    }

    @Override
    public boolean deleteAll() {
        trashQueue.clear();
        photoMap.clear();
        return true;
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        expirePhotos();
        return photoMap.keySet().stream()
            .filter(photo -> photo.matches(regexp))
            .collect(Collectors.toList());
    }

    private void expirePhotos() {
        DelayedPhoto delayedPhoto;
        while ((delayedPhoto = trashQueue.poll()) != null) {
            photoMap.remove(delayedPhoto.getPhoto());
        }
    }

    private static class DelayedPhoto implements Delayed {
        private final IPhoto photo;
        private final long expirationTime;

        public DelayedPhoto(IPhoto photo, long delay) {
            this.photo = photo;
            this.expirationTime = System.currentTimeMillis() + delay;
        }

        public IPhoto getPhoto() {
            return photo;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long delay = expirationTime - System.currentTimeMillis();
            return unit.convert(delay, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            if (this.expirationTime < ((DelayedPhoto) other).expirationTime) {
                return -1;
            } else if (this.expirationTime > ((DelayedPhoto) other).expirationTime) {
                return 1;
            }
            return 0;
        }
    }
}


	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

}
