/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import fr.insa.binder.projets5.mavenproject1.gui.*;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import static fr.insa.binder.projets5.mavenproject1.Client.getnom_client;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author schmi
 */
@PageTitle("Parametre")
@Route(value = "12", layout = BarreGaucheClient.class)
public class ParametreClient extends VerticalLayout{
    
    private TextField nom;
    private TextField prenom;
    private TextField adresse;
    private TextField mail;
    private TextField telephone;
    private PasswordField mdp;
    private Button sauvegarder;
    
    public ParametreClient() {
        String nom_prenom = "";
        try {
            nom_prenom = getnom_client((Integer) VaadinSession.getCurrent().getAttribute("id_client"), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
        }
        catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }   
        H1 nom_client = new H1(nom_prenom);
        nom = new TextField("votre nom :");
        prenom = new TextField("votre prénom :");
        adresse = new TextField("votre adresse :");
        mail = new TextField("votre adresse mail :");
        telephone = new TextField("votre numéro de téléphone :");
        mdp = new PasswordField("changer votre mot de passe :");
        sauvegarder = new Button("Sauvegarder les informations");
        //ALD = new AppLayoutDrawer();
        mdp.setValue("Ex@mplePassw0rd");
        
        
        sauvegarder.addClickListener(e -> {
            Notification.show("Hello " + nom.getValue());
        });
        sauvegarder.addClickShortcut(Key.ENTER);

        setMargin(true);
        //setHorizontalComponentAlignment(FlexComponent.Alignment.END, name, sayHello);

        add(nom_client, nom, prenom, adresse, mail, telephone, mdp, sauvegarder);
    }
}
