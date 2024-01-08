/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientProduit;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Operation;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.commande_produit;
import fr.insa.binder.projets5.mavenproject1.exemplaire;
import fr.insa.binder.projets5.mavenproject1.gui.client.BarreGaucheClient;
import fr.insa.binder.projets5.mavenproject1.produit;
import static fr.insa.binder.projets5.mavenproject1.produit.giveProduit;
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
    private H3 titre;
    private Grid_operation2 grid2;
    
    public ProduitClient() {
        
        this.titre = new H3("Liste de toutes les Produits");
        this.add(titre);
        H1 = new HorizontalLayout();
        this.valid = new Button ("Acheter");
                
        this.rech = new TextField();
        this.recherche = new Button("rechercher");
        this.H2 = new HorizontalLayout();
        
        this.H2.add(rech,recherche,valid);
        this.add(H2);
        stylisation();
        
        try {
            this.grid = new Grid_produit(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        
        this.valid.addClickListener(e -> {
            Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
            Integer idClient = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
            commande nouvelleCommande = new commande("nouvelle commande", "contenu : ", idClient);
            try {
                nouvelleCommande.saveInDBV1(con);
                //nouvelleCommande.setId_commande(nouvelleCommande.getTheID(con));
            } catch (SQLException ex) {
                Notification.show("Problème lors de la création de nouvelleCommande : " + ex.getLocalizedMessage());
            }
            Notification.show(nouvelleCommande.toString());
            String str = new String("contenu : ");
            for (Integer produitId : grid.getSelectedIds()) {
                String produitSelectionne;
                try {
                    produitSelectionne = produit.giveProduit(con, produitId); 
                    str += produitSelectionne;
                    commande_produit cp = new commande_produit(nouvelleCommande.getId_commande(),produitId);
                    nouvelleCommande.setDes(str,nouvelleCommande.getId_commande(),con);
                    cp.saveInDBV1(con);
                    produitCommandé(produitId, nouvelleCommande.getId_commande());
                    
                } catch (SQLException ex) {
                    Notification.show("Problème commande_produit : " + ex.getLocalizedMessage());
                }
            }
            //UI.getCurrent().getPage().reload(); // à remettre !!!
            Notification.show("Vous avez acheté les produits :" + grid.getSelectedIds());
            
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
    
    private void produitCommandé(int produitId, int IdCommande){
        try {
            //doit récupérer les opérations requises pour faire le produit
            Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
            this.grid2 = new Grid_operation2(Operation.tousLesOperations_produit(con,produitId));
            Notification.show("les operation a effectuer : " + Operation.tousLesOperations_produit(con,produitId));
            
            exemplaire exempl = new exemplaire(giveProduit(con, produitId), produitId, IdCommande);
            exempl.saveInDBV1(con);
//            List<operation_effectuee> liste_op_eff = Meilleurs_operation_produit(con, exempl);
//            for (operation_effectuee op_ef : liste_op_eff){
//                op_ef.saveInDBV1(con);
//            }
        } catch (SQLException ex) {
             Notification.show("Problème BdD : aurore");
        }
    }
    
    private void stylisation() {
        
        this.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "1200vh");
        
        recherche.getStyle()
            .set("color", "Crimson")
            .set("background-color", "PowderBlue");
        
        valid.getStyle()
            .set("color", "Crimson")
            .set("background-color", "PowderBlue");
        
        rech.getStyle()
            .set("color", "Crimson");
        this.titre.getStyle()
            .set("color", "Indigo")
            .set("border-radius", "10px") 
            .set("padding", "10px");
    }
}
