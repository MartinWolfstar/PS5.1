/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.type_etat;
import java.util.List;

public class Grid_typeEtat extends Grid<type_etat>{
    
    public Grid_typeEtat(List<type_etat> list_etat) {
        this.setItems(list_etat);
        this.addColumn(type_etat::getId_type_etat).setHeader("getId_type_etat");
        this.addColumn(type_etat::getDes_type_etat).setHeader("getDes_type_etat");  
        this.getStyle().setBackground("PowderBlue");
    }
}
