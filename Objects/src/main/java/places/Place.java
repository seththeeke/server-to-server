package places;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
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

	public static String listToJSON(List<Place> places) {
		String json = "[";
		for (int i = 0; i < places.size(); i++){
			Place place = places.get(i);
			json+=place.toJSON();
			if (i != places.size() - 1){
				json+=",";
			}
		}
		json += "]";
		return json;
	}
	
	public static List<Place> listFromJSON(String placesJSON) {
		JsonReader jsonReader = Json.createReader(new StringReader(placesJSON));
		JsonArray jobj = jsonReader.readArray();
		List<Place> places = new ArrayList<>();
		for (JsonObject obj : jobj.getValuesAs(JsonObject.class)){
			places.add(Place.fromJSON(obj.toString()));
		}
		return places;
	}

}
