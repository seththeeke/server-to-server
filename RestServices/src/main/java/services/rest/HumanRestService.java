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
import properties.ServerProperties;
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
    public Response getHumans() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        return Response.ok(Human.listToJSON(getHumanService(this.uri.getBaseUri()).getHumans())).build();
    }
	
	@GET
	@Path("/{humanId}")
    @Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getHumanById(@PathParam("humanId") final String humanId) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        return Response.ok(getHumanService(this.uri.getBaseUri()).getHumanById(humanId).toJSON()).build();
    }
	
	public IHumanService getHumanService(URI myUri) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		ServerProperties config = ConfigFactory.create(ServerProperties.class);
		int serverPort = myUri.getPort();
		if (serverPort == config.getPlacePort()){
			PlaceServiceProperties services = ConfigFactory.create(PlaceServiceProperties.class);
			return (IHumanService) Class.forName(services.humanService()).newInstance();
		}
		else if (serverPort == config.getHumanPort()){
			HumanServiceProperties services = ConfigFactory.create(HumanServiceProperties.class);
			return (IHumanService) Class.forName(services.humanService()).newInstance();
		} else{
			throw new IllegalStateException("Request cannot be processed for any other ports");
		}
	}

}
