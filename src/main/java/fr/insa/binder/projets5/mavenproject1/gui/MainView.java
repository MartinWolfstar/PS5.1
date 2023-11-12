package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.binder.projets5.mavenproject1.Gestion;
import fr.insa.binder.projets5.mavenproject1.machine;

@PageTitle("main")
@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {


    Grid<machine> grid = new Grid<>(machine.class);
    private Gestion gestion;
    

    public MainView(Gestion gestion) {
        
        this.gestion = gestion;
        addClassName("lis-view");
        setSizeFull();
        configureGrid();
        
        add(grid);

    }

    private void configureGrid() {
        
    }

}

