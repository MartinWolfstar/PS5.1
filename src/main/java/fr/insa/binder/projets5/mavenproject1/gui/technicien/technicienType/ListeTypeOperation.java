package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.*;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.Ajout_machine;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.type_operation;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("ListeTypeOperation")
@Route(value = "27", layout = BarreGaucheTechnicien.class)
public class ListeTypeOperation extends VerticalLayout {

    private Grid_typeOperation grid;
    private HorizontalLayout H1;
    private VerticalLayout V1;
   
    public ListeTypeOperation() {
        
        V1 = new VerticalLayout();
        V1.add(new H3("Liste de toutes les Types d'opération"));
        H1 = new HorizontalLayout();
        try {
            this.grid = new Grid_typeOperation(type_operation.tousLesTypeOperations((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : lto"));
        }
        
        addClassName("liste_type_operation");
        setSizeFull();
        H1.add(new Ajout_type_operation(),new Supp_type_operation(), new Modif_type_operation());
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

