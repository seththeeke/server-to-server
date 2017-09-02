package humans;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Human {
	
	private String name;
	
	public Human(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toJSON(){
		JsonObject json = Json.createObjectBuilder()
				     .add("name", getName())
				     .build();
		return json.toString();
	}

	public static Human fromJSON(String humanJSON) {
		JsonReader jsonReader = Json.createReader(new StringReader(humanJSON));
		JsonObject jobj = jsonReader.readObject();   
		return new Human(jobj.getString("name"));
	}
	
	public static String listToJSON(List<Human> humans) {
		String json = "[";
		for (int i = 0; i < humans.size(); i++){
			Human place = humans.get(i);
			json+=place.toJSON();
			if (i != humans.size() - 1){
				json+=",";
			}
		}
		json += "]";
		return json;
	}
	
	public static List<Human> listFromJSON(String humansJSON) {
		JsonReader jsonReader = Json.createReader(new StringReader(humansJSON));
		JsonArray jobj = jsonReader.readArray();
		List<Human> humans = new ArrayList<>();
		for (JsonObject obj : jobj.getValuesAs(JsonObject.class)){
			humans.add(Human.fromJSON(obj.toString()));
		}
		return humans;
	}

}
