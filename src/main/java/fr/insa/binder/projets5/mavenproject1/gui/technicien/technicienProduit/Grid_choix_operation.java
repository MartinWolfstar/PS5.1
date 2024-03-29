/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Operation;
import static fr.insa.binder.projets5.mavenproject1.Operation.setTypeOperation;
import static fr.insa.binder.projets5.mavenproject1.Precede.liste_to_string;
import static fr.insa.binder.projets5.mavenproject1.Precede.supPrecede;
import static fr.insa.binder.projets5.mavenproject1.Precede.supPrecede1;
import static fr.insa.binder.projets5.mavenproject1.Precede.tousLesPrecede_operation;
import fr.insa.binder.projets5.mavenproject1.type_operation;
import static fr.insa.binder.projets5.mavenproject1.type_operation.getId_type_operation;
import static fr.insa.binder.projets5.mavenproject1.type_operation.tousLesTypeOperations_String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author binde
 */
public class Grid_choix_operation extends Grid<Operation> {

    private Optional<Grid.Column<Operation>> currentColumn = Optional.empty();
    private Optional<Operation> currentItem = Optional.empty();
    private List<Integer> selectedIds;
    private int id_produit;

    public Grid_choix_operation(int id_produit) {
        this.id_produit = id_produit;
        Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
        } catch (SQLException ex) {
            Notification.show(String.valueOf(id_produit));
        }

        Grid.Column<Operation> id = this.addColumn(Operation::getId_operation).setHeader("Id");

        this.addComponentColumn(Operation -> {
            ComboBox<String> combo = new ComboBox<>();
            try {
                combo.setItems(tousLesTypeOperations_String((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                combo.setValue(type_operation.getDes_type_operation(Operation.getId_typeOperation(), (Connection) VaadinSession.getCurrent().getAttribute("conn")));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : a");
            }

            combo.addValueChangeListener(event -> {
                String selectedValue = event.getValue();
                try {
                    int id_type_op = getId_type_operation(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    Operation.setId_typeOperation(id_type_op);
                    setTypeOperation(Operation.getId_typeOperation(), Operation.getId_operation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(Operation);
//                    this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : m2");
                }
            });

            combo.setAllowCustomValue(true);
            combo.addCustomValueSetListener(e -> {
                String customValue = e.getDetail();
                type_operation t_y = new type_operation(customValue);
                try {
                    t_y.save_type_operation((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    int id_type_op = getId_type_operation(customValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    Operation.setId_typeOperation(id_type_op);
                    setTypeOperation(Operation.getId_typeOperation(), Operation.getId_operation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(Operation);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : m2");
                }

            });
            return combo;
        }).setHeader("Type opération");

        this.addColumn(Operation -> {
            String text = "";
            try {
                text = liste_to_string(tousLesPrecede_operation(con, Operation.getId_operation()));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : m2");
            }
            return text;
        }).setHeader("Opération(s) suivantes");

        this.addComponentColumn(Operation -> {
            Button button = new Button("Supprimer", clickEvent -> {
                try {
                    supPrecede(con, Operation.getId_operation());
                    supPrecede1(con, Operation.getId_operation());

//                    UI.getCurrent().getPage().reload();
//                    this.getDataProvider().refreshItem(Operation);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : a" + ex.getLocalizedMessage());
                    // Gérez les erreurs ici
                }
                try {
                    Operation.supOperation(con);
                    this.refresh();
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : a" + ex.getLocalizedMessage());
                }
            });
            return button;
        }).setHeader("");

        this.setSelectionMode(Grid.SelectionMode.MULTI);

        this.selectedIds = new ArrayList<>();

        this.addSelectionListener(selection -> {
            Set<Operation> selectedItems = selection.getAllSelectedItems();
            this.selectedIds.clear(); // Effacer la liste existante
            Notification.show("Number of selected people: ");
            for (Operation op : selectedItems) {
                selectedIds.add(op.getId_operation()); // Ajouter l'identifiant de chaque objet sélectionné à la liste
            }

            Notification.show("Number of selected people: " + selectedItems.size());
        });
    }

    public List<Integer> getSelectedIds() {
        System.out.print(selectedIds);
        return selectedIds;
    }

    public void refresh() {
        try {
            this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
        } catch (SQLException ex) {
            Notification.show(String.valueOf(id_produit));
        }
    }
}
