package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienRealisation;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Gestion;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.realisation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@PageTitle("Listerealisation")
@Route(value = "31", layout = BarreGaucheTechnicien.class)
public class ListeRealisation extends VerticalLayout {

    private Grid_realisation grid;
    private HorizontalLayout H1;
    private Button raz;
   
    public ListeRealisation() {
        this.add(new H3("Liste de toutes les réalisations"));
        H1 = new HorizontalLayout();
        H1.add(new Ajout_realisation());
        this.raz = new Button();
        this.add(H1);
        try {
            this.grid = new Grid_realisation(realisation.tousLesRealisation((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème liste réalisation : " + ex));
            //Notification.show("Problème BdD : liste realisation :"+ ex);
        }
        this.raz.addClickListener((event) -> {
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                Gestion gest = new Gestion(con);
                gest.razBdD((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                Notification.show("test");
            } catch (SQLException ex) {
                Notification.show("Problème BdD : raz BDD " + ex);
            }
        });
        //au cas ou il y aurait un problème et que  la Bdd doit etre réinitialisé :
        raz.addClickShortcut(Key.KEY_R, KeyModifier.ALT);
        raz.getStyle().setOpacity("0");
        this.add(raz);
        
        addClassName("liste_réalisation");
        setSizeFull();
        utile.stylisation(this);
    }
}

