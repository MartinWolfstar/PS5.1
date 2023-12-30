/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.TechnicienCommande;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import fr.insa.binder.projets5.mavenproject1.Operation;
import static fr.insa.binder.projets5.mavenproject1.Precede.liste_to_string;
import static fr.insa.binder.projets5.mavenproject1.Precede.tousLesPrecede_operation;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.operation_effectuee;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author binde
 */
public class Grid_op extends Grid<operation_effectuee> {
    public Grid_op(List<operation_effectuee> list_operation) {
        this.setItems(list_operation);
        this.addColumn(operation_effectuee::getId_operation).setHeader("Id Operation");
        this.addColumn(operation_effectuee::getId_exemplaire).setHeader("Exemplaire");
        this.addColumn(operation_effectuee::getId_machine).setHeader("Machine");
//        this.addColumn(commande::getId_client).setHeader("Id_client");
//        this.getStyle().setBackground("PowderBlue");
//        
//        this.addColumn(Operation -> {
//            String text = "";
//            try {
//                text = liste_to_string(tousLesPrecede_operation(con, Operation.getId_operation()));
//            } catch (SQLException ex) {
//                Notification.show("Problème BdD : m2");
//            }
//            return text;
//        }).setHeader("Operation(s) suivantes");
}}
