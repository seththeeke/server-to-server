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

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

import places.Place;
import properties.Server1ServiceProperties;
import properties.Server2ServiceProperties;
import services.IHumanService;
import services.IPlaceService;
import services.server1.Server1PlaceService;

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
			Server1ServiceProperties services = ConfigFactory.create(Server1ServiceProperties.class);
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
			Server2ServiceProperties services = ConfigFactory.create(Server2ServiceProperties.class);
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
