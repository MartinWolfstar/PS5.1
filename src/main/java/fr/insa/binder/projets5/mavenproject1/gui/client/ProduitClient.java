/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;


import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.Ajout_machine;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.Modif_machine;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.Supp_machine;
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
    
    public ProduitClient() {
        
        this.add(new H3("Liste de tous les Produits"));
        H1 = new HorizontalLayout();
        try {
            this.grid = new Grid_produit(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        H1.add(new Achat());
        this.add(H1);
        
        addClassName("liste_machine");
        setSizeFull();
    }
}
