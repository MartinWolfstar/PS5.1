/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.TechnicienCommande;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Operation;
import static fr.insa.binder.projets5.mavenproject1.Precede.liste_to_string;
import static fr.insa.binder.projets5.mavenproject1.Precede.tousLesPrecede_operation;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.commande_produit;
import fr.insa.binder.projets5.mavenproject1.produit;
import static fr.insa.binder.projets5.mavenproject1.produit.tousLesProduitsCom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author binde
 */
public class Grid_produit_commande extends Grid<produit> {

    public Grid_produit_commande(int id_commande) {
        Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            this.setItems(tousLesProduitsCom(con, id_commande));
        } catch (SQLException ex) {
            Notification.show("Problème BdD : a");
        }
        this.addColumn(produit::getDes).setHeader("Produit");

        this.addComponentColumn(Integer -> {
            Button button = new Button("Operations necessaires", clickEvent -> {
                VaadinSession.getCurrent().setAttribute("produit_com", Integer);
                UI.getCurrent().navigate(Operation_necessaires.class);
            });
            return button;
        }).setHeader("");
    }
}
