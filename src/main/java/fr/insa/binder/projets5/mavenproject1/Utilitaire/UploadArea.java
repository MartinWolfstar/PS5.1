/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.Utilitaire;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.ImageT;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class UploadArea extends VerticalLayout {

    private final Upload uploadField;
    private final Span errorField;

    public UploadArea() {
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        uploadField = new Upload(buffer);
        uploadField.setMaxFiles(10);
        uploadField.setMaxFileSize(5 * 1024 * 1024);
        uploadField.setDropLabel(new com.vaadin.flow.component.html.Label("Drop image here"));

        errorField = new Span();
        errorField.setVisible(false);
        errorField.getStyle().set("color", "red");

        uploadField.addSucceededListener(event -> {
            try {
                String filename = event.getFileName();
                InputStream inputStream = buffer.getInputStream(filename);

                // Création d'une instance de la classe ImageT
                ImageT image = new ImageT(filename, inputStream.readAllBytes());

                // Connexion à la base de données (assurez-vous que votre connexion est établie correctement)
                Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");

                // Enregistrement de l'image dans la base de données
                try {
                    image.saveImage(conn);
                    Notification.show("Image enregistrée avec succès dans la base de données.");
                } catch (SQLException e) {
                    Notification.show("Erreur lors de l'enregistrement de l'image dans la base de données." + e);
                }
            } catch (IOException e) {
                Notification.show("problème image : " + e);
            }
        });


        uploadField.addFailedListener(e -> showErrorMessage(e.getReason().getMessage()));
        uploadField.addFileRejectedListener(e -> showErrorMessage(e.getErrorMessage()));

        add(uploadField, errorField);
    }

    public Upload getUploadField() {
        return uploadField;
    }

    public void hideErrorField() {
        errorField.setVisible(false);
    }

    private void showErrorMessage(String message) {
        errorField.setVisible(true);
        errorField.setText(message);
    }
}
