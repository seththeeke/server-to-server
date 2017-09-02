package services.server1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import places.Place;
import services.IPlaceService;

/**
 * Server 1 Owns the Place Data
 * @author seththeeke
 *
 */
public class Server1PlaceService implements IPlaceService{
	
	public Server1PlaceService(){
		System.out.println("Creating Server 1 Place Service");
	}

	private Map<String, Place> places;

	@Override
	public List<Place> getPlaces() {
		return new ArrayList<Place>(getPlacesMap().values());
	}

	@Override
	public Place getPlace(String placeId) {
		return getPlacesMap().get(placeId);
	}
	
	private Map<String, Place> generatePlaces(){
		Map<String, Place> places = new HashMap<>();
		places.put("1", new Place("Town"));
		places.put("2", new Place("City"));
		return places;
	}
	
	private Map<String, Place> getPlacesMap(){
		if (this.places == null){
			this.places = generatePlaces();
		}
		return this.places;
	}

}
