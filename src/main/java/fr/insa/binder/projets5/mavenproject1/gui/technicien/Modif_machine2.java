/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
public class Modif_machine2 extends VerticalLayout{
    private IntegerField ref;
    private TextField des;
    private HorizontalLayout HL;
    private Button valid;
    private machine mach;

    
    public Modif_machine2(int id){
        this.ref = new IntegerField("Reference produit");
        this.des = new TextField("Description produit");
        this.valid = new Button ("Modifier");
        this.valid.addClickListener(e -> {
            try {
                machine.setDes(this.des.getValue(), id, connectSurServeurM3());
                machine.setRef(this.ref.getValue(), id, connectSurServeurM3());
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        });
        
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout machine"));
        this.HL.add(this.ref, this.des);
        this.add(this.HL, this.valid);
}
}
