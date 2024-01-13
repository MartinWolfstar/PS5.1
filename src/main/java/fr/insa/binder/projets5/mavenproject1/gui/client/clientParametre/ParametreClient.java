/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientParametre;

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
import fr.insa.binder.projets5.mavenproject1.Client;
import fr.insa.binder.projets5.mavenproject1.gui.client.BarreGaucheClient;
import static fr.insa.binder.projets5.mavenproject1.Client.getnom_client;
import fr.insa.binder.projets5.mavenproject1.ImageT;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        mdp.setValue("Ex@mplePassw0rd");
        
        stylisation();
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

        add(nom_client, nom, prenom, adresse, mail, telephone, mdp, sauvegarder);
        
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
                    .set("height", "200vh");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
        
        nom.getStyle()
                .set("color", "Crimson");
        prenom.getStyle()
                .set("color", "Crimson");
        adresse.getStyle()
                .set("color", "Crimson");
        mail.getStyle()
                .set("color", "Crimson");
        telephone.getStyle()
                .set("color", "Crimson");
        mdp.getStyle()
                .set("color", "Crimson");
        sauvegarder.getStyle()
                .set("color", "Crimson")
                .set("background-color", "PowderBlue");
        
    }
}
