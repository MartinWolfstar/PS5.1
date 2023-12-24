/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.sql.Connection;

/**
 *
 * @author binde
 */
@Route(value = "225")
public class Choix_operation_produit extends VerticalLayout {

    private Grid_choix_operation grid;
    private HorizontalLayout H1;

    public Choix_operation_produit() {
        produit p = (produit) VaadinSession.getCurrent().getAttribute("produit");
        this.add(new H3(p.getDes()));
        H1 = new HorizontalLayout();

//        try {
//            this.grid = new Grid_produit(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn")));
        this.grid = new Grid_choix_operation(p.getId());
        this.add(this.grid);
//        } catch (SQLException ex) {
//            this.add(new H3("Problème BdD : " + ex.getMessage()));
//        }

//        H1.add(new Ajout_produit(), new Supp_produit(), new Modif_produit());
        H1.add(new Ajout_operation());

        this.add(H1);

        addClassName("liste_machine");
        setSizeFull();

    }
}
    
