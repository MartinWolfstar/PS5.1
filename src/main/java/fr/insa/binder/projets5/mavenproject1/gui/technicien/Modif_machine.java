/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien;

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
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author binde
 */
public class Modif_machine extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    private Modif_machine2 modif_machine2;
    
    public Modif_machine(){
//        this.id = new IntegerField("id machine");
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de la machine à modifier");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            int id_m = Integer.valueOf(e.getSource().getText());
            this.modif_machine2 = new Modif_machine2(id_m);
            this.add( modif_machine2);
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
        this.add(new H3("Modifier machine"));
        this.add(menu_bar);
        }    
}