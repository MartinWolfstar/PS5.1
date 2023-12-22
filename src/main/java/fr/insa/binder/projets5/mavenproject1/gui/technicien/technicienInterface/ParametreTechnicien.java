/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import static fr.insa.binder.projets5.mavenproject1.operateur.getnom_operateur;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author schmi
 */
@PageTitle("ParametreTech")
@Route(value = "30", layout = BarreGaucheTechnicien.class)
public class ParametreTechnicien extends VerticalLayout{
    
    private TextField nom;
    private TextField prenom;
    private TextField mail;
//    private MenuBar menu_bar;
//    private MenuItem id;
    private PasswordField mdp;
    private Button sauvegarder;
    
    public ParametreTechnicien() {
//        String nom_prenom = "";
//        try {
//            nom_prenom = getnom_operateur((Integer) VaadinSession.getCurrent().getAttribute("id_operateur"), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
//        }
//        catch (SQLException ex) {
//            Notification.show("Problème interne : " + ex.getLocalizedMessage());
//        }   
//        H1 nom_technicien = new H1(nom_prenom);
        nom = new TextField("votre nom :");
        prenom = new TextField("votre prénom :");
        mail = new TextField("votre adresse mail :");
        
//        this.menu_bar = new MenuBar();
//        this.id = menu_bar.addItem("Selectionner votre Etat");
//        SubMenu id_sub = id.getSubMenu();
        
        mdp = new PasswordField("changer votre mot de passe :");
        sauvegarder = new Button("Sauvegarder les informations");
        mdp.setValue("Ex@mplePassw0rd");
        
//        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
//                {
//            int id_m = Integer.valueOf(e.getSource().getText());
//            this.modif_etat = new Modif_etat(id_m);
//            this.add(modif_etat);
//        };
//        try {
//            List<operateur> id_liste = operateur.tousLesOperateur((Connection) VaadinSession.getCurrent().getAttribute("conn"));
//            for (operateur x : id_liste) { 
//                id_sub.addItem(String.valueOf(x.getId_operateur()), listener);
//            }      
//        } 
//        catch(SQLException ex) {
//               Notification.show("Problème BdD : mtm");
//        }
        
        sauvegarder.addClickListener(e -> {
            Notification.show("Hello " + nom.getValue());
        });
        sauvegarder.addClickShortcut(Key.ENTER);

        setMargin(true);
        //add(nom_technicien, nom, prenom, mail, menu_bar, mdp, sauvegarder);
        add(nom, prenom, mail, mdp, sauvegarder);
    }
}
