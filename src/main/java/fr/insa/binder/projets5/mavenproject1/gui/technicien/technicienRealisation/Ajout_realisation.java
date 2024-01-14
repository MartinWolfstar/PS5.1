/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienRealisation;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.realisation;
import java.sql.Connection;
import java.sql.SQLException;

public class Ajout_realisation extends VerticalLayout{
    private IntegerField duree;
    private Button valid;
    private realisation real;

    
    public Ajout_realisation(){
        this.duree = new IntegerField("ajouter une durée");
        this.valid = new Button ("Créer realisation");
        
        this.valid.addClickListener(e -> {
            this.real = new realisation(this.duree.getValue(),1 ,1 );
            try {
                real.save_realisation((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            Notification.show("Problème BdD : ajout realisation");
            }
        });

        this.add(new H3("Ajout realisation"));
        this.add(this.duree, this.valid);
    }
}
