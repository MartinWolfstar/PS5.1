/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.client.Grid_produit;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.produit;
import static fr.insa.binder.projets5.mavenproject1.produit.setDes;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author binde
 */

@PageTitle("Niouf")
@Route(value = "225")
public class AfficherProduit extends VerticalLayout{
    
    private Grid grid;
    private Optional<Grid.Column<produit>> currentColumn = Optional.empty();
    private Optional<produit> currentItem = Optional.empty();
    
    public AfficherProduit(){
        
        Grid<produit> grid = new Grid<>(produit.class, false);
        try{
            grid.setItems(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn")));
        }catch(SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        Grid.Column<produit> firstNameColumn = grid.addColumn(produit::getRef).setHeader("Ref").setWidth("120px").setFlexGrow(0);
        Grid.Column<produit> lastNameColumn = grid.addColumn(produit::getDes).setHeader("Last name").setWidth("120px").setFlexGrow(0);

        Binder<produit> binder = new BeanValidationBinder<>(produit.class);
        Editor<produit> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        editor.addSaveListener(event -> {
        produit item = event.getItem();
        try {
            Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        //                produit.setDes(this.des.getValue(), id, con);
                        setDes(item.getDes(), item.getId(), con);
                    } catch(SQLException ex) {
                    Notification.show("Problème BdD : m2");
               }
        
        });
        
        TextField lastNameField = new TextField();
        lastNameField.setWidthFull();
        //addCloseHandler(firstNameField, editor);
        binder.forField(lastNameField)
            .asRequired("First name must not be empty")
            .bind(produit::getDes, produit::setDes);
        lastNameColumn.setEditorComponent(lastNameField);
        
        
        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(produit -> {
    editor.save();
    if (!editor.isOpen()) {
        grid.getEditor().editItem(produit);
        currentColumn.ifPresent(column -> {
        if (column.getEditorComponent() instanceof Focusable<?> focusable) {
            focusable.focus();
        }
    });
}}));

//        lastNameField.addKeyPressListener(Key.ENTER, event -> {
//            // Enregistrer les modifications et fermer l'éditeur de grille
//            editor.save();
//        });


        Shortcuts.addShortcutListener(grid, () -> {
            if (editor.isOpen()) {
                editor.save();
                currentColumn.ifPresent(column -> {
        if (column.getEditorComponent() instanceof Focusable<?> focusable) {
            focusable.blur();
        }
    });            }
        }, Key.ENTER).listenOn(grid);


    grid.addCellFocusListener(event -> {
        // Store the item on cell focus. Used in the ENTER ShortcutListener
        currentItem = event.getItem();
        // Store the current column. Used in the SelectionListener to focus the editor component
        currentColumn = event.getColumn();
    });
        
//TextField lastNameField = new TextField();
//lastNameField.setWidthFull();
//addCloseHandler(lastNameField, editor);
//binder.forField(lastNameField).asRequired("Last name must not be empty")
//        .withStatusLabel(lastNameValidationMessage)
//        .bind(Person::getLastName, Person::setLastName);
//lastNameColumn.setEditorComponent(lastNameField);
//
//EmailField emailField = new EmailField();
//emailField.setWidthFull();
//addCloseHandler(emailField, editor);
//binder.forField(emailField).asRequired("Email must not be empty")
//        .withValidator(
//                new EmailValidator("Enter a valid email address"))
//        .withStatusLabel(emailValidationMessage)
//        .bind(Person::getEmail, Person::setEmail);
//emailColumn.setEditorComponent(emailField);
//
//        grid.addItemDoubleClickListener(e -> {
//            editor.editItem(e.getItem());
//            Component editorComponent = e.getColumn().getEditorComponent();
//            if (editorComponent instanceof Focusable) {
//                ((Focusable) editorComponent).focus();
//            }
//        });
//

        
//        Button saveButton = new Button("Enregistrer", event -> {
//            if (editor.isOpen() && binder.writeBeanIfValid(editor.getItem())) {
//               // La ligne suivante déclenche la sauvegarde dans la base de données
//              try {
//                       Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
//        //                produit.setDes(this.des.getValue(), id, con);
//                        setDes(editor.getItem().getDes(), editor.getItem().getId(), con);
//                    } catch(SQLException ex) {
//                    Notification.show("Problème BdD : m2");
//               }
//            }
//        });



        this.add(grid);
    }
}
