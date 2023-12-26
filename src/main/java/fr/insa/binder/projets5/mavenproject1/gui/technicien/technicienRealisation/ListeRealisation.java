package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienRealisation;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.realisation;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("Listerealisation")
@Route(value = "31", layout = BarreGaucheTechnicien.class)
public class ListeRealisation extends VerticalLayout {

    private Grid_realisation grid;
    private HorizontalLayout H1;
   
    public ListeRealisation() {
        this.add(new H3("Liste de toutes les realisations"));
        //H1 = new HorizontalLayout();
        //H1.add(new Ajout_realisation());
        //this.add(H1);
        try {
            this.grid = new Grid_realisation(realisation.tousLesRealisation((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème liste realisation : " + ex));
            //Notification.show("Problème BdD : liste realisation :"+ ex);
        }
        
        addClassName("liste_realisation");
        setSizeFull();
        stylisation();
    }
    private void stylisation() {
        
        this.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "120vh");
    }
}

