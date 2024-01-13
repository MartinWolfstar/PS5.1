/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienHabilitation;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.operateur_poste_de_travail;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Grid_Habilitation extends Grid<operateur_poste_de_travail>{
    
    public Grid_Habilitation(List<operateur_poste_de_travail> liste_operateur_poste_de_travail) {
        this.setItems(liste_operateur_poste_de_travail);
        this.addColumn(operateur_poste_de_travail::getNom_operateur).setHeader("Nom de l'opérateur");
        this.addColumn(operateur_poste_de_travail::getId_poste_de_travail).setHeader("Id du poste de travail");
        
        this.addComponentColumn(operateur_poste_de_travail -> {
            Button button = new Button("Supprimer", clickEvent -> {
                try {
                    operateur_poste_de_travail.supOpe_Poste((Connection) VaadinSession.getCurrent().getAttribute("conn"),operateur_poste_de_travail.getId_poste_de_travail());
                    UI.getCurrent().getPage().reload();
                    this.setItems(operateur_poste_de_travail.tousLesOpe_Poste((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : grid machine : " + ex);
                }
            });
            return button;
        }).setHeader("");
        
        
        this.getStyle().setBackground("PowderBlue");
    }
}
