package services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.aeonbits.owner.ConfigFactory;

import properties.ServiceProperties;
import services.IPlaceService;
import services.Server2PlaceService;
import services.rest.IPlaceRestService;

@Path("places")
public class Server2PlaceRestService implements IPlaceRestService {

	IPlaceService placeService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaces(){
        return Response.ok(getPlaceService().getPlaces()).build();
    }
	
	@GET
	@Path("/{placeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlace(@PathParam("placeId") final String placeId){
        return Response.ok(getPlaceService().getPlace(placeId).toJSON()).build();
    }

	public IPlaceService getPlaceService(){
		ServiceProperties services = ConfigFactory.create(ServiceProperties.class);
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

}
