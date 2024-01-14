/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.TechnicienCommande;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit.Choix_operation_produit;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_commande extends Grid<commande> {

    public Grid_commande(List<commande> list_commande) {
        this.setItems(list_commande);
        this.addColumn(commande::getId_commande).setHeader("Id Commande");
        this.addColumn(commande::getNom_commande).setHeader("Nom");
        this.addColumn(commande::getDes_commande).setHeader("Description");
        this.addColumn(commande::getId_client).setHeader("Id_client");
        
        this.addComponentColumn(commande -> {
            Button button = new Button("Operations necessaires", clickEvent -> {
                VaadinSession.getCurrent().setAttribute("commande", commande.getId_commande());
                UI.getCurrent().navigate(Afficher_produit_commande.class);
            });
            return button;
        }).setHeader("");
    }

}