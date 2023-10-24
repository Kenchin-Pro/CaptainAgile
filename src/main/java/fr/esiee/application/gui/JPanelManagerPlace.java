package fr.esiee.application.gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.parking.Place;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsAlreadyFree;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionPlaceNotFound;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.parking.time.TimePaid;
import fr.esiee.application.service.ServiceGson;
import fr.esiee.application.service.ServiceParkingManager;
import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.exceptions.ExceptionRegistrationNotAccepted;
import fr.esiee.application.vehicule.reference.TypeVehicule;
import lombok.Getter;
import lombok.Setter;

public class JPanelManagerPlace extends JPanel {

    private static final long serialVersionUID = -1010383032025265018L;
    
    @Getter private JComboBox<String> vehicleTypeComboBox ;

	@Getter private JComboBox<TimeUnit> timeUnit;
    @Getter private JTextField placeNumberField, plaqueField, timePrePaye ;
    @Getter private JLabel textPlaceNumber, textPlaque, textVoid, textPrePaye ;
    @Getter private JButton occupyButton, freeButton  ;
    
    @Getter @Setter private Parking parking ;
    @Getter @Setter private JPanelParking parkingPanel ;

    public JPanelManagerPlace(JPanelParking parkingPanel, Parking park) {
        setLayout(new GridLayout(3, 1));

        setParkingPanel(parkingPanel);
        setParking(park);
        
        // Liste déroulante pour choisir le type de véhicule
        String[] vehicleTypes = TypeVehicule.valuesToString() ;
        TimeUnit[] timeUnitTypes = TimeUnit.values() ;
        
        vehicleTypeComboBox = new JComboBox<>(vehicleTypes);
        plaqueField = new JTextField();
        
        textVoid = new JLabel("") ;
        textPlaceNumber = new JLabel("Numéro de place :") ;
        textPlaque = new JLabel("Plaque : ") ;
        placeNumberField = new JTextField();
        
        textPrePaye = new JLabel("Temps pré-payé : ") ;
        timePrePaye = new JTextField();
        timeUnit = new JComboBox<TimeUnit>(timeUnitTypes) ;
        
        // Bouton pour valider l'emplacement de la place
        occupyButton = new JButton("Valider l'emplacement");
        occupyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) vehicleTypeComboBox.getSelectedItem();
                
                try {
                	int placeNumber = Integer.parseInt(placeNumberField.getText());
                	
                    String plaque = plaqueField.getText() ;
                    
                    // Vous pouvez ici utiliser les données pour occuper la place dans votre modèle
                    TypeVehicule type = TypeVehicule.valueOf(selectedType);
                    Vehicule v = new Vehicule(type, plaque) ;                    
                    Place place = ServiceParkingManager.vehiculeTakePlace(v, parking, placeNumber) ;
                    
                    if(place.isNeedToPayBefore()) {
                    	TimePaid timePaid = new TimePaid(Integer.parseInt(timePrePaye.getText()), (TimeUnit) timeUnit.getSelectedItem()) ;
                    	place.setTimePaid( timePaid );
                    }
                    ServiceGson.save();
                    
                } catch(NumberFormatException exc) {
                	parkingPanel.setErrorMessage("Vous devez entrer l'identifiant de la place (uniquement le nombre).") ;
                } catch (ExceptionPlaceNotFound e1) {
					parkingPanel.setErrorMessage("Aucune place trouvée avec cet identifiant.") ;
				} catch (ExceptionPlaceIsOccuped e1) {
					parkingPanel.setErrorMessage("La place est déjà occupée") ;
				} catch (ExceptionUnsuitablePlaceForThisVehicule e1) {
					parkingPanel.setErrorMessage("La place n'est pas adaptée au véhicule.") ;
				} catch (ExceptionRegistrationNotAccepted e1) {
					parkingPanel.setErrorMessage("La plaque d'immatriculation n'est pas acceptée.") ;
				} 

                parkingPanel.repaint(); // Actualiser l'affichage du parking
            }
        });
        
        
        
     // Bouton pour valider l'emplacement de la place
        freeButton = new JButton("Libérer l'emplacement");
        freeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                try {
                	int placeNumber = Integer.parseInt(placeNumberField.getText());
                	ServiceParkingManager.vehiculeFreeUpPlace(parking, placeNumber) ;
                	ServiceGson.save();
                	
                } catch(NumberFormatException exc) {
                	parkingPanel.setErrorMessage("Vous devez entrer l'identifiant de la place (uniquement le nombre).") ;
                } catch (ExceptionPlaceNotFound e1) {
					parkingPanel.setErrorMessage("Aucune place trouvée avec cet identifiant.") ;
				} catch (ExceptionPlaceIsAlreadyFree e1) {
					parkingPanel.setErrorMessage("La place est déjà libre") ;
				}
                parkingPanel.repaint(); // Actualiser l'affichage du parking
            }
        });
        
        
    }
    
    @Override
    public void repaint() {
    	super.repaint();
    	
    	if(parking == null) return ;
    	
    	add(vehicleTypeComboBox);
    	add(textVoid) ;
    	add(textPlaceNumber) ;
        add(placeNumberField);
        add(textPlaque) ;
        add(plaqueField) ;
        add(occupyButton);
        add(freeButton);
        add(textPrePaye) ;
        add(timePrePaye) ;
        add(timeUnit) ;
    }
}
