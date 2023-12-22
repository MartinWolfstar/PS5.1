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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.client.Grid_produit;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import static fr.insa.binder.projets5.mavenproject1.produit.setRef;
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
        try {
            grid.setItems(produit.tousLesProduits((Connection) VaadinSession.getCurrent().getAttribute("conn")));
        } catch (SQLException ex) {
            this.add(new H3("Problème BdD : "));
        }
        Grid.Column<produit> id = grid.addColumn(produit::getId).setHeader("Id");
        Grid.Column<produit> ref = grid.addColumn(produit::getRef).setHeader("Ref");
        Grid.Column<produit> des = grid.addColumn(produit::getDes).setHeader("Des");

        Binder<produit> binder = new BeanValidationBinder<>(produit.class);
        Editor<produit> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        editor.addSaveListener(event -> {
            produit item = event.getItem();
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                setRef(54, 1, con);
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
                .bind(produit::getDes, produit::setDes);
        des.setEditorComponent(lastNameField);

        binder.forField(ref_field)
                .asRequired("First name must not be empty")
                .bind(produit::getRef, produit::setRef);
        ref.setEditorComponent(ref_field);

        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(produit -> {
            editor.save();
            if (!editor.isOpen()) {
                grid.getEditor().editItem(produit);
                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.focus();
                    }
                });
            }
        }));

        Shortcuts.addShortcutListener(grid, () -> {
            if (editor.isOpen()) {
                editor.save();
                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.blur();
                    }
                });
            }
        }, Key.ENTER).listenOn(grid);

        grid.addCellFocusListener(event -> {
            // Store the item on cell focus. Used in the ENTER ShortcutListener
            currentItem = event.getItem();
            // Store the current column. Used in the SelectionListener to focus the editor component
            currentColumn = event.getColumn();
        });
        this.add(grid);
    }

    public void CreerGrid() {

    }
}
