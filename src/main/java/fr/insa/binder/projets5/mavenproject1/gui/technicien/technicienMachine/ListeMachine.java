package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("ListeMachine")
@Route(value = "21", layout = BarreGaucheTechnicien.class)
public class ListeMachine extends VerticalLayout {

    private Grid_machine grid;
    private HorizontalLayout H1;
   
    public ListeMachine() {
        this.add(new H3("Liste de toutes les machines"));
        H1 = new HorizontalLayout();
        H1.add(new Ajout_machine(), new Modif_machine());
        this.add(H1);
        try {
            this.grid = new Grid_machine(machine.tousLesMachines((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : liste machine : " + ex));
        }
        
        addClassName("liste_machine");
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

