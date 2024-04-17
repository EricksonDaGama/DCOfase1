package leiphotos.domain.core;

import leiphotos.domain.facade.IPhoto;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Collection;
import java.util.stream.Collectors;

public class RecentlyDeletedLibrary extends ATrashLibrary {
	private LocalDateTime lastCleanTime = LocalDateTime.now();
	private static final Duration CLEAN_INTERVAL = Duration.ofMinutes(5);  // Ajuste conforme necessário

	@Override
	protected void clean() {
		LocalDateTime now = LocalDateTime.now();
		photos.removeIf(photo -> Duration.between(photo.addedDate(), now).toMinutes() > 5);  // Substituir 5 pelo seu valor de tempo de retenção
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
}
