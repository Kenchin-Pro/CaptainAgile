package fr.esiee.application.parking.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.parking.Place;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionPlaceNotFound;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.service.ServiceParkingManager;

public class ParkingSerializer implements JsonSerializer<Parking>, JsonDeserializer<Parking> {

	@Override
	public Parking deserialize(JsonElement e, Type arg1, JsonDeserializationContext c) throws JsonParseException {

		JsonObject obj = e.getAsJsonObject() ;
		
		String name = obj.get("name").getAsString() ;
		int nb2 = obj.get("nb_places_deux_roues").getAsInt() ;
		int nb4 = obj.get("nb_places_quatre_roues").getAsInt() ;
		boolean needPay = obj.get("needToPayBefore").getAsBoolean() ; 
		
		Parking p = new Parking(name, nb2, nb4, needPay) ;
		
		JsonArray array2 = obj.get("list_2_roues").getAsJsonArray() ;
		JsonArray array4 = obj.get("list_4_roues").getAsJsonArray() ;
		
		for(int i=0 ; i< nb2; i++) {
			Place place = c.deserialize(array2.get(i), Place.class);
			if(place != null) {
				
				place.setNeedToPayBefore(needPay);
				
				if(place.getVehicule() != null)
					try {
						p.setVehiculeOnPlaceID(place.getVehicule(), place.getIdPlace());
					} catch (ExceptionPlaceNotFound | ExceptionPlaceIsOccuped
							| ExceptionUnsuitablePlaceForThisVehicule e1) {
						e1.printStackTrace();
					}
			
				p.getListDeuxRoues().set(i, place) ;
				
			}
		}
		

		for(int i=0 ; i< nb4 ; i++) {
			Place place = c.deserialize(array4.get(i), Place.class);
			if(place != null) {
				
				place.setNeedToPayBefore(needPay);
				
				if(place.getVehicule() != null)
					try {
						ServiceParkingManager.vehiculeTakePlace(place.getVehicule(), p, place.getIdPlace());
					} catch (ExceptionPlaceNotFound | ExceptionPlaceIsOccuped
							| ExceptionUnsuitablePlaceForThisVehicule e1) {
						e1.printStackTrace();
					}
			
				p.getListQuatreRoues().set(i, place) ;
				
			}
		}
		
		
		return p;
	}

	@Override
	public JsonElement serialize(Parking p, Type arg1, JsonSerializationContext c) {
		
		JsonObject obj = new JsonObject() ;
		
		obj.addProperty("name", p.getName());
		obj.addProperty("nb_places_deux_roues", p.getNbPlacesDeuxRoues());
		obj.addProperty("nb_places_quatre_roues", p.getNbPlacesQuatreRoues());
		
		obj.add("list_2_roues", c.serialize(p.getListDeuxRoues())); 
		obj.add("list_4_roues", c.serialize(p.getListQuatreRoues())); 
		
		obj.addProperty("needToPayBefore", p.isNeedToPayBefore()); 
		
		return obj ;
	}

	
	
}
