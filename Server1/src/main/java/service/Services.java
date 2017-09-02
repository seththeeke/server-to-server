package service;

import services.rest.HumanRestService;
import services.rest.PlaceRestService;

public class Services {
	
	private static String[] services = {
			HumanRestService.class.getCanonicalName(),
			PlaceRestService.class.getCanonicalName()
	};
	
	public static String[] getServices(){
		return services;
	}
}
