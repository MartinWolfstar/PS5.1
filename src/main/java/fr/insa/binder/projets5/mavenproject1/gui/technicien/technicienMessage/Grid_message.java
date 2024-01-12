/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMessage;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;

import fr.insa.binder.projets5.mavenproject1.messagerie;
import fr.insa.binder.projets5.mavenproject1.operateur;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_message extends Grid<messagerie>{
    
    public Grid_message(List<messagerie> list_messagerie) {
        this.setItems(list_messagerie);
//        
//        String nom = operateur.getnom_operateur(messagerie::getId_op,(Connection) VaadinSession.getCurrent().getAttribute("conn"));
//        
//        this.addColumn(nom).setHeader("Nom");
        this.addColumn(messagerie::getMes).setHeader("Message");  
        this.getStyle().setBackground("PowderBlue");
    }
}
