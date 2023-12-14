/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.login;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import fr.insa.binder.projets5.mavenproject1.machine;
import java.sql.SQLException;

/**
 *
 * @author binde
 */


public class premiere_page extends VerticalLayout {
    
    private Vue_principale_login main;
    private H1 titre;
    private Button administrateur; 
    private Button client;
    private HorizontalLayout h; 
//    private Session sessionInfo;
//    private VerticalLayout mainContent;


    
    public premiere_page(Vue_principale_login main) {
        this.main = main;
        this.titre = new H1("Bienvenue sur notre site");
        this.client = new Button("Client");
        this.administrateur = new Button ("Operateur");
        this.client.addClickListener(e -> {
           this.main.setMainContent(new login_client(main));
        });
        this.administrateur.addClickListener(e -> {
        });
        this.h = new HorizontalLayout(this.administrateur, this.client);
        this.add(this.titre, this.h);
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.setAlignItems(FlexComponent.Alignment.CENTER);
    }
    
}
