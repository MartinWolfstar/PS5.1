/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMessage;

import com.vaadin.flow.component.grid.Grid;

import fr.insa.binder.projets5.mavenproject1.messagerie;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_message extends Grid<messagerie>{
    
    public Grid_message(List<messagerie> list_messagerie) {
        this.setItems(list_messagerie);
        Grid.Column<messagerie> nom = this.addColumn(messagerie::getNom).setHeader("Nom");
        Grid.Column<messagerie> mes =this.addColumn(messagerie::getMes).setHeader("Message");  
        
        nom.setWidth("100px");
        mes.setWidth("900px");

    }
}
