/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.etat;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;



/**
 *
 * @author melan
 */
public class Ajout_etat_technicien extends VerticalLayout {
    private HorizontalLayout HL;
    private Button valid;
    private etat etat;
    private int type_etat;

    private ComboBox<String> etatComboBox;
    private DateTimePicker debutDateTimePicker;
    private DateTimePicker finDateTimePicker;

    public Ajout_etat_technicien() {
        // Initialisation des composants
        this.etatComboBox = new ComboBox<>("État du technicien");
        this.etatComboBox.setItems("Present", "Absent");

        this.debutDateTimePicker = new DateTimePicker("Date et heure de début");
        this.debutDateTimePicker.setRequiredIndicatorVisible(true);
        this.debutDateTimePicker.setStep(Duration.ofMinutes(15));
        this.debutDateTimePicker.setValue(LocalDateTime.now().withSecond(0).withNano(0));

        this.finDateTimePicker = new DateTimePicker("Date et heure de fin");
        this.finDateTimePicker.setRequiredIndicatorVisible(true);
        this.finDateTimePicker.setStep(Duration.ofMinutes(15));
        this.finDateTimePicker.setValue(LocalDateTime.now().withSecond(0).withNano(0));

        this.valid = new Button("Soumettre");

        // Ajout du style au bouton
        this.valid.getStyle()
                .set("color", "Crimson")
                .set("background-color", "PowderBlue");
        // Récupérer l'état sélectionné
        String etatSelectionne = this.etatComboBox.getValue();
        
        // Comparer l'état sélectionné avec "Présent"
        if ("Present".equals(etatSelectionne)) {
            // Si l'état est "Présent", définir type_etat à 1
            this.type_etat = 2;
        } else {
            // Si l'état est différent de "Présent", définir type_etat à une autre valeur si nécessaire
            this.type_etat = 1; // par exemple, 0 pour "Absent"
        }
        
        this.valid.addClickListener(event -> {
            String selectedEtat = this.etatComboBox.getValue();
            LocalDateTime debutDateTime = this.debutDateTimePicker.getValue();
            LocalDateTime finDateTime = this.finDateTimePicker.getValue();

            Timestamp debutTimestamp = Timestamp.valueOf(debutDateTime);
            Timestamp finTimestamp = Timestamp.valueOf(finDateTime);

            // Faire quelque chose avec l'état et les timestamps, par exemple, mettre à jour la base de données
            System.out.println("État du technicien sélectionné : " + selectedEtat);
            System.out.println("De : " + debutTimestamp + " à : " + finTimestamp);

            
            this.etat = new etat(this.type_etat, debutTimestamp, finTimestamp);
            try {
                this.etat.save_etat((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch (SQLException ex) {
                Notification.show("Problème BdD : ajout etat : " + ex);
            }
        });
        
        // Ajouter les composants à la mise en page
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout etat"));
        this.HL.add(this.etatComboBox, this.debutDateTimePicker, this.finDateTimePicker, this.valid);
        this.add(this.HL);
        
        
    }
    private void stylisation() {
        
        valid.getStyle()
                .set("color", "Crimson")
                .set("background-color", "PowderBlue");
        
    }
}
