/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
public class Ajout_machine extends VerticalLayout{
    private IntegerField ref;
    private TextField des;
    private HorizontalLayout HL;
    private Button valid;
    private machine mach;

    public Ajout_machine(){
        this.ref = new IntegerField("Reference produit");
        this.des = new TextField("Description produit");
        this.valid = new Button ("Ajouter machine");
        this.valid.addClickListener(e -> {
            this.mach = new machine(this.ref.getValue(), this.des.getValue(), 1, 1);
            try {
                mach.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
                Notification.show("Problème BdD : ajout machine : " + ex);
            }
        });    
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout machine"));
        this.HL.add(this.ref, this.des, this.valid);
        this.add(this.HL);
    }
    public Ajout_machine(int id_pdt){
        this.ref = new IntegerField("Reference produit");
        this.des = new TextField("Description produit");
        this.valid = new Button ("Ajouter machine");
        this.valid.addClickListener(e -> {
            this.mach = new machine(this.ref.getValue(), this.des.getValue(), id_pdt, 1);
            try {
                mach.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
                Notification.show("Problème BdD : ajout machine : " + ex);
            }
        });    
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout machine"));
        this.HL.add(this.ref, this.des);
        this.add(this.HL, this.valid);
    }
}
