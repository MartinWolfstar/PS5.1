/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Supp_machine extends VerticalLayout{
    
//    private IntegerField id;
    private MenuBar menu_bar;
    private MenuItem id;
    
    public Supp_machine(){
//        this.id = new IntegerField("id machine");
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selctionner l'identifiant de la machine à supprimer");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            try {
                machine.supMachine((Connection) VaadinSession.getCurrent().getAttribute("conn"), Integer.valueOf(e.getSource().getText()));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : x");
            }
            UI.getCurrent().getPage().reload();
        };
        try {
            List<machine> id_liste = machine.tousLesMachines((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (machine x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : x");
            }
        this.add(new H3("Supprimer machine"));
        this.add(menu_bar);
        }


//        this.valid = new Button ("Supprimer machine");
//        this.valid.addClickListener(e -> {
//            try {
//                machine.supMachine(connectSurServeurM3(),id.getValue());
//                UI.getCurrent().getPage().reload();
//            } catch(SQLException ex) {
//            this.add(new H3("Problème BdD : "));
//        }
//        });
//        
//
//        this.add(new H3("Supprimer machine"));
//        this.add(this.id,this.valid);
//        
        
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


