/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienHabilitation;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.operateur_poste_de_travail;
import java.util.List;

public class Grid_Habilitation extends Grid<operateur_poste_de_travail>{
    
    public Grid_Habilitation(List<operateur_poste_de_travail> liste_operateur_poste_de_travail) {
        this.setItems(liste_operateur_poste_de_travail);
        this.addColumn(operateur_poste_de_travail::getId_operateur).setHeader("getId_operateur");
        this.addColumn(operateur_poste_de_travail::getId_poste_de_travail).setHeader("getId_poste_de_travail");  
        this.getStyle().setBackground("PowderBlue");
    }
}
