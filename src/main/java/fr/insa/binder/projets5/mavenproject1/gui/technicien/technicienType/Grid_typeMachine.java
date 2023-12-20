/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation.*;
import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.Operation;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_typeMachine extends Grid<Operation>{
    
    public Grid_typeMachine(List<Operation> list_operation) {
        this.setItems(list_operation);
        this.addColumn(Operation::getId_operation).setHeader("getId_operation");
        this.addColumn(Operation::getId_produit).setHeader("getId_produit");       
    }
}
