package fr.insa.binder.projets5.mavenproject1.gui.technicien;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.messagerie;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("Messagerie")
@Route(value = "24", layout = BarreGaucheTechnicien.class)
public class technicienMessagerie extends VerticalLayout {

    private Grid_message grid;
    private HorizontalLayout H1;
   
    public technicienMessagerie() {
        this.add(new H3("Messagerie interne"));
        H1 = new HorizontalLayout();
        try {
            this.grid = new Grid_message(messagerie.tousLesMessages((Connection) VaadinSession.getCurrent().getAttribute("conn"))); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        
        addClassName("liste_message");
        setSizeFull();
        H1.add(new Ajout_message());
        this.add(H1);
    }
}

