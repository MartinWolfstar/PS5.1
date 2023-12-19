/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation;

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
import fr.insa.binder.projets5.mavenproject1.Operation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Supp_operation extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    
    public Supp_operation(){

        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de la Operation à supprimer");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            try {
                Operation.supOperation((Connection) VaadinSession.getCurrent().getAttribute("conn"), Integer.valueOf(e.getSource().getText()));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : so");
            }
            UI.getCurrent().getPage().reload();
        };
        try {
            List<Operation> id_liste = Operation.tousLesOperations((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (Operation x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId_operation()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : so");
            }
        this.add(new H3("Supprimer Operation"));
        this.add(menu_bar);
        } 
    }


