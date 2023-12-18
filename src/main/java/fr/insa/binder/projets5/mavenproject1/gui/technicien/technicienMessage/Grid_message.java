/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMessage;

import com.vaadin.flow.component.grid.Grid;

import fr.insa.binder.projets5.mavenproject1.messagerie;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_message extends Grid<messagerie>{
    
    public Grid_message(List<messagerie> list_messagerie) {
        this.setItems(list_messagerie);
        this.addColumn(messagerie::getId_op).setHeader("Nom");
        this.addColumn(messagerie::getMes).setHeader("Message");  
    }
}
