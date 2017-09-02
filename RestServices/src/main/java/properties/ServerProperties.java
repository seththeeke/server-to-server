package properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:serverConfig.properties" })
public interface ServerProperties extends Config {
	
	@Key("places.protocol")
	public String getPlaceProtocol();
	
	@Key("places.context")
	public String getPlaceContext();
	
	@Key("places.port")
	public int getPlacePort();
	
	@Key("humans.protocol")
	public String getHumanProtocol();
	
	@Key("humans.context")
	public String getHumanContext();
	
	@Key("humans.port")
	public int getHumanPort();

}
