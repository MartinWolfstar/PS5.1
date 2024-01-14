/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
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
import fr.insa.binder.projets5.mavenproject1.operateur;
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
        mdp = new PasswordField("changer votre mot de passe :");
        sauvegarder = new Button("Sauvegarder les informations");
        nom.setValue(nom.getValue());
        prenom.setValue(prenom.getValue());
        mdp.setValue(mdp.getValue());
        //mdp.setValue("Ex@mplePassw0rd");
        H4 = new HorizontalLayout();
        H4.add(nom, prenom, mdp);
        
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        int id_o = (Integer) VaadinSession.getCurrent().getAttribute("id_operateur");
        

        
        sauvegarder.addClickListener(e -> {
            try {
                operateur.setNom(nom.getValue(),id_o, conn);
                operateur.setPrenom(prenom.getValue(),id_o, conn);
                operateur.setPassword(mdp.getValue(),id_o, conn);
            } catch (SQLException ex) {
                Notification.show("Problème interne des parametres: " + ex);
            }
        });
        sauvegarder.addClickShortcut(Key.ENTER);

        setMargin(true);
        //add(nom_technicien, nom, prenom, mail, menu_bar, mdp, sauvegarder);
        this.add(new H5("Informations personnelles"));
        add(H4, sauvegarder);
        
        this.add(new H5("Liste de tous les etats d'un operateur"));
        H1 = new HorizontalLayout();
        H1.add(new Ajout_etat_technicien());
        this.add(H1);
        //H2 = new VerticalLayout();
        this.add(new Supp_etat());
        //this.add(H2);
        try{
            this.grid = new Grid_technicien33(get_etat_d_un_operateur((Connection) VaadinSession.getCurrent().getAttribute("conn"), this.id_operateur));
            this.add(this.grid);
        }catch (SQLException ex){
            this.add(new H3("Problème BdD : liste etat : " + ex));
        }
        addClassName("list_etat");
        setSizeFull();
        utile.stylisation(this,nom,prenom,mdp,sauvegarder);
    }
}
