/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
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

/**
 *
 * @author binde
 */
public class Modif_operation extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    private Modif_operation2 modif_operation2;
    
    public Modif_operation(){

        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de la Operation à modifier");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            int getId_operation = Integer.valueOf(e.getSource().getText());
            this.modif_operation2 = new Modif_operation2(getId_operation);
            this.add(modif_operation2);
        };
        try {
            List<Operation> id_liste = Operation.tousLesOperations((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (Operation x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId_operation()), listener);
            }      
        }catch(SQLException ex) {
               Notification.show("Problème BdD : mo");
        }
        
        this.add(new H3("Modifier Operation"));
        this.add(menu_bar);
        }    
}