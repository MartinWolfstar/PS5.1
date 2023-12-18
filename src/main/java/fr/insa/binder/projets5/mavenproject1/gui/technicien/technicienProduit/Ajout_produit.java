/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
public class Ajout_produit extends VerticalLayout{
    private IntegerField ref;
    private TextField des;
    private HorizontalLayout HL;
    private Button valid;
    private produit prod;

    
    public Ajout_produit(){
        this.ref = new IntegerField("Reference produit");
        this.des = new TextField("Description produit");
        this.valid = new Button ("Ajouter produit");
        this.valid.addClickListener(e -> {
            this.prod = new produit(this.des.getValue(), this.ref.getValue());
            try {
                prod.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            Notification.show("Problème BdD : a");
        }
        });
        
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout produit"));
        this.HL.add(this.ref, this.des);
        this.add(this.HL, this.valid);
    }
}
