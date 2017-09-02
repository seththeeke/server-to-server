package services.server2;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import humans.Human;
import places.Place;
import services.IPlaceService;

/**
 * Server 1 Owns the Place Data
 * @author seththeeke
 *
 */
public class Server2PlaceService implements IPlaceService{
	
	public Server2PlaceService(){
		System.out.println("Creating Server 2 Place Service");
	}

	@Override
	public List<Place> getPlaces() {
		final Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8090/places")
                                  .request(MediaType.APPLICATION_JSON).get();
        return Place.listFromJSON(response.readEntity(String.class));
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
