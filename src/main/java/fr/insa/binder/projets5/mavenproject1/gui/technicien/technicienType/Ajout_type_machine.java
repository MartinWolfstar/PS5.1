/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.type_machine;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
public class Ajout_type_machine extends VerticalLayout{
    private TextField des;
    private HorizontalLayout HL;
    private Button valid;
    private type_machine mach;

    
    public Ajout_type_machine(){
        this.des = new TextField("Description produit");
        this.valid = new Button ("Ajouter type_machine");
        this.valid.addClickListener(e -> {
            this.mach = new type_machine(this.des.getValue());
            try {
                mach.save_type_machine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            Notification.show("Problème BdD : atm");
        }
        });
        
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout type_machine"));
        this.HL.add(this.des);
        this.add(this.HL, this.valid);
    }
}
