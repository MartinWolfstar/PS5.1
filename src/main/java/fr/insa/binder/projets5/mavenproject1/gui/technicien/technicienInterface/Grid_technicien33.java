/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.etat;
import java.util.List;

/**
 *
 * @author melan
 */
public class Grid_technicien33 extends Grid<etat>{
    public Grid_technicien33(List<etat> list_etat){
        this.setItems(list_etat);
        this.addColumn(etat::getId_etat).setHeader("Id_etat");
        //this.addColumn(type_etat::getDes_type_etat).setHeader("getDes_type_etat");  
        this.addColumn(etat::getId_type_etat).setHeader("Id_type_etat");
        this.addColumn(etat::getDebut).setHeader("Debut");
        this.addColumn(etat::getFin).setHeader("Fin");
        this.getStyle().setBackground("PowderBlue");
    }
    
}