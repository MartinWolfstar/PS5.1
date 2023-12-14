package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.binder.projets5.mavenproject1.Gestion;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.Connection;
import java.sql.SQLException;

@PageTitle("main")
@Route(value = "1", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    private Grid_machine grid;
    private HorizontalLayout H1;
   
    public MainView() {
        this.add(new H3("Liste de toutes les machines"));
        H1 = new HorizontalLayout();
        try {
            this.grid = new Grid_machine(machine.tousLesMachines(connectSurServeurM3())); 
            this.add(this.grid);
        } catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        
        addClassName("liste_machine");
        setSizeFull();
        H1.add(new Ajout_machine(),new Supp_machine(), new Modif_machine());
        this.add(H1);
    }
    
//    public MainView(){
//        
//    }
//    
//    @Override
//    public void setParameter(BeforeEvent event, Connection parameter){
//        this.conect = parameter;
//    }
//    
//    @Override
//    public void afterNavigation(AfterNavigationEvent event){
//        this.add(new H3("Liste de toute les machines"));
//        try {
//            this.grid = new Grid_machine(machine.tousLesMachines(this.conect)); 
//            this.add(this.grid);
//        } catch(SQLException ex) {
//            this.add(new H3("Problème BdD : "));
//        }
//        
//        addClassName("lis-view");
//        setSizeFull();
//    }
//    

}

