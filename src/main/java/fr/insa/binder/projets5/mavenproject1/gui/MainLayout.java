/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import javax.swing.text.html.ListView;

/**
 *
 * @author schmi
 */
public class MainLayout extends AppLayout{
    
    public MainLayout(){
        creatHeader();
        createDrawer();
    }

    private void creatHeader() {
        H1 logo = new H1("App PS5");
        logo.addClassName("logo");
        
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        
        addToNavbar(header);
                
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("Home", MainView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        
        addToDrawer(new VerticalLayout(
                listLink
        ));
        
        RouterLink listLink2 = new RouterLink("Graph", GraphView.class);
        listLink2.setHighlightCondition(HighlightConditions.sameLocation());
        
        addToDrawer(new VerticalLayout(
                listLink2
        ));
        
    }
    
    
}
