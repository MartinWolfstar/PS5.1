/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
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

/**
 *
 * @author binde
 */
public class Modif_type_operation extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    private Modif_type_operation2 type_operation2;
    
    public Modif_type_operation(){
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de l'operation à modifier");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            int id_m = Integer.valueOf(e.getSource().getText());
            this.type_operation2 = new Modif_type_operation2(id_m);
            this.add(type_operation2);
        };
        try {
            List<type_operation> id_liste = type_operation.tousLesTypeOperations((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (type_operation x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId_type_operation()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : mtm");
            }
        this.add(new H3("Modifier type operation"));
        this.add(menu_bar);
        stylisation();
        }
    private void stylisation() {
        
        id.getStyle()
                .set("color", "Crimson").set("background-color", "PowderBlue");
        
    }    
}