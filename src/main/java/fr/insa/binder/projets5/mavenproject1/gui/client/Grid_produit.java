/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.util.List;
 
/**
 *
 * @author binde
 */
public class Grid_produit extends Grid<produit>{
    
    public Grid_produit(List<produit> list_produit) {
        this.setItems(list_produit);
        this.setSelectionMode(Grid.SelectionMode.MULTI);
        this.addColumn(produit::getRef).setHeader("Nom");
        this.addColumn(produit::getDes).setHeader("Description");
        
        this.addSelectionListener(selection -> {
            Notification.show("Number of selected people:" + selection.getAllSelectedItems().size());
            //select = selection.getAllSelectedItems();
        });
    }
    
//    public String getSelect() {
//        return selection.getAllSelectedItems();
//    }
}
