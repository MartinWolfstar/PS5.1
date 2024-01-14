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
import fr.insa.binder.projets5.mavenproject1.Client;
import fr.insa.binder.projets5.mavenproject1.ImageT;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.gui.client.BarreGaucheClient;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
        utile.stylisation(this);
    }
    
    private void showFactureDialog() {
        Dialog factureDialog = new Dialog();
        //factureDialog.getStyle().setBackground("#FEE59D;");
        factureDialog.setCloseOnOutsideClick(true);
        factureDialog.setWidth("700px"); // Ajustez la largeur selon vos besoins
        factureDialog.setModal(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateDuJour = LocalDate.now().format(formatter);
        int idc = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
        Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        String nomClient;
        try {
            nomClient = Client.getnom_client(idc,con);
        } catch (SQLException ex) {
            Notification.show("echec lors de l'import du nom client" + ex);
            nomClient = "inconnu";
        }
        String adresseClient;
        try {
            adresseClient = Client.getAdd_client(idc,con);
        } catch (SQLException ex) {
            Notification.show("echec lors de l'import de l'adresse client" + ex);
            adresseClient = "inconnu";
        }
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String numeroCommande = randomNumber+"-FR67";
        String idclient = "0C0-"+idc;
        
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

        try {
            this.grid = new Grid_commande(commande.tousLesCommandes(idc, (Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            content.add(this.grid);
        } catch(SQLException ex) {
            content.add(new H3("Problème BdD"));
        }
        
        content.add(new H3("Merci de votre achat"));
        HorizontalLayout H3 = new HorizontalLayout();
        content.add(H3);
        Button closeButton = new Button("Fermer", event -> factureDialog.close());
        H3.add(closeButton);
        Button TelechargementB = new Button("Telecharger", event -> factureDialog.close());
        H3.add(TelechargementB);
        factureDialog.add(content);
        factureDialog.open();
    }
}
