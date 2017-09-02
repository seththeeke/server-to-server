package services;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import humans.Human;

/**
 * Server2 Owns the Human Data
 * @author seththeeke
 *
 */
public class Server1HumanService implements IHumanService{

	@Override
	public List<Human> getHumans() {
		final Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/humans")
                                  .request(MediaType.APPLICATION_JSON).get();
        return Human.listFromJSON(response.readEntity(String.class));
	}

	@Override
	public Human getHumanById(String humanId) {
		final Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/humans/" + humanId)
                                  .request(MediaType.APPLICATION_JSON).get();
        String humanJSON = response.readEntity(String.class);
        return Human.fromJSON(humanJSON);
	}

}
