package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.utile;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("ListeMachine")
@Route(value = "21", layout = BarreGaucheTechnicien.class)
public class ListeMachine extends VerticalLayout {

    private Grid_machine grid;
    private HorizontalLayout H1;
    private HorizontalLayout H2;
    private VerticalLayout H3;
   
    public ListeMachine() {
        this.add(new H3("Liste de toutes les machines"));
        H1 = new HorizontalLayout();
        H1.add(new Ajout_machine());
        this.add(H1);
        H2 = new HorizontalLayout();
        H2.add(new Ajout_etat_machine());
        this.add(H2);
        //H3 = new VerticalLayout();
        //H3.add(new Sup_etat_machine());
        //this.add(H3);
        try {
            this.grid = new Grid_machine(machine.tousLesMachines((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.grid.setMinHeight(50, Unit.VMIN);
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : liste machine : " + ex));
        }
        this.add(new Etat_d_une_machine());
        addClassName("liste_machine");
        setSizeFull();
        
       utile.stylisation(this);
    }


}

