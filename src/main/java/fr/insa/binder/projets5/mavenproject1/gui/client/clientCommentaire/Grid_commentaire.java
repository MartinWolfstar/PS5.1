/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client.clientCommentaire;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.Client;
import fr.insa.binder.projets5.mavenproject1.commantaire;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

 
public class Grid_commentaire extends Grid<commantaire> {

    private List<Integer> selectedIds;
    private Optional<Grid.Column<commantaire>> currentColumn = Optional.empty();
    private Optional<commantaire> currentItem = Optional.empty();

    public Grid_commentaire(List<commantaire> list_commentaires) {
        Grid.Column<commantaire> nom = addColumn(commantaire -> {
            int idClient = commantaire.getId_client();
            try {
                Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                return Client.getnom_client(idClient, con);
            } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex);
                return "Erreur bdd";
            }
        }).setHeader("Nom du Client");
        
        Grid.Column<commantaire> mes =addColumn(commantaire::getMessage).setHeader("Message");
        nom.setWidth("50px");
        mes.setWidth("900px");

        //addColumn(this::createMessageEditor).setHeader("Message");

//        selectedIds = new ArrayList<>();
//        setSelectionMode(SelectionMode.MULTI);
        setItems(list_commentaires);

//        addSelectionListener(event -> {
//            Set<commantaire> selectedItems = event.getAllSelectedItems();
//            selectedIds = selectedItems.stream().map(commantaire::getId_commentaire).collect(Collectors.toList());
//            currentItem = selectedItems.stream().findFirst();
//        });
        this.setMaxHeight("50vh");

    }
//    private HorizontalLayout createMessageEditor(commantaire commentaire) {
//        HorizontalLayout layout = new HorizontalLayout();
//
//        // Affiche le message actuel
//        TextField messageField = new TextField();
//        messageField.setValue(commentaire.getMessage());
//        messageField.setReadOnly(true);
//
//        // Ajoute un bouton pour activer l'édition
//        Button editButton = new Button("Modifier");
//        editButton.addClickListener(event -> {
//            messageField.setReadOnly(false);
//            messageField.focus();
//        });
//
//        // Ajoute un bouton pour enregistrer les modifications
//        Button saveButton = new Button("Enregistrer");
//        saveButton.addClickListener(event -> {
//            try {
//                // Enregistre les modifications dans la base de données
//                commentaire.setMessage(messageField.getValue());
//                commentaire.updateMessageInDB((Connection) VaadinSession.getCurrent().getAttribute("conn"));
//
//                // Rafraîchit la grille
//                getDataProvider().refreshItem(commentaire);
//
//                // Désactive l'édition
//                messageField.setReadOnly(true);
//            } catch (SQLException ex) {
//                Notification.show("Problème BdD : " + ex.getMessage());
//            }
//        });
//
//        layout.add(messageField, editButton, saveButton);
//        return layout;
//    }
}


