package properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:services.properties" })
public interface HumanServiceProperties extends Config{
	
	@Key("human.server2.service")
    String humanService();
	
	@Key("place.server2.service")
    String placeService();
}
