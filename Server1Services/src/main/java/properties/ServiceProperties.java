package properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:services.properties" })
public interface ServiceProperties extends Config{
	
	@Key("human.service")
    String humanService();
	
	@Key("place.service")
    String placeService();
}
