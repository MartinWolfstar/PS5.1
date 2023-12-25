package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.client.clientProduit.ProduitClient;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.ListeMachine;

public class premiere_page extends VerticalLayout {

    private Vue_principale_login main;
    private H1 titre;
    private Button administrateur;
    private Button client;
    private Button client_dev;
    private Button administrateur_dev;
    private HorizontalLayout h;
    private HorizontalLayout h_dev;
    
    
    public premiere_page(Vue_principale_login main) {
        this.main = main;
        this.titre = new H1("Bienvenue sur notre site");
        this.client = new Button("Client");
        this.administrateur = new Button("Operateur");
        this.client_dev = new Button("Connection direct client (sur le compte du client avec id = 1 si il existe)");
        this.administrateur_dev = new Button("Connection direct operateur");

        // Ajoutez des styles aux composants
        this.titre.getStyle()
            .set("color", "Indigo")
            .set("border-radius", "10px") 
            .set("padding", "10px");
        this.client.getStyle().set("color", "Crimson").set("background-color", "PowderBlue");
        this.administrateur.getStyle().set("color", "Crimson").set("background-color", "PowderBlue");
        this.client_dev.getStyle().set("color", "Crimson").set("background-color", "PowderBlue");
        this.administrateur_dev.getStyle().set("color", "Crimson").set("background-color", "PowderBlue");

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
        this.add(this.titre, this.h, this.h_dev);

        this.getStyle()
            .set("background", "url(images/1275600.jpg) no-repeat center center fixed")
            .set("background-size", "cover")
            .set("height", "100vh");

        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.setAlignItems(FlexComponent.Alignment.CENTER);
//        this.setMargin(true);
//        this.setPadding(true);
    }
}

