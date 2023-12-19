/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author schmi
 */
@PageTitle("Produit")
@Route(value = "13", layout = BarreGaucheClient.class)
public class ProduitClient extends VerticalLayout{
    
    private Grid_produit grid;
    private HorizontalLayout H1;
    private Button valid;
    private TextField rech;
    private HorizontalLayout H2;
    private Button recherche;
    
    public ProduitClient() {
        
        this.add(new H3("Liste de tous les Produits"));
        H1 = new HorizontalLayout();
        this.valid = new Button ("Acheter");
                
        this.rech = new TextField();
        this.recherche = new Button("rechercher");
        this.H2 = new HorizontalLayout();
        
        this.H2.add(rech,recherche,valid);
        this.add(H2);
        
        try {
            this.grid = new Grid_produit(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        this.valid.addClickListener(e -> {
            Notification.show("Vous avez acheté les produits :" + grid.getSelectedIds());
            commande nouvelleCommande = new commande("nouvelle commande", grid.getSelectedIds().toString(), 1);  
            try {
                nouvelleCommande.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
                Notification.show("Problème BdD : p");
            }
        });
        
        this.recherche.addClickListener(e -> {
            this.removeAll();  
            this.add(H2);
            //this.add(new H3("Cherchez par vous-même"));
            String mot = "%" + this.rech.getValue() + "%";
            Notification.show("mot :" + mot +"-");
            try{
                this.grid = new Grid_produit(produit.tousLesProduitsrecherche(mot, (Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
                this.add(this.grid);
            }catch(SQLException ex){
                Notification.show("Problème BdD : pp");
            }
        });
        recherche.addClickShortcut(Key.ENTER);
        
        addClassName("liste_machine");
        setSizeFull();

        
    }
}
