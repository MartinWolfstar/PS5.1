/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.upload.Upload;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.UploadArea;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
import java.io.File;


/**
 * Product view for the technician.
 */
@PageTitle("Produit")
@Route(value = "25", layout = BarreGaucheTechnicien.class)
public class ProduitTechnicien extends VerticalLayout {

    private AfficherProduit grid;
    private HorizontalLayout H1;
    private Upload upload;
    private UploadArea UpArea;

    public ProduitTechnicien() {
        this.add(new H3("Liste de tous les Produits"));
        H1 = new HorizontalLayout();
//        File uploadFolder = new File("src\\main\\resources\\META-INF\\resources\\images");
//        if (!uploadFolder.exists()) {
//            uploadFolder.mkdirs();
//        }
        this.UpArea = new UploadArea();
        H1.add(new Ajout_produit(),UpArea);

        this.add(H1);

        this.grid = new AfficherProduit();
        this.add(this.grid);

        addClassName("liste_machine");
        setSizeFull();
        utile.stylisation(this);

//        this.add(upload);
    }
}
