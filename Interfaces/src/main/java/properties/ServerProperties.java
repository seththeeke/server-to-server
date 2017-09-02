package properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:/etc/properties/serverConfig.properties",
	"file:~/Desktop/sethweb/server-to-server/server-to-server/Interfaces/src/main/resources/serverConfig.properties" })
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
