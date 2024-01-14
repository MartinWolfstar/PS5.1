/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Client;
import static fr.insa.binder.projets5.mavenproject1.Client.login_c;
import fr.insa.binder.projets5.mavenproject1.ImageT;
import fr.insa.binder.projets5.mavenproject1.gui.client.clientProduit.ProduitClient;
import java.io.IOException;
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
    private VerticalLayout VL;
    
    public Inscription_client(Vue_principale_login main) {
        this.main = main;
        nom = new TextField("votre nom :");
        prenom = new TextField("votre prénom :");
        login = new TextField("votre login :");
        mdp = new PasswordField("Votre mot de passe :");
        sauvegarder = new Button("Sauvegarder les informations");
        this.VL = new VerticalLayout();
        prenom.setValue("titi");
        login.setValue("tototiti");
        //ALD = new AppLayoutDrawer();
        mdp.setValue("Ex@mplePassw0rd");
        
        
        sauvegarder.addClickListener(e -> {
            Client client = new Client(this.nom.getValue(), this.prenom.getValue(), this.login.getValue(), this.mdp.getValue());
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                client.saveInDBV(con);
                Optional<Integer> user = login_c(con, this.login.getValue(), this.mdp.getValue());
                VaadinSession.getCurrent().setAttribute("id_client", user.get());
                UI.getCurrent().navigate(ProduitClient.class);
                } catch (SQLException ex) {
                Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }

            
        });
        sauvegarder.addClickShortcut(Key.ENTER);

        setMargin(true);

        VL.add(nom, prenom, login, mdp, sauvegarder);
        add(VL);
        stylisation();
    }
    private void stylisation() {
        
//        this.getStyle()
//            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
//            .set("background-size", "cover")
//            .set("height", "100vh");
        
        String imageName = "fdecran.jpg";
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            ImageT image = ImageT.getImageByName(conn, imageName);

            if (image != null) {
                
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());

                VL.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover")
                    .set("height", "100%");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
        
        VL.getStyle()
            .set("margin", "auto")
            .set("text-align", "center")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("justify-content", "center")
            .set("align-items", "center");  
            
    }
}

