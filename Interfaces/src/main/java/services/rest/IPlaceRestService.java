package services.rest;

import javax.ws.rs.core.Response;

public interface IPlaceRestService {
	
	public Response getPlaces();
	
	public Response getPlace(String placeId);

}
