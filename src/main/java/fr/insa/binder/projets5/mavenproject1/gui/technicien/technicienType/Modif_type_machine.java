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
import fr.insa.binder.projets5.mavenproject1.type_machine;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Modif_type_machine extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    private Modif_type_machine2 modif_machine2;
    
    public Modif_type_machine(){
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant du type de machine à modifier");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            int id_m = Integer.valueOf(e.getSource().getText());
            this.modif_machine2 = new Modif_type_machine2(id_m);
            this.add(modif_machine2);
        };
        try {
            List<type_machine> id_liste = type_machine.tousLesTypeMachine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (type_machine x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId_type_machine()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : mtm");
            }
        this.add(new H3("Modifier type machine"));
        this.add(menu_bar); 
    }
}