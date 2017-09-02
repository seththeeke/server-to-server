package services.rest;

import javax.ws.rs.core.Response;


public interface IHumanRestService {
	
	public Response getHumans() throws InstantiationException, IllegalAccessException, ClassNotFoundException;
	
	public Response getHumanById(String humanId) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
