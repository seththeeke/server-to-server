package properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:services.properties" })
public interface Server1ServiceProperties extends Config{
	
	@Key("human.server1.service")
    String humanService();
	
	@Key("place.server1.service")
    String placeService();
}
