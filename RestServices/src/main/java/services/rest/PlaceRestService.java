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
import properties.HumanServiceProperties;
import services.IPlaceService;

@Path("places")
public class PlaceRestService {
	
	@Context
	UriInfo uri;
	
	IPlaceService placeService;
	
	public PlaceRestService(){
		System.out.println("Created PlaceRestService");
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaces(){
        return Response.ok(Place.listToJSON(getPlaceService(this.uri.getBaseUri()).getPlaces())).build();
    }
	
	@GET
	@Path("/{placeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlace(@PathParam("placeId") final String placeId){
        return Response.ok(getPlaceService(this.uri.getBaseUri()).getPlace(placeId).toJSON()).build();
    }

	public IPlaceService getPlaceService(URI myUri){
		int serverPort = myUri.getPort();
		if (serverPort == 8090){
			PlaceServiceProperties services = ConfigFactory.create(PlaceServiceProperties.class);
			String placeService = services.placeService();
			IPlaceService service;
			try {
				service = (IPlaceService) Class.forName(placeService).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				service = null;
			}
			return service;
		}
		else if (serverPort == 8080){
			HumanServiceProperties services = ConfigFactory.create(HumanServiceProperties.class);
			String placeService = services.placeService();
			IPlaceService service;
			try {
				service = (IPlaceService) Class.forName(placeService).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				service = null;
			}
			return service;
		} else{
			return null;
		}
	}
}
