/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import fr.insa.binder.projets5.mavenproject1.gui.technicien.Grid_machine;
import fr.insa.binder.projets5.mavenproject1.gui.*;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.SQLException;

/**
 *
 * @author schmi
 */
@PageTitle("Produit")
@Route(value = "13", layout = BarreGaucheClient.class)
public class ProduitClient extends VerticalLayout{
    
    private Grid_machine grid;
    
    public ProduitClient() {
        
        this.add(new H3("Liste de toutes les commandes"));
        try {
            this.grid = new Grid_machine(machine.tousLesMachines(connectSurServeurM3())); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        
        addClassName("liste_machine");
        setSizeFull();
    }
}
