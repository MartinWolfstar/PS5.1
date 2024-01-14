/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.type_operation;
import java.util.List;

public class Grid_typeOperation extends Grid<type_operation>{
    
    public Grid_typeOperation(List<type_operation> list_type_operation) {
        this.setItems(list_type_operation);
        this.addColumn(type_operation::getId_type_operation).setHeader("getId_type_operation");
        this.addColumn(type_operation::getDes_type_operation).setHeader("getDes_type_operation");      
    }
}
