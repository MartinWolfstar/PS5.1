/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientCommande;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.gui.client.BarreGaucheClient;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author schmi
 */
@PageTitle("Commande")
@Route(value = "11", layout = BarreGaucheClient.class)
public class CommandeClient extends VerticalLayout{
    
    private Button facture;
    private Grid_commande grid;
    private HorizontalLayout H1;
    private H3 titre;
    
    public CommandeClient() {
        
        this.titre = new H3("Liste de toutes les commandes");
        this.add(titre);
        H1 = new HorizontalLayout();
        
        try {
            int idc = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
            this.grid = new Grid_commande(commande.tousLesCommandes(idc, (Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : (faut se connecter)"));
        }
        H1.add(new Supp_commande());
        this.add(H1);
        
        
        addClassName("liste_commande");
        setSizeFull();

        facture = new Button("facture");
        facture.addClickListener(e -> {
            Notification.show("facture ");
            showFactureDialog();
        });
        facture.addClickShortcut(Key.ENTER);
        setMargin(true);
        //setHorizontalComponentAlignment(FlexComponent.Alignment.END, name, sayHello);
        add(facture);
        stylisation();
    }
    
    private void showFactureDialog() {
        // Créer une fenêtre modale
        Dialog factureDialog = new Dialog();
        factureDialog.setCloseOnOutsideClick(true);
        factureDialog.setWidth("700px"); // Ajustez la largeur selon vos besoins
        factureDialog.setModal(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateDuJour = LocalDate.now().format(formatter);
        String nomClient = "nom du client";
        String adresseClient = "adresse du Client";
        String numeroCommande = "numero Commande";
        String idclient = "id du client";
        
        // Ajouter le texte et la liste des commandes à la fenêtre
        VerticalLayout content = new VerticalLayout();
        content.add(new H1("Facture"));
        VerticalLayout V1 = new VerticalLayout();
        HorizontalLayout H2 = new HorizontalLayout();
        H2.add(new H4("Facturé à : " + nomClient));
        H2.add(new H4("Envoyé à : " + adresseClient));
        H2.add(V1);
        V1.add(new H4("facture numéro : " + numeroCommande + "-" + idclient));
        V1.add(new H4("date : " + dateDuJour));
        content.add(H2);


        // Ajouter la liste des commandes (utilisez le contenu de votre grille)
        try {
            int idc = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
            this.grid = new Grid_commande(commande.tousLesCommandes(idc, (Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            content.add(this.grid);
        } catch(SQLException ex) {
            content.add(new H3("Problème BdD"));
        }
        
        content.add(new H3("Merci de votre achat"));
        HorizontalLayout H3 = new HorizontalLayout();
        content.add(H3);
        // Close button
        Button closeButton = new Button("Fermer", event -> factureDialog.close());
        H3.add(closeButton);
        // Telechargement button
        Button TelechargementB = new Button("Telecharger", event -> factureDialog.close());
        
        H3.add(TelechargementB);
        
        factureDialog.add(content);

        // Ouvrir la fenêtre modale
        factureDialog.open();
    }
    
    private void stylisation() {
        
        this.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "1200vh");
        
        facture.getStyle()
                .set("color", "Crimson")
                .set("background-color", "PowderBlue");
        
        this.titre.getStyle()
            .set("color", "Indigo")
            .set("border-radius", "10px") 
            .set("padding", "10px");
    }
}
