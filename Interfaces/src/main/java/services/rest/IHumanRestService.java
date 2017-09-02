package services.rest;

import javax.ws.rs.core.Response;


public interface IHumanRestService {
	
	public Response getHumans();
	
	public Response getHumanById(String humanId);

}
