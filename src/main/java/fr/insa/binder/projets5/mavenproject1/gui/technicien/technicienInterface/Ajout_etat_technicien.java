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
import fr.insa.binder.projets5.mavenproject1.Operateur__etat;
import fr.insa.binder.projets5.mavenproject1.etat;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import fr.insa.binder.projets5.mavenproject1.type_etat;
import static fr.insa.binder.projets5.mavenproject1.type_etat.getLesTypesEtatsA;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author melan
 */
public class Ajout_etat_technicien extends VerticalLayout {
    private HorizontalLayout HL;
    private Button valid;
    private etat etat;
    private int type_etat;
    private int id_operateur;
    private Operateur__etat op_etat;

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
        
        // Recuperer id_operateur
        id_operateur = (int) VaadinSession.getCurrent().getAttribute("id_operateur");
        
        
        // Ajout du style au bouton
        this.valid.getStyle()
                .set("color", "Crimson")
                .set("background-color", "PowderBlue");
        // Récupérer l'état sélectionné
        String etatSelectionne = this.etatComboBox.getValue();
      
        
        
        try {
            ArrayList<type_etat> a = getLesTypesEtatsA((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            List<String> etats = new ArrayList<>();
            for (int i=0; i <a.size(); i++){
                etats.add(a.get(i).getDes_type_etat());
            }
            etatComboBox.setItems(etats);
        } catch (SQLException ex) {
                Notification.show("Problème BdD : ajout etat : " + ex);
            }
        
        this.valid.addClickListener(event -> {
            String selectedEtat = this.etatComboBox.getValue();
            LocalDateTime debutDateTime = this.debutDateTimePicker.getValue();
            LocalDateTime finDateTime = this.finDateTimePicker.getValue();

            Timestamp debutTimestamp = Timestamp.valueOf(debutDateTime);
            Timestamp finTimestamp = Timestamp.valueOf(finDateTime);
            
            Connection con = (Connection) VaadinSession.getCurrent().getAttribute("conn");
            int id_type_etat=-1;
            try{
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select id_type_etat from type_etat_bof where des_type_etat='"+ selectedEtat+"'");
            res.next();
            id_type_etat = res.getInt("id_type_etat");
            } catch (SQLException ex) {
                Notification.show("Problème BdD : ajout etat : " + ex);
            }
            
            // Faire quelque chose avec l'état et les timestamps, par exemple, mettre à jour la base de données
            System.out.println("État du technicien sélectionné : " + selectedEtat);
            System.out.println("De : " + debutTimestamp + " à : " + finTimestamp);

            
            this.etat = new etat(id_type_etat, debutTimestamp, finTimestamp);
            try {
                this.etat.save_etat((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch (SQLException ex) {
                Notification.show("Problème BdD : ajout etat : " + ex);
            }
            op_etat = new Operateur__etat(id_operateur,etat.getId_etat());
            try {
                op_etat.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                UI.getCurrent().getPage().reload();
            } catch (SQLException ex) {
                Notification.show("Problème BdD : ajout etat : " + ex);
            }
        });
        
        // Ajouter les composants à la mise en page
        this.HL = new HorizontalLayout();
        this.add(new H3("Ajout etat"));
        this.HL.add(this.etatComboBox, this.debutDateTimePicker, this.finDateTimePicker);
        this.add(this.HL,this.valid);
        
        
    }
    private void stylisation() {
        
        valid.getStyle()
                .set("color", "Crimson")
                .set("background-color", "PowderBlue");
        
    }
}
