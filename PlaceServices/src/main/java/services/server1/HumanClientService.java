package services.server1;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.aeonbits.owner.ConfigFactory;

import humans.Human;
import properties.ServerProperties;
import services.IHumanService;

public class HumanClientService implements IHumanService{

	@Override
	public List<Human> getHumans() {
		final Client client = ClientBuilder.newClient();
        Response response = client.target(getHumansPath())
                                  .request(MediaType.APPLICATION_JSON).get();
        return Human.listFromJSON(response.readEntity(String.class));
	}

	@Override
	public Human getHumanById(String humanId) {
		final Client client = ClientBuilder.newClient();
        Response response = client.target(getHumansPath() + "/" + humanId)
                                  .request(MediaType.APPLICATION_JSON).get();
        String humanJSON = response.readEntity(String.class);
        return Human.fromJSON(humanJSON);
	}
	
	private String getHumansPath(){
		ServerProperties properties = ConfigFactory.create(ServerProperties.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(properties.getHumanProtocol());
		stringBuilder.append("://");
		stringBuilder.append(properties.getHumanContext());
		stringBuilder.append(":");
		stringBuilder.append(properties.getHumanPort());
		stringBuilder.append("/humans");
		return stringBuilder.toString();
	}

}
