package services.rest;

import javax.ws.rs.core.Response;

public interface IPlaceRestService {
	
	public Response getPlaces() throws InstantiationException, IllegalAccessException, ClassNotFoundException;
	
	public Response getPlace(String placeId) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
