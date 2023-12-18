/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author binde
 */
public class Modif_produit extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    private Modif_produit2 modif_produit2;
    
    public Modif_produit(){
//        this.id = new IntegerField("id produit");
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de la produit à modifier");
        SubMenu id_sub = id.getSubMenu();
        

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            int id_m = Integer.valueOf(e.getSource().getText());
            this.modif_produit2 = new Modif_produit2(id_m);
            this.add( modif_produit2);
        };
        try {
            List<produit> id_liste = produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (produit x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : m");
            }
        this.add(new H3("Modifier produit"));
        this.add(menu_bar);
        }    
}