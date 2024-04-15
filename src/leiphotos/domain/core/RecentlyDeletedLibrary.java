package leiphotos.domain.core;

import java.util.Collection;

import leiphotos.domain.facade.IPhoto;

//Class automatically generated so the code compiles
//CHANGE ME
public class RecentlyDeletedLibrary implements Library, TrashLibrary{

	@Override
	public int getNumberOfPhotos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addPhoto(IPhoto photo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePhoto(IPhoto photo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<IPhoto> getPhotos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IPhoto> getMatches(String regexp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

}
