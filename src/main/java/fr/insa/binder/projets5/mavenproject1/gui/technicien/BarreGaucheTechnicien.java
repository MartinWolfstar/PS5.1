/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien;

import fr.insa.binder.projets5.mavenproject1.gui.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import fr.insa.binder.projets5.mavenproject1.Gestion;
import java.sql.Connection;
import javax.swing.text.html.ListView;

/**
 *
 * @author schmi
 */
@PageTitle("barreGaucheTech")
@Route(value = "20", layout = BarreGaucheTechnicien.class)
public class BarreGaucheTechnicien extends AppLayout{
    
    public BarreGaucheTechnicien(){
        creatHeader();
        createDrawer();
    }

    private void creatHeader() {
        H1 logo = new H1("App PS5 technicien");
        logo.addClassName("logo");
        
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        
        addToNavbar(header);
                
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("ListeMachine", ListeMachine.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        
        addToDrawer(new VerticalLayout(
                listLink
        ));
        RouterLink listLink2 = new RouterLink("Plan", technicien_PlanUsine.class);
        listLink2.setHighlightCondition(HighlightConditions.sameLocation());
        
        addToDrawer(new VerticalLayout(
                listLink2
        ));
        RouterLink listLink3 = new RouterLink("Messagerie", technicienMessagerie.class);
        listLink3.setHighlightCondition(HighlightConditions.sameLocation());
        
        addToDrawer(new VerticalLayout(
                listLink3
        ));
//        RouterLink listLink3 = new RouterLink("Plan", technicien_PlanUsine.class);
//        listLink3.setHighlightCondition(HighlightConditions.sameLocation());
//        
//        addToDrawer(new VerticalLayout(
//                listLink3
//        ));
        
    }
    
    
}
