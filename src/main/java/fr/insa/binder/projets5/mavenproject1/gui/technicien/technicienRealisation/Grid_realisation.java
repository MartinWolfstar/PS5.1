/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienRealisation;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import static fr.insa.binder.projets5.mavenproject1.Operation.setTypeOperation;
import fr.insa.binder.projets5.mavenproject1.realisation;
import static fr.insa.binder.projets5.mavenproject1.realisation.setTypeMachine;
import fr.insa.binder.projets5.mavenproject1.type_machine;
import static fr.insa.binder.projets5.mavenproject1.type_machine.getId_type_machine;
import static fr.insa.binder.projets5.mavenproject1.type_machine.tousLesTypeMachines_String;
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

        this.addColumn(realisation::getId_realisation).setHeader("getId_realisation");
        Grid.Column<realisation> id = this.addColumn(realisation::getId_type_operation).setHeader("Id");

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
                    setTypeOperation(realisation.getId_type_operation(), realisation.getId_realisation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(realisation);
//                    this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
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
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(realisation);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : 4 rea");
                }

            });
            return combo;
        }).setHeader("Type operation");
        
        Grid.Column<realisation> id_machine = this.addColumn(realisation::getId_machine).setHeader("Id_machine");

        this.addComponentColumn(realisation -> {
            ComboBox<String> combo = new ComboBox<>();
            try {
                combo.setItems(tousLesTypeMachines_String((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                combo.setValue(type_machine.getDes_type_machine(realisation.getId_machine(), (Connection) VaadinSession.getCurrent().getAttribute("conn")));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : 5 rea");
            }

            combo.addValueChangeListener(event -> {
                String selectedValue = event.getValue();
                try {
                    int id_type_ma = getId_type_machine(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    realisation.setId_machine(id_type_ma);
                    setTypeMachine(realisation.getId_machine(), realisation.getId_realisation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(realisation);
//                    this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : 6 rea");
                }
            });

            combo.setAllowCustomValue(true);
            combo.addCustomValueSetListener(e -> {
                String customValue = e.getDetail();
                type_machine t_y = new type_machine(customValue);
                try {
                    t_y.save_type_machine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    int id_type_ma = getId_type_operation(customValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    realisation.setId_type_operation(id_type_ma);
                    setTypeOperation(realisation.getId_machine(), realisation.getId_realisation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(realisation);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : 7 rea");
                }

            });
            return combo;
        }).setHeader("Type operation");
    }
}
