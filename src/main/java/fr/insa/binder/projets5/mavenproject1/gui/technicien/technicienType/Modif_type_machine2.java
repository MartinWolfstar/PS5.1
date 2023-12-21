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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.type_machine;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
public class Modif_type_machine2 extends VerticalLayout{
    private TextField des;
    private HorizontalLayout HL;
    private Button valid;
    private type_machine mach;

    
    public Modif_type_machine2(int id){
        this.des = new TextField("Description type machine");
        this.valid = new Button ("Modifier");
        this.valid.addClickListener(e -> {
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                type_machine.setDes_type_machine(this.des.getValue(), id, con);
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            Notification.show("Problème BdD : mtm2");
        }
        });
        
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout type_machine"));
        this.HL.add(this.des);
        this.add(this.HL, this.valid);
}
}
