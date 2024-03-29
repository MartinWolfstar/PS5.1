/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.ImageT;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienPlanUsine.technicien_PlanUsine;
import static fr.insa.binder.projets5.mavenproject1.operateur.login_o;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author binde
 */
public class login_operateur extends VerticalLayout {
    
    private Vue_principale_login main;
    private TextField vnom;
    private PasswordField vpass;
    private Button vbLogin;
    private Button inscription;
    private VerticalLayout VL;
    private HorizontalLayout VH;
    
    public login_operateur(Vue_principale_login main) {
        this.main = main ;
        this.vnom = new TextField("Login");
        this.vpass = new PasswordField("Mot de passe");
        this.vbLogin = new Button("Login");
        this.inscription = new Button("Inscription");
        this.VL = new VerticalLayout();
        this.VH = new HorizontalLayout();
        VH.add(vbLogin,inscription);
        VL.add(this.vnom,this.vpass,VH);
        this.add(VL);
        this.vbLogin.addClickListener((event) -> {
            this.doLogin();
        });
        this.inscription.addClickListener((event) -> {
            this.main.setMainContent(new Inscription_operateur(this.main));
        });
        vbLogin.addClickShortcut(Key.ENTER);
        stylisation();
        //this.inscription.setVisible(false);
        inscription.addClickShortcut(Key.KEY_I);;
    }
    
    public void doLogin() {
        String nom = this.vnom.getValue();
        String pass = this.vpass.getValue();
        try {
            Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
            Optional<Integer> user = login_o(con, nom, pass);
            if(user.isEmpty()) {
                Notification.show("Utilisateur ou pass invalide");
            } else {
                VaadinSession.getCurrent().setAttribute("id_operateur", user.get());
                UI.getCurrent().navigate(technicien_PlanUsine.class);
//                RouterLink listLink = new RouterLink("Produit", ProduitClient.class);
//                this.main.add(listLink);
            }
        } catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }        
    }
    private void stylisation() {
        
//        this.getStyle()
//            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
//            .set("background-size", "cover")
//            .set("height", "100vh");
        String imageName = "fdecran.jpg";
        
        // Connexion à la base de données (assurez-vous que votre connexion est établie correctement)
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");

        try {
            // Récupération de l'image depuis la base de données
            ImageT image = ImageT.getImageByName(conn, imageName);

            if (image != null) {
                // Convertir les données binaires de l'image en base64 pour l'utiliser dans le style
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());

                this.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover")
                    .set("height", "100%");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
            // Gérer les exceptions appropriées ici
        }
        
        VL.getStyle()
            .set("margin", "auto")
            .set("text-align", "center")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("justify-content", "center")
            .set("align-items", "center");  
            
//        vbLogin.getStyle()
//            .set("color", "#FEE59D")
//            .set("background-color", "#030876");
//        
//        inscription.getStyle()
//            .set("color", "#FEE59D")
//            .set("background-color", "#030876");
//        
//        vnom.getStyle()
//            .set("color", "#030876");
//        vpass.getStyle()
//            .set("color", "#030876");
        
    }
    
}

