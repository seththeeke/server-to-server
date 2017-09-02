package services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.aeonbits.owner.ConfigFactory;

import humans.Human;
import properties.Server1ServiceProperties;
import services.IHumanService;
import services.rest.IHumanRestService;
import services.server1.Server1HumanService;

@Path("humans")
public class HumanRestService implements IHumanRestService{
	
	IHumanService humanService;
	private Server1ServiceProperties serviceProperties;
	
	public HumanRestService(final Server1ServiceProperties serviceProperties){
		this.serviceProperties = serviceProperties;
	}
	
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
		Server1ServiceProperties services = ConfigFactory.create(Server1ServiceProperties.class);
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
