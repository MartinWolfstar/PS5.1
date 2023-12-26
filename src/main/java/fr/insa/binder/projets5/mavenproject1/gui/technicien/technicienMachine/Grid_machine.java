/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.machine;
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
        this.getStyle().setBackground("PowderBlue");
    }
}
