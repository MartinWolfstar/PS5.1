/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.machine;
import static fr.insa.binder.projets5.mavenproject1.machine.setPosteDeTravail;
import fr.insa.binder.projets5.mavenproject1.poste_de_travail;
import static fr.insa.binder.projets5.mavenproject1.poste_de_travail.getId_poste_de_travail;
import static fr.insa.binder.projets5.mavenproject1.poste_de_travail.tousLesPostes_String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_machine extends Grid<machine>{
    
    public Grid_machine(List<machine> list_machine) {
        this.setItems(list_machine);
        this.addColumn(machine::getRef).setHeader("Reference");
        this.addColumn(machine::getDes).setHeader("Description");
        this.addColumn(machine::getId).setHeader("Identifiant");    
        
        //poste de travail
        this.addComponentColumn(machine -> {
            ComboBox<String> combo = new ComboBox<>();
            try {
                combo.setItems(tousLesPostes_String((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                combo.setValue(poste_de_travail.getRef_poste_de_travail(machine.getId_poste_de_travail(), (Connection) VaadinSession.getCurrent().getAttribute("conn")));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : 2 gridma");
            }

            combo.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            try {
                int id_pt = getId_poste_de_travail(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                machine.setId_poste_de_travail(id_pt);
                //Notification.show("t " + realisation.getId_type_operation()); 
                setPosteDeTravail(id_pt, machine.getId_poste_de_travail(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                //UI.getCurrent().getPage().reload();
                this.getDataProvider().refreshItem(machine);
//                this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : 3 gridma");
            }
            });

            combo.setAllowCustomValue(true);
            combo.addCustomValueSetListener(e -> {
                String customValue = e.getDetail();
                poste_de_travail pt = new poste_de_travail(customValue);
                try {
                    pt.save_poste_de_travail((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    Notification.show("pt : " + pt);
                    int id_pt = getId_poste_de_travail(customValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    machine.setId_poste_de_travail(id_pt);
                    setPosteDeTravail(machine.getId_poste_de_travail(), machine.getId(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(machine);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : grid machine pt" + ex);
                }

            });
            return combo;
        }).setHeader("poste de travail");
        
        this.addComponentColumn(machine -> {
            Button button = new Button("Supprimer", clickEvent -> {
                try {
                    machine.supMachine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.setItems(machine.tousLesMachines((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : grid machine : " + ex);
                    // Gérez les erreurs ici
                }
            });
            return button;
        }).setHeader("");
        
        this.getStyle().setBackground("PowderBlue");
    }
}
