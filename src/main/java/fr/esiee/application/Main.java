package fr.esiee.application;

import org.apache.logging.log4j.core.config.Configurator;

import fr.esiee.application.gui.ParkingGUI;
import fr.esiee.application.service.ServiceGson;
import fr.esiee.application.service.ServiceLogger;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		System.out.println("Initialisation de Log4j2");
        Configurator.initialize(null, "log4j2.xml");				
        ServiceLogger.debug("Initilisation de Log4j2 réussie.");
        
        ServiceLogger.info("");
        ServiceLogger.info("");
        ServiceLogger.info("");
		ServiceLogger.info("Lancement de l'application...");
	
		ServiceGson.load() ;
		
		//new ManagerParking().loadDefault();
		
		new ParkingGUI() ;
		
		
	}

}
