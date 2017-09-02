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

import humans.Human;
import properties.PlaceServiceProperties;
import properties.HumanServiceProperties;
import services.IHumanService;
import services.rest.IHumanRestService;

@Path("humans")
public class HumanRestService implements IHumanRestService{
	
	@Context
	UriInfo uri;
	
	IHumanService humanService;
	
	public HumanRestService(){
		System.out.println("Created HumanRestService");
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getHumans(){
        return Response.ok(Human.listToJSON(getHumanService(this.uri.getBaseUri()).getHumans())).build();
    }
	
	@GET
	@Path("/{humanId}")
    @Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getHumanById(@PathParam("humanId") final String humanId){
        return Response.ok(getHumanService(this.uri.getBaseUri()).getHumanById(humanId).toJSON()).build();
    }
	
	public IHumanService getHumanService(URI myUri){
		int serverPort = myUri.getPort();
		if (serverPort == 8090){
			PlaceServiceProperties services = ConfigFactory.create(PlaceServiceProperties.class);
			String humanService = services.humanService();
			IHumanService service;
			try {
				service = (IHumanService) Class.forName(humanService).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				service = null;
			}
			return service;
		}
		else if (serverPort == 8080){
			HumanServiceProperties services = ConfigFactory.create(HumanServiceProperties.class);
			String humanService = services.humanService();
			IHumanService service;
			try {
				service = (IHumanService) Class.forName(humanService).newInstance();
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
