/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.machine;
import static fr.insa.binder.projets5.mavenproject1.machine.setDes;
import static fr.insa.binder.projets5.mavenproject1.machine.setPosteDeTravail;
import static fr.insa.binder.projets5.mavenproject1.machine.setRef;
import static fr.insa.binder.projets5.mavenproject1.machine.setTypeMachine;
import fr.insa.binder.projets5.mavenproject1.poste_de_travail;
import static fr.insa.binder.projets5.mavenproject1.poste_de_travail.getId_poste_de_travail;
import static fr.insa.binder.projets5.mavenproject1.poste_de_travail.tousLesPostes_String;
import fr.insa.binder.projets5.mavenproject1.type_machine;
import static fr.insa.binder.projets5.mavenproject1.type_machine.getId_type_machine;
import static fr.insa.binder.projets5.mavenproject1.type_machine.tousLesTypeMachines_String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Grid_machine extends Grid<machine>{
    
    private Optional<Grid.Column<machine>> currentColumn = Optional.empty();
    private Optional<machine> currentItem = Optional.empty();
    
    public Grid_machine(List<machine> list_machine) {
        this.setItems(list_machine);
        Grid.Column<machine> id = this.addColumn(machine::getId).setHeader("Identifiant");
        Grid.Column<machine> ref = this.addColumn(machine::getRef).setHeader("Reference");
        Grid.Column<machine> des = this.addColumn(machine::getDes).setHeader("Description");

        Binder<machine> binder = new BeanValidationBinder<>(machine.class);
        Editor<machine> editor = this.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        editor.addSaveListener(event -> {
            machine item = event.getItem();
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                setRef(item.getRef(), item.getId(), con);
                setDes(item.getDes(), item.getId(), con);
//                setRef(54, 1, con);
            } catch (SQLException ex) {
                Notification.show("Problème BdD : m2");
            }

        });
        
        TextField lastNameField = new TextField();
        IntegerField ref_field = new IntegerField();
        lastNameField.setWidthFull();
        ref_field.setWidthFull();

        binder.forField(lastNameField)
                .asRequired("First name must not be empty")
                .bind(machine::getDes, machine::setDes);
        des.setEditorComponent(lastNameField);

        binder.forField(ref_field)
                .asRequired("First name must not be empty")
                .bind(machine::getRef, machine::setRef);
        ref.setEditorComponent(ref_field);
        
        this.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(produit -> {
            editor.save();
            if (!editor.isOpen()) {
                this.getEditor().editItem(produit);
                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.focus();
                    }
                });
            }
        }));

        Shortcuts.addShortcutListener(this, () -> {
            if (editor.isOpen()) {
                editor.save();
                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.blur();
                    }
                });
            }
        }, Key.ENTER).listenOn(this);

        this.addCellFocusListener(event -> {
            // Store the item on cell focus. Used in the ENTER ShortcutListener
            currentItem = event.getItem();
            // Store the current column. Used in the SelectionListener to focus the editor component
            currentColumn = event.getColumn();
        });
        
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
                //Notification.show("t " + machine.getId_poste_de_travail()); 
                setPosteDeTravail(id_pt, machine.getId(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                //Notification.show("t : " + machine); 
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
        
        //id_type_machine
        this.addComponentColumn(machine -> {
            ComboBox<String> combo = new ComboBox<>();
            try {
                combo.setItems(tousLesTypeMachines_String((Connection) VaadinSession.getCurrent().getAttribute("conn")));
                combo.setValue(type_machine.getDes_type_machine(machine.getId_type_machine(), (Connection) VaadinSession.getCurrent().getAttribute("conn")));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : 2 gridma");
            }

            combo.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            try {
                int id_tm = getId_type_machine(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                machine.setId_type_machine(id_tm);
                //Notification.show("t " + machine.getId_poste_de_travail()); 
                setTypeMachine(id_tm, machine.getId(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                //Notification.show("t : " + machine); 
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
                type_machine tm = new type_machine(customValue);
                try {
                    tm.save_type_machine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    Notification.show("pt : " + tm);
                    int id_tm = getId_type_machine(customValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    machine.setId_type_machine(id_tm);
                    setTypeMachine(machine.getId_type_machine(), machine.getId(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    //UI.getCurrent().getPage().reload();
                    this.getDataProvider().refreshItem(machine);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : grid machine tm" + ex);
                }
            });
            return combo;
        }).setHeader("type machine");
        
        this.addComponentColumn(machine -> {
            Button button = new Button("Supprimer", clickEvent -> {
                try {
                    machine.supMachine((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    UI.getCurrent().getPage().reload();
                    //this.setItems(machine.tousLesMachines((Connection) VaadinSession.getCurrent().getAttribute("conn")));
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
