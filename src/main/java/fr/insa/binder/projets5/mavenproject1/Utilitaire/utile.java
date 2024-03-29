/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.Utilitaire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.ImageT;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface.ParametreTechnicien;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienPlanUsine.technicien_PlanUsine;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author schmi
 */
public class utile {
    
    public static void stylisation(VerticalLayout V) {
        
//        V.getStyle()
//            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
//            .set("background-size", "cover")
//            .set("height", "1200vh");

        String imageName = "fdecran.jpg";
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            ImageT image = ImageT.getImageByName(conn, imageName);

            if (image != null) {
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());
                V.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover")
                    .setOpacity("50")
                    .set("height", "200vh");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
    } 
        public static void stylisation(HorizontalLayout V) {
        
//        V.getStyle()
//            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
//            .set("background-size", "cover")
//            .set("height", "1200vh");

        String imageName = "fdecran.jpg";
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            ImageT image = ImageT.getImageByName(conn, imageName);

            if (image != null) {
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());
                V.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover")
                    .setOpacity("50")
                    .set("height", "200vh");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
    } 
    public static void stylisation2(HorizontalLayout V) {
        
//        V.getStyle()
//            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
//            .set("background-size", "cover")
//            .set("height", "1200vh");

        String imageName = "MarcoL.jpg";
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            ImageT image = ImageT.getImageByName(conn, imageName);

            if (image != null) {
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());
                V.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover");
                    //.setOpacity("50")
                    //.set("height", "200vh");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
    } 
    
    public static void stylisation(ParametreTechnicien V,TextField a1,TextField a3,PasswordField a4, Button b1) {
        
        String imageName = "fdecran.jpg";
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");

        try {
            ImageT image = ImageT.getImageByName(conn, imageName);
            if (image != null) {
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());
                V.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover")
                    .set("height", "400vh");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }      
    }
    public static void stylisation(technicien_PlanUsine aThis, Button ajoutB, Button modifB, Button suppB) {
        
        String imageName = "fdecran.jpg";
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
        try {
            ImageT image = ImageT.getImageByName(conn, imageName);

            if (image != null) {
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());

                aThis.getStyle()
                    .set("background", "url(data:image/jpeg;base64," + base64Image + ") no-repeat center center fixed")
                    .set("background-size", "cover")
                    .set("height", "200vh");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
    }
    
}
