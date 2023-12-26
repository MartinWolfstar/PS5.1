/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientCommande;

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
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.commande_produit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Supp_commande extends VerticalLayout{
    
//    private IntegerField id;
    private MenuBar menu_bar;
    private MenuItem id;
    
    public Supp_commande(){

        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de la commande à supprimer");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                commande_produit.supCommande_produit(con, Integer.valueOf(e.getSource().getText()));
                commande.supCommande(con, Integer.valueOf(e.getSource().getText()));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : x");
            }
            UI.getCurrent().getPage().reload();
        };
        int idc = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
        try {
            List<commande> id_liste = commande.tousLesCommandes(idc, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (commande x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId_commande()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : x");
            }
        
        this.add(new H3("Supprimer commande"));
        this.add(menu_bar);
        }
    }


