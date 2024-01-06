/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien;

import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienPlanUsine.technicien_PlanUsine;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMessage.technicienMessagerie;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.ListeMachine;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.TechnicienCommande.Afficher_commande;
import fr.insa.binder.projets5.mavenproject1.gui.client.clientCommande.CommandeClient;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienHabilitation.Habilitation;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface.ParametreTechnicien;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit.ProduitTechnicien;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienRealisation.ListeRealisation;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType.ListeTypeEtat;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType.ListeTypeMachine;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienType.ListeTypeOperation;

/**
 *
 * @author schmi
 */
@PageTitle("barreGaucheTech")
@Route(value = "20", layout = BarreGaucheTechnicien.class)
public class BarreGaucheTechnicien extends AppLayout {

    public BarreGaucheTechnicien() {
        creatHeader();
        createDrawer();
    }

    private void creatHeader() {
        H1 logo = new H1("App PS5 technicien");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("ListeMachine", ListeMachine.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink
        ));
        RouterLink listLink2 = new RouterLink("Plan", technicien_PlanUsine.class);
        listLink2.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink2
        ));
        RouterLink listLink3 = new RouterLink("Messagerie", technicienMessagerie.class);
        listLink3.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink3
        ));
        RouterLink listLink4 = new RouterLink("Produit", ProduitTechnicien.class);
        listLink4.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink4
        ));
        RouterLink listLink6 = new RouterLink("TypeOperation", ListeTypeOperation.class);
        listLink6.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink6
        ));
        RouterLink listLink7 = new RouterLink("TypeMachine", ListeTypeMachine.class);
        listLink7.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink7
        ));
        RouterLink listLink8 = new RouterLink("TypeEtat", ListeTypeEtat.class);
        listLink8.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink8
        ));
        RouterLink listLink9 = new RouterLink("Paramètre", ParametreTechnicien.class);
        listLink9.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink9
        ));

        RouterLink listLink10 = new RouterLink("Commande", Afficher_commande.class);
        listLink10.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink10
        ));
        RouterLink listLinkASup = new RouterLink("client", CommandeClient.class);
        listLinkASup.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLinkASup
        ));
        RouterLink listLink11 = new RouterLink("realisation", ListeRealisation.class);
        listLink11.setHighlightCondition(HighlightConditions.sameLocation());
        
        addToDrawer(new VerticalLayout(
                listLink11
        ));
//        RouterLink listLink12 = new RouterLink("habilitation", Habilitation.class);
//        listLink12.setHighlightCondition(HighlightConditions.sameLocation());
//        
//        addToDrawer(new VerticalLayout(
//                listLink12
//        ));
    }
}
