/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinSession;
import static fr.insa.binder.projets5.mavenproject1.Operation.tousLesOperations_produit_int;
import fr.insa.binder.projets5.mavenproject1.Precede;
import fr.insa.binder.projets5.mavenproject1.produit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ordre_operation extends HorizontalLayout {

    private ComboBox combo;
    private Button bouton;

    private int op;

    public Ordre_operation(Grid_choix_operation grid) {
        this.bouton = new Button("Selectionnez les operations suivantes. Cliquez pour valider votre choix");
        Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        produit produit = (produit) VaadinSession.getCurrent().getAttribute("produit");
        this.combo = new ComboBox<Integer>("Selectionnez l'ID de l'opération");
        List<Integer> liste = new ArrayList<>();
        try {
            liste = tousLesOperations_produit_int(con, produit.getId());
        } catch (SQLException ex) {
            Notification.show("Problème BdD : m2");
        }
        combo.setItems(liste);

        combo.addValueChangeListener(event -> {
            op = (int) event.getValue();
        });

        this.bouton.addClickListener(e -> {
              List<Integer> selected_id = grid.getSelectedIds();
            for (Integer IdOperation_2 : selected_id) {
                Notification.show("Number of selected people: " + selected_id);
                Precede precede = new Precede(op, IdOperation_2);
                try {
                    precede.saveInDBV1(con);
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : m2");
                }
                
            }
        grid.refresh();
    });
        this.add(this.combo, this.bouton);
}}
