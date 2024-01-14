/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.type_operation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Supp_type_operation extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    
    public Supp_type_operation(){

        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Sélectionner l'identifiant du type d'operation à supprimer");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            try {
                type_operation.supTypeOperation((Connection) VaadinSession.getCurrent().getAttribute("conn"), Integer.valueOf(e.getSource().getText()));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : sto1");
            }
            UI.getCurrent().getPage().reload();
        };
        try {
            List<type_operation> id_liste = type_operation.tousLesTypeOperations((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (type_operation x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId_type_operation()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : sto2");
            }
        this.add(new H3("Supprimer type Operation"));
        this.add(menu_bar); 
    }      
}


