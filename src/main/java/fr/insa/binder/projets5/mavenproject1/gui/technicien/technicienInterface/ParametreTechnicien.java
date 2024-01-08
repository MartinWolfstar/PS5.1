/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import static fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface.Grid_technicien33.get_etat_d_un_operateur;
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
    private Grid_technicien33 grid;
    private HorizontalLayout H1;
    private VerticalLayout H2;
    private HorizontalLayout H4;
    private int id_operateur;
    
    public ParametreTechnicien() {
         
        id_operateur = (int) VaadinSession.getCurrent().getAttribute("id_operateur");
        nom = new TextField("votre nom :");
        prenom = new TextField("votre prénom :");
        mail = new TextField("votre adresse mail :");
        mdp = new PasswordField("changer votre mot de passe :");
        sauvegarder = new Button("Sauvegarder les informations");
        mdp.setValue("Ex@mplePassw0rd");
        H4 = new HorizontalLayout();
        H4.add(nom, prenom, mail, mdp);
        
        sauvegarder.addClickListener(e -> {
            Notification.show("Hello " + nom.getValue());
        });
        sauvegarder.addClickShortcut(Key.ENTER);

        setMargin(true);
        //add(nom_technicien, nom, prenom, mail, menu_bar, mdp, sauvegarder);
        add(H4, sauvegarder);
        
        this.add(new H3("Liste de tous les etats d'un operateur"));
        H1 = new HorizontalLayout();
        H1.add(new Ajout_etat_technicien());
        this.add(H1);
        H2 = new VerticalLayout();
        H2.add(new Supp_etat());
        this.add(H2);
        try{
            this.grid = new Grid_technicien33(get_etat_d_un_operateur((Connection) VaadinSession.getCurrent().getAttribute("conn"), this.id_operateur));
            this.add(this.grid);
        }catch (SQLException ex){
            this.add(new H3("Problème BdD : liste etat : " + ex));
        }
        addClassName("list_etat");
        setSizeFull();
        utile.stylisation(this,nom,prenom,mail,mdp,sauvegarder);
    }
}
