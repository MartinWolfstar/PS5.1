/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Client;
import static fr.insa.binder.projets5.mavenproject1.Client.getnom_client;
import static fr.insa.binder.projets5.mavenproject1.Client.login;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.gui.client.ProduitClient;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author binde
 */
public  class Inscription_client extends VerticalLayout{
    private TextField nom;
    private Vue_principale_login main;
    private TextField prenom;
    private TextField login;
    private PasswordField mdp;
    private Button sauvegarder;
    
    public Inscription_client(Vue_principale_login main) {
        this.main = main;
        nom = new TextField("votre nom :");
        prenom = new TextField("votre prénom :");
        login = new TextField("votre login :");
        mdp = new PasswordField("Votre mot de passe :");
        sauvegarder = new Button("Sauvegarder les informations");
        //ALD = new AppLayoutDrawer();
        mdp.setValue("Ex@mplePassw0rd");
        
        
        sauvegarder.addClickListener(e -> {
            Client client = new Client(this.nom.getValue(), this.prenom.getValue(), this.login.getValue(), this.mdp.getValue());
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                client.saveInDBV(con);
                Optional<Integer> user = login(con, this.login.getValue(), this.mdp.getValue());
                VaadinSession.getCurrent().setAttribute("id_client", user.get());
                UI.getCurrent().navigate(ProduitClient.class);
                } catch (SQLException ex) {
                Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }
//            try {
//                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
//                Optional<Integer> user = login(con, this.login.getValue(), this.mdp.getValue());
//                if(user.isEmpty()) {
//                    Notification.show("Probleme problematique");
//                } else {
//                    VaadinSession.getCurrent().setAttribute("id_client", user.get());
//                    UI.getCurrent().navigate(ProduitClient.class);
//            }
//            } catch (SQLException ex) {
//                Notification.show("Autre Problème stupide");
//            }
            
        });
        sauvegarder.addClickShortcut(Key.ENTER);

        setMargin(true);
        //setHorizontalComponentAlignment(FlexComponent.Alignment.END, name, sayHello);

        add(nom, prenom, login, mdp, sauvegarder);
    }
}

