package service;

import services.rest.Server2HumanRestService;
import services.rest.Server2PlaceRestService;

public class Services {
	
	private static String[] services = {
			Server2HumanRestService.class.getCanonicalName(),
			Server2PlaceRestService.class.getCanonicalName()
	};
	
	public static String[] getServices(){
		return services;
	}
}
