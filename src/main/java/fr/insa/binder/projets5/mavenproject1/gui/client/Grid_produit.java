/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
 
/**
 *
 * @author binde
 */
public class Grid_produit extends Grid<produit> {

    private List<Integer> selectedIds; // Liste pour stocker les identifiants des objets sélectionnés

    public Grid_produit(List<produit> list_produit) {
        this.setItems(list_produit);
        this.setSelectionMode(Grid.SelectionMode.MULTI);

        selectedIds = new ArrayList<>();

        this.addColumn(produit::getRef).setHeader("Nom");
        this.addColumn(produit::getDes).setHeader("Description");

        this.addSelectionListener(selection -> {
            Set<produit> selectedItems = selection.getAllSelectedItems();
            selectedIds.clear(); // Effacer la liste existante

            for (produit produit : selectedItems) {
                selectedIds.add(produit.getId()); // Ajouter l'identifiant de chaque objet sélectionné à la liste
            }

            Notification.show("Number of selected people: " + selectedItems.size());
        });
    }

    public List<Integer> getSelectedIds() {
        return selectedIds;
    }
}

