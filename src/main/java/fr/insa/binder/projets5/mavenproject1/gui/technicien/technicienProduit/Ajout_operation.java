/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Operation;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
public class Ajout_operation extends VerticalLayout{
        private Button valid;

        public Ajout_operation(){
            this.valid = new Button("Ajouter une operation");
            this.valid.addClickListener(e -> {
                produit p = (produit) VaadinSession.getCurrent().getAttribute("produit");
            Operation op = new Operation(1, p.getId());
            try {
                op.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            Notification.show("Problème BdD : a");
            }
        });
        
        valid.addClickShortcut(Key.ENTER);
    this.add(valid);
}
    
}
