/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.binder.projets5.mavenproject1.Operation;

/**
 *
 * @author binde
 */
public class Modif_operation2 extends VerticalLayout{
    private IntegerField ref;
    private TextField des;
    private HorizontalLayout HL;
    private Button valid;
    private Operation ope;

    
    public Modif_operation2(int id){
//        this.ref = new IntegerField("Reference produit");
//        this.des = new TextField("Description produit");
//        this.valid = new Button ("Modifier");
//        this.valid.addClickListener(e -> {
//            try {
//                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
//                Operation.setDes(this.des.getValue(), id, con);
//                Operation.setRef(this.ref.getValue(), id, con);
//                UI.getCurrent().getPage().reload();
//            } catch(SQLException ex) {
//            Notification.show("Problème BdD : x");
//        }
//        });
//        
//        this.HL = new HorizontalLayout();
//        this.add(new H3("Ajout Operation"));
//        this.HL.add(this.ref, this.des);
//        this.add(this.HL, this.valid);
    }
}
