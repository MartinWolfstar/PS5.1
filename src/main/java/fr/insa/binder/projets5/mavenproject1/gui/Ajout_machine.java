/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
            this.mach = new machine(this.des.getValue(), this.ref.getValue());
            try {
                mach.saveInDBV1(connectSurServeurM3());
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        });
        
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout machine"));
        this.HL.add(this.ref, this.des);
        this.add(this.HL, this.valid);

//        this.menu_bar = new MenuBar();
//        this.des = menu_bar.addItem("Description");
//        this.ref = menu_bar.addItem("Reference");
//        SubMenu des_sub = des.getSubMenu();
//        List<String> messages = Arrays.asList("Hello", "World!", "How", "Are", "You");
//        for (String x : messages) { 
//            des_sub.addItem(x);
//        }
//        this.add(this.menu_bar);
    }
}
