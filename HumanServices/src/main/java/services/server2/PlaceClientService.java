package services.server2;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.aeonbits.owner.ConfigFactory;

import places.Place;
import properties.ServerProperties;
import services.IPlaceService;

public class PlaceClientService implements IPlaceService{

	@Override
	public List<Place> getPlaces() {
		final Client client = ClientBuilder.newClient();
        Response response = client.target(getPlacesPath())
                                  .request(MediaType.APPLICATION_JSON).get();
        return Place.listFromJSON(response.readEntity(String.class));
	}

	@Override
	public Place getPlace(String placeId) {
        final Client client = ClientBuilder.newClient();
        Response response = client.target(getPlacesPath() + "/" + placeId)
                                  .request(MediaType.APPLICATION_JSON).get();
        String placeJSON = response.readEntity(String.class);
        return Place.fromJSON(placeJSON);
	}
	
	private String getPlacesPath(){
		ServerProperties properties = ConfigFactory.create(ServerProperties.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(properties.getPlaceProtocol());
		stringBuilder.append("://");
		stringBuilder.append(properties.getPlaceContext());
		stringBuilder.append(":");
		stringBuilder.append(properties.getPlacePort());
		stringBuilder.append("/places");
		return stringBuilder.toString();
	}

}
