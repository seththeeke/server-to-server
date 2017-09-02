package services;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import humans.Human;
import places.Place;

/**
 * Server 1 Owns the Place Data
 * @author seththeeke
 *
 */
public class Server2PlaceService implements IPlaceService{

	@Override
	public List<Place> getPlaces() {
		final Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8090/places")
                                  .request(MediaType.APPLICATION_JSON).get();
        return (List<Place>) response.getEntity();
	}

	@Override
	public Place getPlace(String placeId) {
        final Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8090/places/" + placeId)
                                  .request(MediaType.APPLICATION_JSON).get();
        String placeJSON = response.readEntity(String.class);
        return Place.fromJSON(placeJSON);
	}

}
