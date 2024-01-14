/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientCommande;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.commande;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_commande extends Grid<commande>{
    
    public Grid_commande(List<commande> list_commande) {
        this.setItems(list_commande);
        Grid.Column<commande> Id_commande =this.addColumn(commande::getId_commande).setHeader("N°Commande");
        Grid.Column<commande> nom =this.addColumn(commande::getNom_commande).setHeader("Nom");
        Grid.Column<commande> Description =this.addColumn(commande::getDes_commande).setHeader("Description");
        this.setMaxHeight("50vh");
        nom.setWidth("100px");
        Description.setWidth("1000px");
        Id_commande.setWidth("100px");
        
    }
}
