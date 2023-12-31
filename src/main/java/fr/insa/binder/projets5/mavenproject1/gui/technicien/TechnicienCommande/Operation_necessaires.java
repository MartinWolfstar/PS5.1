/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.TechnicienCommande;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.exemplaire;
import static fr.insa.binder.projets5.mavenproject1.exemplaire.tousLesxemplaires_produit;
import static fr.insa.binder.projets5.mavenproject1.operation_effectuee.Meilleurs_operation_produit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author binde
 */
@Route(value = "3335")
public class Operation_necessaires extends VerticalLayout {

    private Grid_op grid;

    public Operation_necessaires() {
        Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        this.add(new H3("Liste de toutes les opérations"));
        exemplaire exem = (exemplaire) VaadinSession.getCurrent().getAttribute("exemplaire");
        this.add(new H3(String.valueOf(exem.getDes_exemplaire())));
        try {
            this.grid = new Grid_op(Meilleurs_operation_produit(con, tousLesxemplaires_produit(con, exem.getId_produit()).get(0)));
        } catch (SQLException ex) {
            Notification.show("Problème BdD : a");
        }
        this.add(this.grid);
        addClassName("liste_commande");
        setSizeFull();
    }

}
