package services.rest;

import javax.ws.rs.core.Response;

import humans.Human;

public interface IHumanRestService {
	
	public Response getHumans();
	
	public Response getHumanById(String humanId);

}
