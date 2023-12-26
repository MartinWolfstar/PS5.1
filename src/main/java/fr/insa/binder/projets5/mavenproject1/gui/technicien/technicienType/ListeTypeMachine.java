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
import fr.insa.binder.projets5.mavenproject1.type_machine;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("ListeTypeMachine")
@Route(value = "28", layout = BarreGaucheTechnicien.class)
public class ListeTypeMachine extends VerticalLayout {

    private Grid_typeMachine grid;
    private HorizontalLayout H1;
    private VerticalLayout V1;
   
    public ListeTypeMachine() {
        
        V1 = new VerticalLayout();
        V1.add(new H3("Liste de toutes les Types de machine"));
        H1 = new HorizontalLayout();
        H1.add(new Ajout_type_machine(),new Supp_type_machine(), new Modif_type_machine());
        this.add(H1);
        try {
            this.grid = new Grid_typeMachine(type_machine.tousLesTypeMachine((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : ltm"));
        }
        
        addClassName("liste_type_machine");
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

