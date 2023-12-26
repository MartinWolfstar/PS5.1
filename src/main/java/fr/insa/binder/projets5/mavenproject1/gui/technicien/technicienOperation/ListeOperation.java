package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.Operation;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("ListeOperation")
@Route(value = "26", layout = BarreGaucheTechnicien.class)
public class ListeOperation extends VerticalLayout {

    private Grid_operation grid;
    private HorizontalLayout H1;
   
    //TODO : mettre le nom des typeoperation et des produits et pas leurs références
    
    public ListeOperation() {
        this.add(new H3("Liste de toutes les Operations"));
        H1 = new HorizontalLayout();
        try {
            this.grid = new Grid_operation(Operation.tousLesOperations((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : o"));
        }
        addClassName("liste_operation");
        setSizeFull();
        H1.add(new Ajout_operation(),new Supp_operation(), new Modif_operation());
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

