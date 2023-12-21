/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.type_machine;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_typeMachine extends Grid<type_machine>{
    
    public Grid_typeMachine(List<type_machine> list_machine) {
        this.setItems(list_machine);
        this.addColumn(type_machine::getId_type_machine).setHeader("getId_type_machine");
        this.addColumn(type_machine::getDes_type_machine).setHeader("getDes_type_machine");       
    }
}
