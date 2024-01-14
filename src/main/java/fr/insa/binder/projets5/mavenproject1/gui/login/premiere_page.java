package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.ImageT;
import fr.insa.binder.projets5.mavenproject1.gui.client.clientProduit.ProduitClient;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.ListeMachine;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.pekkam.Canvas;
import org.vaadin.pekkam.CanvasRenderingContext2D;

public class premiere_page extends VerticalLayout {

    private Vue_principale_login main;
    private H1 titre;
    private H2 sstitre;
    private Button administrateur;
    private Button client;
    private Button client_dev;
    private Button administrateur_dev;
    private HorizontalLayout h;
    private HorizontalLayout h_dev;
    private ImageT im;
    private Canvas canvas;
    private CanvasRenderingContext2D ctx;
    
    
    public premiere_page(Vue_principale_login main) {
        this.main = main;
        this.titre = new H1("MarcoPolo");
        this.sstitre = new H2("La voie du commerce moderne");
        this.client = new Button("Client");
        this.administrateur = new Button("Operateur");
        this.client_dev = new Button("Connection direct client (sur le compte du client avec id = 1 si il existe)");
        this.administrateur_dev = new Button("Connection direct operateur");
        canvas = new Canvas(340,250);
        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");

        // Ajoutez des styles aux composants
//        this.titre.getStyle()
//            .set("color", "Indigo")
//            .set("border-radius", "10px") 
//            .set("padding", "10px");
        this.sstitre.getStyle()
    .set("color", "#030876")  
    .set("border-radius", "10px")
    .set("font-family", "Bookman")
    .set("padding", "10px");
        this.client.getStyle().set("color", "#FEE59D").set("background-color", "#030876").setWidth("180px");
        this.administrateur.getStyle().set("color", "#FEE59D").set("background-color", "#030876").setWidth("180px");
        this.client_dev.getStyle().set("color", "Crimson").set("background-color", "PowderBlue").setOpacity("0");
        this.administrateur_dev.getStyle().set("color", "Crimson").set("background-color", "PowderBlue").setOpacity("0");

        administrateur_dev.addClickShortcut(Key.KEY_D, KeyModifier.ALT);
        client_dev.addClickShortcut(Key.KEY_C, KeyModifier.ALT);
        
        this.client.addClickListener(e -> {
            this.main.setMainContent(new login_client(main));
        });
        this.administrateur.addClickListener(e -> {
            this.main.setMainContent(new login_operateur(main));
        });
        this.administrateur_dev.addClickListener(e -> {
            VaadinSession.getCurrent().setAttribute("id_operateur", 1);
            UI.getCurrent().navigate(ListeMachine.class);
        });
        this.client_dev.addClickListener(e -> {
            VaadinSession.getCurrent().setAttribute("id_client", 1);
            UI.getCurrent().navigate(ProduitClient.class);
        });
        this.h = new HorizontalLayout(this.administrateur, this.client);
        this.h_dev = new HorizontalLayout(this.administrateur_dev, this.client_dev);
        this.add(canvas,this.sstitre, this.h, this.h_dev);
        

        String imageName = "MarcoLogo.jpg";
        try {
            ImageT image = ImageT.getImageByName(conn, imageName);
            if (image != null) {
                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());
                canvas.getStyle().set("background", "url(data:image/jpeg;base64," + base64Image+")");
            } else {
                System.err.println("Image not found in the database.");
            }
        } catch (SQLException | IOException e) {
            Notification.show("probleme style : " + e);
        }
        
        this.getStyle().setBackground("#FEE59D").set("background-size", "cover").set("height", "90vh");
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.setAlignItems(FlexComponent.Alignment.CENTER);
    }
}

