package fr.esiee.application.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.parking.Place;
import fr.esiee.application.parking.serializer.ParkingSerializer;
import fr.esiee.application.parking.serializer.PlaceSerializer;
import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.serialize.VehiculeSerializer;

public interface ServiceGson {
	
	public static GsonBuilder gsonBuilder = new GsonBuilder()
			.serializeNulls()
			.setPrettyPrinting() 
			.registerTypeAdapter(Parking.class, new ParkingSerializer()) 
			.registerTypeAdapter(Place.class, new PlaceSerializer()) 
			.registerTypeAdapter(Vehicule.class, new VehiculeSerializer()) ;
	public static final File file = new File("logs/saves.json") ;
	
	
	public static void load() {
		
		ServiceLogger.info("Loading du fichier : "+file.getPath());
	
	    Gson gson = gsonBuilder.create();

	    try (FileReader reader = new FileReader(file)) {
	        JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
	        
	        for (Entry<String, JsonElement> entry : obj.entrySet()) {
	            Parking parking = gson.fromJson(entry.getValue(), Parking.class);
	            ServiceParkingManager.addParking(parking);
	        }
	        
	        ServiceLogger.info("Chargement du fichier : " + file.getPath() + " effectué avec succès.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public static void save() {
		
		Gson gson = gsonBuilder.create() ;
		
		JsonObject obj = new JsonObject() ; 
		
		for(Entry<String, Parking> p : ServiceParkingManager.mapParkings.entrySet()) {
			obj.add(p.getKey(), gson.toJsonTree(p.getValue()));
		}
		
		File file = new File("logs/saves.json") ;
		try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
