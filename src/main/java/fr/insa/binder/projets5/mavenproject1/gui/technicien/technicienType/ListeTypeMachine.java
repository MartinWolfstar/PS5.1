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
        
        V1.add(new H3("Liste de toutes les Types de machine"));
        H1 = new HorizontalLayout();
        try {
            this.grid = new Grid_typeMachine(type_machine.tousLesTypeMachine((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        
        addClassName("liste_type_machine");
        setSizeFull();
        H1.add(new Ajout_machine(),new Supp_machine(), new Modif_machine());
        this.add(H1);
    }
}

