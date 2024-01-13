/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienRealisation;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.machine;
import static fr.insa.binder.projets5.mavenproject1.machine.getId_machine;
import static fr.insa.binder.projets5.mavenproject1.machine.tousLesMachines_String;
import fr.insa.binder.projets5.mavenproject1.realisation;
import static fr.insa.binder.projets5.mavenproject1.realisation.setMachine;
import static fr.insa.binder.projets5.mavenproject1.realisation.setTypeOperation;
import fr.insa.binder.projets5.mavenproject1.type_operation;
import static fr.insa.binder.projets5.mavenproject1.type_operation.getId_type_operation;
import static fr.insa.binder.projets5.mavenproject1.type_operation.tousLesTypeOperations_String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_realisation extends Grid<realisation>{
    
    public Grid_realisation(List<realisation> list_realisation) {
//        this.setItems(list_realisation);
//        this.addColumn(realisation::getDuree).setHeader("getDuree");
//        this.addColumn(realisation::getId_type_operation).setHeader("getId_type_operation");
//        this.addColumn(realisation::getId_machine).setHeader("getId_machine");     
//        this.getStyle().setBackground("PowderBlue");

        Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            this.setItems(realisation.tousLesRealisation((Connection) VaadinSession.getCurrent().getAttribute("conn")));
        } catch (SQLException ex) {
            Notification.show("Problème BdD : 1 rea");
        }

        this.addColumn(realisation::getId_realisation).setHeader("Id_realisation");
        
        //duree
        this.addColumn(realisation::getDuree).setHeader("Duree");
        
        //type operation
        //Grid.Column<realisation> id = this.addColumn(realisation::getId_type_operation).setHeader("Id_type_operation1");
        this.addComponentColumn(realisation -> {
            ComboBox<String> combo = new ComboBox<>();
            try {
                combo.setItems(tousLesTypeOperations_String((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                combo.setValue(type_operation.getDes_type_operation(realisation.getId_type_operation(), (Connection) VaadinSession.getCurrent().getAttribute("conn")));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : 2 rea");
            }

            combo.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            try {
                int id_type_op = getId_type_operation(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                realisation.setId_type_operation(id_type_op);
                //Notification.show("t " + realisation.getId_type_operation()); 
                setTypeOperation(id_type_op, realisation.getId_realisation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
                this.getDataProvider().refreshItem(realisation);
//                this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : 3 rea");
            }
            });

            combo.setAllowCustomValue(true);
            combo.addCustomValueSetListener(e -> {
                String customValue = e.getDetail();
                type_operation t_y = new type_operation(customValue);
                try {
                    t_y.save_type_operation((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    int id_type_op = getId_type_operation(customValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    realisation.setId_type_operation(id_type_op);
                    setTypeOperation(realisation.getId_type_operation(), realisation.getId_realisation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(realisation);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : 4 rea");
                }

            });
            return combo;
        }).setHeader("Type operation");
        
        //Grid.Column<realisation> id_machine = this.addColumn(realisation::getId_machine).setHeader("Id_machine1");

        //machine
        this.addComponentColumn(realisation -> {
            ComboBox<String> combo = new ComboBox<>();
            try {
                combo.setItems(tousLesMachines_String((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                combo.setValue(machine.getDes_machine(realisation.getId_machine(), (Connection) VaadinSession.getCurrent().getAttribute("conn")));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : 5 rea");
            }

            combo.addValueChangeListener(event -> {
                String selectedValue = event.getValue();
                try {
                    int id_ma = getId_machine(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    realisation.setId_machine(id_ma);
                    setMachine(realisation.getId_machine(), realisation.getId_realisation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(realisation);
//                    this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : 6 rea");
                }
            });

//            combo.setAllowCustomValue(true);
//            combo.addCustomValueSetListener(e -> {
//                String customValue = e.getDetail();
//                machine t_y = new machine(customValue);
//                try {
//                    t_y.save_machine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                    int id_tma = getId_type_operation(customValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                    realisation.setId_type_operation(id_ma);
//                    setTypeOperation(realisation.getId_machine(), realisation.getId_realisation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                    //UI.getCurrent().getPage().reload();
//                    this.getDataProvider().refreshItem(realisation);
//                } catch (SQLException ex) {
//                    Notification.show("Problème BdD : 7 rea");
//                }
//            });
            return combo;
        }).setHeader("id machine");
        
        this.addComponentColumn(produit -> {
            Button button = new Button("Supprimer", clickEvent -> {
                try {
                    produit.supRealisation((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    UI.getCurrent().getPage().reload();
                    this.setItems(realisation.tousLesRealisation((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : grid realisation : " + ex);
                    // Gérez les erreurs ici
                }
            });
            return button;
        }).setHeader("");
        
        this.getStyle().setBackground("PowderBlue");
        this.setMinHeight("100vh");
    }
}
