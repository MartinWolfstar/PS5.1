/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienOperation;
//
//import com.vaadin.flow.component.Focusable;
//import com.vaadin.flow.component.Key;
//import com.vaadin.flow.component.Shortcuts;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.grid.editor.Editor;
//import com.vaadin.flow.component.html.Label;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.textfield.IntegerField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.server.VaadinSession;
//import fr.insa.binder.projets5.mavenproject1.Operation;
//import static fr.insa.binder.projets5.mavenproject1.Operation.setDes;
//import static fr.insa.binder.projets5.mavenproject1.Operation.setRef;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Optional;
//
///**
// *
// * @author binde
// */
//public class Grid_choix_operation extends Grid<Operation> {
//
//    private Optional<Grid.Column<Operation>> currentColumn = Optional.empty();
//    private Optional<Operation> currentItem = Optional.empty();
//
//    public Grid_choix_operation(int id_produit) {
//
////        Grid grid = new Grid();
////        Grid<Operation> grid = new Grid<>(Operation.class, false);
//        try {
//            this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
//        } catch (SQLException ex) {
//            Notification.show("Problème BdD : m2");
//        }
//        Grid.Column<Operation> id = this.addColumn(Operation::getId_operation).setHeader("Id");
//
//        this.addComponentColumn(Operation -> {
//            return new Label(ffffffffff)
//            ComboBox<String> combo = new ComboBox<>("Operations necessaires", clickEvent -> {
//                try {
//                    Operation.supOperation((Connection) VaadinSession.getCurrent().getAttribute("conn"));
////                    UI.getCurrent().getPage().reload();
//                    this.setItems(Operation.tousLesOperations((Connection) VaadinSession.getCurrent().getAttribute("conn")));
//                } catch (SQLException ex) {
//                    Notification.show("Problème BdD : a");
//                    // Gérez les erreurs ici
//                }
//            });
//            return button;
//        }).setHeader("");
//
//        this.addComponentColumn(Operation -> {
//            Button button = new Button("Supprimer", clickEvent -> {
//                try {
//                    Operation.supOperation((Connection) VaadinSession.getCurrent().getAttribute("conn"));
////                    UI.getCurrent().getPage().reload();
//                    this.setItems(Operation.tousLesOperations((Connection) VaadinSession.getCurrent().getAttribute("conn")));
//                } catch (SQLException ex) {
//                    Notification.show("Problème BdD : a");
//                    // Gérez les erreurs ici
//                }
//            });
//            return button;
//        }).setHeader("");
////        
////        this.addComponentColumn(Operation -> {
////            ComboBox<String> combo = new ComboBox<>("Operations necessaires", clickEvent -> {
////                try {
////                    Operation.supOperation((Connection) VaadinSession.getCurrent().getAttribute("conn"));
//////                    UI.getCurrent().getPage().reload();
////                    this.setItems(Operation.tousLesOperations((Connection) VaadinSession.getCurrent().getAttribute("conn")));
////                } catch (SQLException ex) {
////                    Notification.show("Problème BdD : a");
////                    // Gérez les erreurs ici
////                }
////            });
////            return button;
////        }).setHeader("");
//
//        Binder<Operation> binder = new BeanValidationBinder<>(Operation.class);
//        Editor<Operation> editor = this.getEditor();
//        editor.setBinder(binder);
//        editor.setBuffered(true);
//
//        editor.addSaveListener(event -> {
//            Operation item = event.getItem();
//            try {
//                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
//                setRef(54, 1, con);
//                setDes(item.getDes(), item.getId(), con);
////                setRef(54, 1, con);
//            } catch (SQLException ex) {
//                Notification.show("Problème BdD : m2");
//            }
//
//        });
//
//        TextField lastNameField = new TextField();
//        IntegerField ref_field = new IntegerField();
//        lastNameField.setWidthFull();
//        ref_field.setWidthFull();
//
//        binder.forField(lastNameField)
//                .asRequired("First name must not be empty")
//                .bind(Operation::getDes, Operation::setDes);
//        des.setEditorComponent(lastNameField);
//
//        binder.forField(ref_field)
//                .asRequired("First name must not be empty")
//                .bind(Operation::getRef, Operation::setRef);
//        ref.setEditorComponent(ref_field);
//
//        this.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(Operation -> {
//            editor.save();
//            if (!editor.isOpen()) {
//                this.getEditor().editItem(Operation);
//                currentColumn.ifPresent(column -> {
//                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
//                        focusable.focus();
//                    }
//                });
//            }
//        }));
//
//        Shortcuts.addShortcutListener(this, () -> {
//            if (editor.isOpen()) {
//                editor.save();
//                currentColumn.ifPresent(column -> {
//                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
//                        focusable.blur();
//                    }
//                });
//            }
//        }, Key.ENTER).listenOn(this);
//
//        this.addCellFocusListener(event -> {
//            // Store the item on cell focus. Used in the ENTER ShortcutListener
//            currentItem = event.getItem();
//            // Store the current column. Used in the SelectionListener to focus the editor component
//            currentColumn = event.getColumn();
//        });
//    }
//
//}
