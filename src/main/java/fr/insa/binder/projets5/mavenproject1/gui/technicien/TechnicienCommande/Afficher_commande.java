/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.TechnicienCommande;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
@PageTitle("Commande")
@Route(value = "3331", layout = BarreGaucheTechnicien.class)
public class Afficher_commande extends VerticalLayout {

    private Grid_commande grid;

    public Afficher_commande() {
        this.add(new H3("Liste de toutes les commandes"));
        try {
            this.grid = new Grid_commande(commande.tousLesCommandes((Connection) VaadinSession.getCurrent().getAttribute("conn")));
            this.add(this.grid);
        } catch (SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }

        
        addClassName("liste_commande");
        setSizeFull();

        utile.stylisation(this);
    }
}
