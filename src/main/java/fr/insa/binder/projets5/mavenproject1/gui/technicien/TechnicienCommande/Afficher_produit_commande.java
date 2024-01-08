/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.TechnicienCommande;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;

/**
 *
 * @author binde
 */
@Route(value = "3333")
public class Afficher_produit_commande extends VerticalLayout {

    private Grid_produit_commande grid;

    public Afficher_produit_commande() {
        this.add(new H3("Liste de toutes les produits"));
        int i = (Integer) VaadinSession.getCurrent().getAttribute("commande");
        this.add(new H3(String.valueOf(i)));
        this.grid = new Grid_produit_commande((Integer) VaadinSession.getCurrent().getAttribute("commande"));
        this.add(this.grid);
        addClassName("liste_commande");
        setSizeFull();

        utile.stylisation(this);
    }
}
