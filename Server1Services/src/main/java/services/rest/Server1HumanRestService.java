package services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.aeonbits.owner.ConfigFactory;

import humans.Human;
import properties.ServiceProperties;
import services.IHumanService;
import services.Server1HumanService;
import services.rest.IHumanRestService;

@Path("humans")
public class Server1HumanRestService implements IHumanRestService{
	
	IHumanService humanService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getHumans(){
        return Response.ok(Human.listToJSON(getHumanService().getHumans())).build();
    }
	
	@GET
	@Path("/{humanId}")
    @Produces(MediaType.APPLICATION_JSON)
	@Override
    public Response getHumanById(@PathParam("humanId") final String humanId){
        return Response.ok(getHumanService().getHumanById(humanId).toJSON()).build();
    }
	
	public IHumanService getHumanService(){
		ServiceProperties services = ConfigFactory.create(ServiceProperties.class);
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

}
