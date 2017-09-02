package services.server2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import humans.Human;
import services.IHumanService;

/**
 * Server2 Owns the Human Data
 * @author seththeeke
 *
 */
public class Server2HumanService implements IHumanService{
	
	public Server2HumanService(){
		System.out.println("Creating Server 2 Human Service");
	}
	
	public Map<String, Human> humans;

	@Override
	public List<Human> getHumans() {
		return new ArrayList<Human>(getHumansMap().values());
	}

	@Override
	public Human getHumanById(String humanId) {
		return getHumansMap().get(humanId);
	}
	
	private Map<String, Human> generateHumans(){
		Map<String, Human> humans = new HashMap<>();
		humans.put("1", new Human("Sally"));
		humans.put("2", new Human("John"));
		return humans;
	}
	
	private Map<String, Human> getHumansMap(){
		if (this.humans == null){
			this.humans = generateHumans();
		}
		return this.humans;
	}

}
