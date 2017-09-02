package humans;

import java.io.StringReader;

import javax.json.Json;
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

}
