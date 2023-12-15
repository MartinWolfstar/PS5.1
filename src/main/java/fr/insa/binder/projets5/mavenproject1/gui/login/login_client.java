/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Client;
import static fr.insa.binder.projets5.mavenproject1.Client.login;
import fr.insa.binder.projets5.mavenproject1.gui.MainView;
import fr.insa.binder.projets5.mavenproject1.gui.client.ProduitClient;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author binde
 * 
 * 
 */
public class login_client extends VerticalLayout {
    
    private Vue_principale_login main;
    private TextField vnom;
    private PasswordField vpass;
    private Button vbLogin;
    private Button inscription;
    
    public login_client(Vue_principale_login main) {
        this.main = main ;
        this.vnom = new TextField("Login");
        this.vpass = new PasswordField("Mot de passe");
        this.vbLogin = new Button("Login");
        this.inscription = new Button("Inscription");
        this.add(this.vnom,this.vpass,this.vbLogin, this.inscription);
        this.vbLogin.addClickListener((event) -> {
            this.doLogin();
        });
    }
    
    public void doLogin() {
        String nom = this.vnom.getValue();
        String pass = this.vpass.getValue();
        try {
            Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
            Optional<Integer> user = login(con, nom, pass);
            if(user.isEmpty()) {
                Notification.show("Utilisateur ou pass invalide");
            } else {
                VaadinSession.getCurrent().setAttribute("id_client", user.get());
                UI.getCurrent().navigate(ProduitClient.class);
//                RouterLink listLink = new RouterLink("Produit", ProduitClient.class);
//                this.main.add(listLink);
            }
        } catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }        
    }
    
}

