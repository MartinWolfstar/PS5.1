/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientParametre;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Client;
import fr.insa.binder.projets5.mavenproject1.gui.client.BarreGaucheClient;
import static fr.insa.binder.projets5.mavenproject1.Client.getnom_client;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
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
    private VerticalLayout V1;
    private VerticalLayout V2;
    private HorizontalLayout H1;
    
    public ParametreClient() {
        
        String nom_prenom = "";
        try {
            nom_prenom = getnom_client((Integer) VaadinSession.getCurrent().getAttribute("id_client"), (Connection) VaadinSession.getCurrent().getAttribute("conn"));  
        }
        catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }  
        
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        int id_c = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
        H1 nom_client = new H1(nom_prenom);
        nom = new TextField("votre nom :");
        prenom = new TextField("votre prénom :");
        adresse = new TextField("votre adresse :");
        mail = new TextField("votre adresse mail :");
        telephone = new TextField("votre numéro de téléphone :");
        mdp = new PasswordField("changer votre mot de passe :");
        sauvegarder = new Button("Sauvegarder les informations");
        //ALD = new AppLayoutDrawer();
        nom.setValue(nom.getValue());
        prenom.setValue(prenom.getValue());
        adresse.setValue(adresse.getValue());
        mail.setValue(mail.getValue());
        telephone.setValue(telephone.getValue());
        mdp.setValue(mdp.getValue());

        sauvegarder.addClickListener(e -> {
            try {
                Client.setNom(nom.getValue(),id_c, conn);
                Client.setPrenom(prenom.getValue(),id_c, conn);
                Client.setAdresse(adresse.getValue(),id_c, conn);
                Client.setMail(mail.getValue(),id_c, conn);
                Client.setTelephone(telephone.getValue(),id_c, conn);
                Client.setPassword(mdp.getValue(),id_c, conn);
            } catch (SQLException ex) {
                Notification.show("Problème interne des parametres: " + ex);
            }
        });
        sauvegarder.addClickShortcut(Key.ENTER);
        setMargin(true);
        //setHorizontalComponentAlignment(FlexComponent.Alignment.END, name, sayHello);

        V1 = new VerticalLayout();
        V2 = new VerticalLayout();
        H1 = new HorizontalLayout();
        V1.add(nom, prenom, adresse);
        V2.add(mail, telephone, mdp);
        H1.add(V1,V2);
        add(nom_client,H1,sauvegarder);
        utile.stylisation(this);
    }
}
