/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.machine;
import fr.insa.binder.projets5.mavenproject1.realisation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_machine extends Grid<machine>{
    
    public Grid_machine(List<machine> list_machine) {
        this.setItems(list_machine);
        this.addColumn(machine::getRef).setHeader("Reference");
        this.addColumn(machine::getDes).setHeader("Description");
        this.addColumn(machine::getId).setHeader("Identifiant");    
        
        this.addComponentColumn(machine -> {
            Button button = new Button("Supprimer", clickEvent -> {
                try {
                    machine.supMachine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    UI.getCurrent().getPage().reload();
                    this.setItems(machine.tousLesMachines((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : grid machine : " + ex);
                    // Gérez les erreurs ici
                }
            });
            return button;
        }).setHeader("");
        
        this.getStyle().setBackground("PowderBlue");
    }
}
