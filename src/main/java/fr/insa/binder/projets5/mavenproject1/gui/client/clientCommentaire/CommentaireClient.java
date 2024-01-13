/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientCommentaire;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.ImageT;
import fr.insa.binder.projets5.mavenproject1.commantaire;
import fr.insa.binder.projets5.mavenproject1.gui.client.BarreGaucheClient;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author schmi
 */
@PageTitle("Commentaire")
@Route(value = "14", layout = BarreGaucheClient.class)
public class CommentaireClient extends VerticalLayout {

    private MenuBar menu_bar;
    private MenuItem id;
    private Grid_commentaire grid;
    private ComboBox<produit> comboBoxProduits;
    private TextArea nouveauCommentaire;
    private Button boutonAjoutCommentaire;
    private HorizontalLayout HL;
    private VerticalLayout VL;
    private VerticalLayout VL2;

    public CommentaireClient() {
        this.menu_bar = new MenuBar();
        this.HL = new HorizontalLayout();
        this.VL = new VerticalLayout();
        this.VL2 = new VerticalLayout();
        this.id = menu_bar.addItem("Sélectionner l'identifiant du produit");
        SubMenu id_sub = id.getSubMenu();

        ComponentEventListener<ClickEvent<MenuItem>> listener = e -> {
            try {
                String selectedProduct = e.getSource().getText();
                int selectedProductId = produit.getIdProduitParDescription(selectedProduct, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                this.grid = new Grid_commentaire(commantaire.tousLescommentairesParProduit(selectedProductId, (Connection) VaadinSession.getCurrent().getAttribute("conn")));
                this.add(new H6("Commentaires pour le produit : " + selectedProduct));
                this.add(this.grid);
            } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getMessage());
            }
        };

        try {
            List<produit> id_liste = produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (produit x : id_liste) {
                String name = x.getDes();
                id_sub.addItem(name, listener);
            }
        } catch (SQLException ex) {
            Notification.show("Problème BdD : " + ex.getMessage());
        }

        // Ajouter une ComboBox pour sélectionner le produit
        this.comboBoxProduits = new ComboBox<>("Sélectionner le produit");
        try {
            this.comboBoxProduits.setItems(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn")));
        } catch (SQLException ex) {
            Notification.show("Problème BdD : " + ex);
        }
        this.comboBoxProduits.setItemLabelGenerator(produit::getDes);

        // Ajouter une TextArea pour le nouveau commentaire
        this.nouveauCommentaire = new TextArea("Nouveau Commentaire");

        // Ajouter le bouton pour ajouter un commentaire
        this.boutonAjoutCommentaire = new Button("Ajouter Commentaire", this::ajouterCommentaire);

        boutonAjoutCommentaire.addClickShortcut(Key.ENTER);
        
        VL.add(new H6("Pour rajouter un commentaire :"),comboBoxProduits,nouveauCommentaire,boutonAjoutCommentaire);
        VL2.add(new H6("Pour voir les commentaires :"),menu_bar);
        HL.add(VL2,VL);
        this.add(HL);
        stylisation();
    }

    private void ajouterCommentaire(ClickEvent<Button> event) {
        try {
            produit selectedProduct = comboBoxProduits.getValue();
            int selectedProductId = selectedProduct.getId();
            int idClient = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
            String nouveauMessage = nouveauCommentaire.getValue();
            commantaire nouveauCommantaire = new commantaire(selectedProductId, nouveauMessage, idClient);
            nouveauCommantaire.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            this.grid.setItems(commantaire.tousLescommentairesParProduit(selectedProductId, (Connection) VaadinSession.getCurrent().getAttribute("conn")));
        } catch (SQLException ex) {
            Notification.show("Problème BdD : " + ex);
        }
    }
    private void stylisation() {
        String imageName = "1275600.jpg";
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            ImageT image = ImageT.getImageByName(conn, imageName);
            if (image != null) {
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());
                this.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover")
                    .set("height", "1200vh");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
    }
}

