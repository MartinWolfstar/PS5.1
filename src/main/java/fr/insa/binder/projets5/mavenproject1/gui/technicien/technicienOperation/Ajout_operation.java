/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation;

import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.Operation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author binde
 */
public class Ajout_operation extends VerticalLayout{
    private IntegerField type_operation;
    private IntegerField produit;
    private HorizontalLayout HL;
    private Button valid;
    private Operation ope;

    
    public Ajout_operation(){
        this.type_operation = new IntegerField("type_operationerence produit");
        this.produit = new IntegerField("produitcription produit");
        this.valid = new Button ("Ajouter Operation");
        this.valid.addClickListener(e -> {
            this.ope = new Operation(this.type_operation.getValue(), this.produit.getValue());
            try {
                ope.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            Notification.show("Problème BdD : ao");
        }
        });
        
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout Operation"));
        this.HL.add(this.type_operation, this.produit);
        this.add(this.HL, this.valid);

    }
}
