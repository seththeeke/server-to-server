package services;

import java.util.List;

import places.Place;

public interface IPlaceService {
	
	public List<Place> getPlaces();
	
	public Place getPlace(final String placeId);
}
