/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.binder.projets5.mavenproject1.commande;

/**
 *
 * @author binde
 */
public class Achat extends VerticalLayout{
    private TextField nom;
    private IntegerField quant;
    private HorizontalLayout HL;
    private Button valid;
    private commande com;

    
    public Achat(){
        this.nom = new TextField("Nom produit");
        this.quant = new IntegerField("Quantité");
        this.valid = new Button ("Acheter");
        
        this.valid.addClickListener(e -> {
            //for i in range (quant){
                
                
//                this.com = new commande(this.nom.getValue());
//                try {
//                    com.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                    UI.getCurrent().getPage().reload();
//                } catch(SQLException ex) {
//                Notification.show("Problème BdD : x");
//                }
//            }
        });
        
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout machine"));
        this.HL.add(this.nom, this.quant);
        this.add(this.HL, this.valid);
    }
}
