package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.type_etat;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("ListeTypeEtat")
@Route(value = "29", layout = BarreGaucheTechnicien.class)
public class ListeTypeEtat extends VerticalLayout {

    private Grid_typeEtat grid;
    private HorizontalLayout H1;
    private VerticalLayout V1;
   
    public ListeTypeEtat() {
        
        V1 = new VerticalLayout();
        V1.add(new H3("Liste de toutes les Types de etat"));
        H1 = new HorizontalLayout();
        try {
            this.grid = new Grid_typeEtat(type_etat.tousLesTypeEtats((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : lte"));
        }
        
        addClassName("liste_type_etat");
        setSizeFull();
        H1.add(new Ajout_type_etat(),new Supp_type_etat(), new Modif_type_etat());
        this.add(H1);
        stylisation();
    }
    private void stylisation() {
        
        this.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "120vh");
    }
}

