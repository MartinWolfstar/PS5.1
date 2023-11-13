/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.SQLException;

public class Supp_machine extends VerticalLayout{
    
    private IntegerField id;
    private Button valid;
    
    public Supp_machine(){
        this.id = new IntegerField("id machine");
        this.valid = new Button ("Supprimer machine");
        this.valid.addClickListener(e -> {
            try {
                machine.supMachine(connectSurServeurM3(),id.getValue());
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        });
        

        this.add(new H3("Supprimer machine"));
        this.add(this.id,this.valid);

    }
}

