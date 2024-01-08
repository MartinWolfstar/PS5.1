package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienHabilitation;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.operateur_poste_de_travail;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("ListeHabilitation")
@Route(value = "34", layout = BarreGaucheTechnicien.class)
public class Habilitation extends VerticalLayout {

    private Grid_Habilitation grid;
    private HorizontalLayout H1;
    private VerticalLayout V1;
   
    public Habilitation() {
        
        V1 = new VerticalLayout();
        V1.add(new H3("Liste de toutes les Types de etat"));
        H1 = new HorizontalLayout();
        H1.add(new Ajout_Habilitation());
        this.add(H1);
        try {
            this.grid = new Grid_Habilitation(operateur_poste_de_travail.tousLesOpe_Poste((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : lhab"));
        }
        
        addClassName("liste_habilitation");
        setSizeFull();
        utile.stylisation(this);
    }
    
}

