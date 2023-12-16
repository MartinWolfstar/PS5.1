/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.Ajout_machine;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.Modif_machine;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.Supp_machine;
import java.sql.Connection;
import java.sql.SQLException;

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
    
    public CommandeClient() {
        
        this.add(new H3("Liste de toutes les commandes"));
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
        });
        facture.addClickShortcut(Key.ENTER);
        setMargin(true);
        //setHorizontalComponentAlignment(FlexComponent.Alignment.END, name, sayHello);
        add(facture);
    }
}