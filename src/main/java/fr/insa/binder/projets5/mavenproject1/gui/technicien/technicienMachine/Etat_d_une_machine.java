/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.machine;
import static fr.insa.binder.projets5.mavenproject1.machine.tousLesMachines;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.Grid_etat_d_une_machine.get_etat_d_une_machine;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Etat_d_une_machine extends VerticalLayout {
    private Grid_etat_d_une_machine grid;
    private ComboBox id_machine_select;
    private int id_int;
    private Button soumettre;
    
    public Etat_d_une_machine(){
        this.id_machine_select = new ComboBox<>("Id machine select");
        this.grid = new Grid_etat_d_une_machine();
        this.soumettre = new Button("soumettre");
        try {
            List<machine> listMachines = tousLesMachines((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            List<String> ids = new ArrayList<>();
            for (int i = 0; i < listMachines.size(); i++) {
                ids.add(Integer.toString(listMachines.get(i).getId()));
            }
            id_machine_select.setItems(ids);
        } catch (SQLException ex) {
            Notification.show("Problème BdD : ajout etat : " + ex);
        }
        this.soumettre.addClickListener(event -> {
            
            System.out.println(id_machine_select.getValue());
            id_machine_select.getValue().getClass();
            System.out.println(id_machine_select.getValue().getClass());
            try {
                //id_int = Integer.parseInt(id_machine_select.getValue());
                this.grid.setEtat(get_etat_d_une_machine((Connection) VaadinSession.getCurrent().getAttribute("conn"),Integer.valueOf((String) id_machine_select.getValue())));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : ajout etat : " + ex);
            }
            
        });
        this.add(id_machine_select,soumettre,grid);
    }
}
