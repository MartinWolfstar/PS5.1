/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.messagerie;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
public class Ajout_message extends VerticalLayout{
    private TextField message;
    private HorizontalLayout HL;
    private Button valid;
    private messagerie mess;

    
    public Ajout_message(){
        this.message = new TextField();
        this.valid = new Button ("envoyer");
        int idc = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
        this.valid.addClickListener(e -> {
            this.mess = new messagerie(this.message.getValue(),idc);
            try {
                mess.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            Notification.show("Problème BdD : x");
        }
        });
        valid.addClickShortcut(Key.ENTER);
        this.HL = new HorizontalLayout();
        this.HL.add(this.message);
        this.add(this.HL, this.valid);
    }
}
