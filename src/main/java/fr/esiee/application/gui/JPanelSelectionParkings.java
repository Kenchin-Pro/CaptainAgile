package fr.esiee.application.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.service.ServiceParkingManager;
import lombok.Getter;
import lombok.Setter;

public class JPanelSelectionParkings extends JPanel {

	private static final long serialVersionUID = 2324382433348813008L;
	
    @Getter @Setter private JList<String> parkingList;
    @Getter @Setter private DefaultListModel<String> listModel;
    

	public JPanelSelectionParkings(JPanelParkingManager parkingManagerPanel) {

        listModel = new DefaultListModel<>();
        parkingList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(parkingList);
        listScrollPane.setPreferredSize(new Dimension(100, 400));
        add(listScrollPane, BorderLayout.WEST);

        for(Parking p : ServiceParkingManager.mapParkings.values())
        	listModel.addElement(p.getName());
        
        
        // Gestionnaire d'événements pour sélectionner un parking
        parkingList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String nameParking = parkingList.getSelectedValue();
                Parking parking = ServiceParkingManager.getParking(nameParking) ;

                if (parking != null) {
                    parkingManagerPanel.setParking(parking);
                    parkingManagerPanel.repaint(); // Actualisez l'affichage du JPanelParkingManager
                    parkingManagerPanel.updateUI() ;
                }
            }
        });
        
        
	}
	
}
