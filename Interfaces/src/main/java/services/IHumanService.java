package services;

import java.util.List;

import humans.Human;

public interface IHumanService {
	
	public List<Human> getHumans();
	
	public Human getHumanById(String humanId);

}
