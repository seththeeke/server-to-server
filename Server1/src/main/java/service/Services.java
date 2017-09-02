package service;

import services.rest.Server1HumanRestService;
import services.rest.Server1PlaceRestService;

public class Services {
	
	private static String[] services = {
			Server1HumanRestService.class.getCanonicalName(),
			Server1PlaceRestService.class.getCanonicalName()
	};
	
	public static String[] getServices(){
		return services;
	}
}
