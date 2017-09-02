package places;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Place {
	
	private String name;
	
	public Place(String name){
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

	public static Place fromJSON(String placeJSON) {
		JsonReader jsonReader = Json.createReader(new StringReader(placeJSON));
		JsonObject jobj = jsonReader.readObject();   
		return new Place(jobj.getString("name"));
	}

}
