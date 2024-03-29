/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientProduit;

import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

 
/**
 *
 * @author binde
 */
public class Grid_produit extends Grid<produit> {

    private List<Integer> selectedIds;
    private Optional<Grid.Column<produit>> currentColumn = Optional.empty();
    private Optional<produit> currentItem = Optional.empty();

    public Grid_produit(List<produit> list_produit) {
        this.setItems(list_produit);
        this.setSelectionMode(Grid.SelectionMode.MULTI);

        selectedIds = new ArrayList<>();

        //this.addComponentColumn(i -> i.getImage()).setHeader("Preview");
        Grid.Column<produit> nom =this.addColumn(produit::getRef).setHeader("Nom");
        Grid.Column<produit> des =this.addColumn(produit::getDes).setHeader("Description");
        nom.setWidth("50px");
        des.setWidth("1000px");
        
        
//        Grid.Column<produit> quantite = this.addColumn(produit -> {
//            int text = 1;
//            return text;
//        }).setHeader("quantité");
//        
//        Binder<produit> binder = new BeanValidationBinder<>(produit.class);
//        Editor<produit> editor = this.getEditor();
//        editor.setBinder(binder);
//        editor.setBuffered(true);
//
//        editor.addSaveListener(event -> {
//            //Notification.show("Number : " + event);
//        });
//        
//        IntegerField quant_field = new IntegerField();
//        quant_field.setWidthFull();
//        
////        binder.forField(quant_field)
////                .asRequired("First name must not be empty")
////                .bind(produit::getRef, produit::setRef);
//        quantite.setEditorComponent(quant_field);
//        
//        this.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(produit -> {
//            editor.save();
//            if (!editor.isOpen()) {
//                this.getEditor().editItem(produit);
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

        this.addCellFocusListener(event -> {
            currentItem = event.getItem();
            currentColumn = event.getColumn();
        });
        
        this.addSelectionListener(selection -> {
            Set<produit> selectedItems = selection.getAllSelectedItems();
            selectedIds.clear(); 

            for (produit produit : selectedItems) {
                selectedIds.add(produit.getId()); 
            }
            //Notification.show("Number of selected people: " + selectedItems.size());
        });
        this.setMaxHeight("100vh");
    }

    public List<Integer> getSelectedIds() {
        return selectedIds;
    }
}

