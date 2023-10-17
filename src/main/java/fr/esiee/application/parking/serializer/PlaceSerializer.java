package fr.esiee.application.parking.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.esiee.application.parking.Place;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.parking.reference.TypePlace;
import fr.esiee.application.vehicule.Vehicule;

public class PlaceSerializer implements JsonSerializer<Place>, JsonDeserializer<Place> {

	@Override
	public Place deserialize(JsonElement ele, Type arg1, JsonDeserializationContext context) throws JsonParseException {

		JsonObject obj = ele.getAsJsonObject() ;
		
		int id = obj.get("id").getAsInt() ;
		TypePlace type = TypePlace.valueOf(obj.get("type").getAsString()) ;
		Vehicule v = context.deserialize(obj.get("vehicule"), Vehicule.class) ;
		Place p = new Place(id, type);
		
		try {
			if(v != null) p.setVehicule(v);
		} catch (ExceptionPlaceIsOccuped | ExceptionUnsuitablePlaceForThisVehicule e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public JsonElement serialize(Place p, Type arg1, JsonSerializationContext context) {
		
		JsonObject obj = new JsonObject() ;
		
		obj.addProperty("id", p.getIdPlace());
		obj.addProperty("type", p.getType().toString());
		obj.add("vehicule", context.serialize(p.getVehicule()));
		
		return obj;
	}

	
	
}
