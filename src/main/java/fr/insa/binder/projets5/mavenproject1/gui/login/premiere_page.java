package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class premiere_page extends VerticalLayout {

    private Vue_principale_login main;
    private H1 titre;
    private Button administrateur;
    private Button client;
    private HorizontalLayout h;

    public premiere_page(Vue_principale_login main) {
        this.main = main;
        this.titre = new H1("Bienvenue sur notre site");
        this.client = new Button("Client");
        this.administrateur = new Button("Operateur");

        // Ajoutez des styles aux composants
        this.titre.getStyle().set("color", "blue");
        //this.client.getStyle().set("background-color", "green");
        //this.administrateur.getStyle().set("background-color", "orange");

        this.client.addClickListener(e -> {
            this.main.setMainContent(new login_client(main));
        });
        this.administrateur.addClickListener(e -> {
            this.main.setMainContent(new login_client(main));
        });

        this.h = new HorizontalLayout(this.administrateur, this.client);

        // Ajoutez du style à la mise en page principale
        this.getStyle().set("background-color", "#f0f0f0");
        this.add(this.titre, this.h);
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.setAlignItems(FlexComponent.Alignment.CENTER);
        this.setMargin(true);
        this.setPadding(true);
    }
}

