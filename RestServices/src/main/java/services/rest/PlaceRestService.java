package services.rest;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.aeonbits.owner.ConfigFactory;
import places.Place;
import properties.PlaceServiceProperties;
import properties.ServerProperties;
import properties.HumanServiceProperties;
import services.IPlaceService;

@Path("places")
public class PlaceRestService implements IPlaceRestService{
	
	@Context
	UriInfo uri;
	
	IPlaceService placeService;
	
	public PlaceRestService(){
		System.out.println("Created PlaceRestService");
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaces() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        return Response.ok(Place.listToJSON(getPlaceService(this.uri.getBaseUri()).getPlaces())).build();
    }
	
	@GET
	@Path("/{placeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlace(@PathParam("placeId") final String placeId) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        return Response.ok(getPlaceService(this.uri.getBaseUri()).getPlace(placeId).toJSON()).build();
    }
	
	public IPlaceService getPlaceService(URI myUri) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		ServerProperties config = ConfigFactory.create(ServerProperties.class);
		int serverPort = myUri.getPort();
		if (serverPort == config.getPlacePort()){
			PlaceServiceProperties services = ConfigFactory.create(PlaceServiceProperties.class);
			return (IPlaceService) Class.forName(services.placeService()).newInstance();
		}
		else if (serverPort == config.getHumanPort()){
			HumanServiceProperties services = ConfigFactory.create(HumanServiceProperties.class);
			return (IPlaceService) Class.forName(services.placeService()).newInstance();
		} else{
			throw new IllegalStateException("Request cannot be processed for any other ports");
		}
	}
}
