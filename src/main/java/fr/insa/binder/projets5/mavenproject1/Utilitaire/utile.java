/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.Utilitaire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface.ParametreTechnicien;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienPlanUsine.technicien_PlanUsine;

/**
 *
 * @author schmi
 */
public class utile {
    
    public static void stylisation(VerticalLayout V) {
        
        V.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "1200vh");
    } 
    
    public static void stylisation(ParametreTechnicien V,TextField a1,TextField a2,TextField a3,PasswordField a4, Button b1) {
        
        V.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "1200vh");
        a1.getStyle()
                .set("color", "Crimson");
        a2.getStyle()
                .set("color", "Crimson");
        a3.getStyle()
                .set("color", "Crimson");
        a4.getStyle()
                .set("color", "Crimson");
        b1.getStyle()
                .set("color", "Crimson")
                .set("background-color", "PowderBlue");
        
    }
    public static void stylisation(technicien_PlanUsine aThis, Button ajoutB, Button modifB, Button suppB) {
        
        aThis.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "1200vh");
        ajoutB.getStyle()
            .set("color", "Crimson")
            .set("background-color", "PowderBlue");
        modifB.getStyle()
            .set("color", "Crimson")
            .set("background-color", "PowderBlue");
        suppB.getStyle()
            .set("color", "Crimson")
            .set("background-color", "PowderBlue");
        
    }
    
}
