package fr.esiee.application.vehicule.serialize;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.exceptions.ExceptionRegistrationNotAccepted;
import fr.esiee.application.vehicule.reference.TypeVehicule;

public class VehiculeSerializer implements JsonSerializer<Vehicule>, JsonDeserializer<Vehicule> {

	@Override
	public Vehicule deserialize(JsonElement ele, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		
		JsonObject obj = ele.getAsJsonObject() ; 
		TypeVehicule type = TypeVehicule.valueOf(obj.get("type").getAsString()) ;
		String id = obj.get("ID").getAsString() ;
		
		Vehicule v = null ;
		try {
			v = new Vehicule(type, id);
		} catch (NullPointerException | ExceptionRegistrationNotAccepted e) {
			e.printStackTrace();
		}
		
		return v;
	}

	@Override
	public JsonElement serialize(Vehicule v, Type arg1, JsonSerializationContext arg2) {
		
		JsonObject obj = new JsonObject() ;
		
		obj.addProperty("ID", v.getID());
		obj.addProperty("type", v.getTypeVehicule().toString());
		
		return obj;
	}

}
