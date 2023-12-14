/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
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
    
    public login_client(Vue_principale_login main) {
        this.main = main ;
        this.vnom = new TextField("nom");
        this.vpass = new PasswordField("pass");
        this.vbLogin = new Button("login");
        this.add(this.vnom,this.vpass,this.vbLogin);
        this.vbLogin.addClickListener((event) -> {
            this.doLogin();
        });
    }
    
    public void doLogin() {
        String nom = this.vnom.getValue();
        String pass = this.vpass.getValue();
        try {
            Connection con = this.main.getSessionInfo().getConBdD();
            Optional<Utilisateur> user = GestionBdD.login(con, nom, pass);
            if(user.isEmpty()) {
                Notification.show("Utilisateur ou pass invalide");
            } else {
                this.main.getSessionInfo().setCurUser(user);
                this.main.setEntete(new EnteteAfterLogin(this.main));
                this.main.setMainContent(new MainAfterLogin(this.main));
            }
        } catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }        
    }
    
}
