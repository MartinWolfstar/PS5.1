/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Operation;
import static fr.insa.binder.projets5.mavenproject1.Operation.getOperation;
import static fr.insa.binder.projets5.mavenproject1.Operation.setTypeOperation;
import static fr.insa.binder.projets5.mavenproject1.Operation.tousLesOperations_produit;
import static fr.insa.binder.projets5.mavenproject1.Operation.tousLesOperations_produit_int;
import fr.insa.binder.projets5.mavenproject1.Precede;
import fr.insa.binder.projets5.mavenproject1.commande;
import fr.insa.binder.projets5.mavenproject1.produit;
import fr.insa.binder.projets5.mavenproject1.type_operation;
import static fr.insa.binder.projets5.mavenproject1.type_operation.getId_type_operation;
import static fr.insa.binder.projets5.mavenproject1.type_operation.tousLesTypeOperations_String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Salut
 *
 * @author binde
 */
public class Ordre_operation extends HorizontalLayout {

    private ComboBox combo;
    private Button bouton;
    private TextField tf;
    private int op;

    public Ordre_operation(Grid_choix_operation grid) {
        this.tf = new TextField("Salut");
        this.bouton = new Button("Ajoutez des operations qui suivent");
        Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        produit produit = (produit) VaadinSession.getCurrent().getAttribute("produit");
        this.combo = new ComboBox<Integer>();
        List<Integer> liste = new ArrayList<>();
        try {
            liste = tousLesOperations_produit_int(con, produit.getId());
        } catch (SQLException ex) {
            Notification.show("Problème BdD : m2");
        }
        combo.setItems(liste);

        combo.addValueChangeListener(event -> {
            op = (int) event.getValue();

//            try {
//                int id_type_op = getId_type_operation(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                Operation.setId_typeOperation(id_type_op);
//                setTypeOperation(Operation.getId_typeOperation(), Operation.getId_operation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                //UI.getCurrent().getPage().reload();
//                this.getDataProvider().refreshItem(Operation);
////                    this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
//            } catch (SQLException ex) {
//                Notification.show("Problème BdD : m2");
//            }
        });

        this.bouton.addClickListener(e -> {
            Notification.show("Number of selected people: " );
//            Integer idClient = (Integer) VaadinSession.getCurrent().getAttribute("id_client");
            for (Integer IdOperation_2 : grid.getSelectedIds()) {
                
                Precede precede = new Precede(op, IdOperation_2);
                try {
                    precede.saveInDBV1(con);
//                    String produitSelectionne = produit.giveProduit(con, produitId);
//
//                    // Vérifiez si le produit sélectionné existe avant de créer la commande
//                    if (produitSelectionne != null) {
//                        commande nouvelleCommande = new commande("nouvelle commande", produitSelectionne, idClient);
//                        nouvelleCommande.saveInDBV1(con);
//                    } else {
//                        // Gérez le cas où le produit sélectionné n'existe pas
//                        Notification.show("Le produit avec l'ID " + produitId + " n'existe pas.");
//                    }
//                } catch (SQLException ex) {
//                    Notification.show("Problème lors de la création de la commande : " + ex.getLocalizedMessage());
//                }
//            }
//            UI.getCurrent().getPage().reload();
//            Notification.show("Vous avez acheté les produits :" + grid.getSelectedIds());
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : m2");
                }
            }
        
    });
        this.add(this.combo, this.bouton);
}}
