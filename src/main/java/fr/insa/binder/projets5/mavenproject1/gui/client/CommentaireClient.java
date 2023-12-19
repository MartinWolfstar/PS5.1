/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.machine;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author schmi
 */
@PageTitle("Commentaire")
@Route(value = "14", layout = BarreGaucheClient.class)
public class CommentaireClient extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    private Grid_produit grid;
    
    public CommentaireClient() {
        
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant du produit");
        SubMenu id_sub = id.getSubMenu();
        
        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->{
            try{
                this.grid = new Grid_produit(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
                this.add(this.grid);
            }catch(SQLException ex){
                Notification.show("Problème BdD : pp");
            }
            
            UI.getCurrent().getPage().reload();
        };
        
        try {
            List<produit> id_liste = produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (produit x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId()), listener);
        }      
        } 
        catch(SQLException ex) {
            Notification.show("Problème BdD : x");
        }
        
        this.add(new H3("Acceder au commentaire du produit"));
        this.add(menu_bar); 
    }
}
