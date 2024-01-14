/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import fr.insa.binder.projets5.mavenproject1.gui.client.clientParametre.ParametreClient;
import fr.insa.binder.projets5.mavenproject1.gui.client.clientCommentaire.CommentaireClient;
import fr.insa.binder.projets5.mavenproject1.gui.client.clientProduit.ProduitClient;
import fr.insa.binder.projets5.mavenproject1.gui.client.clientCommande.CommandeClient;
import fr.insa.binder.projets5.mavenproject1.gui.zoneTest.MainLayout;
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
import fr.insa.binder.projets5.mavenproject1.gui.login.Vue_principale_login;

/**
 * 
 * @author schmi
 */
@PageTitle("barreGauche")
@Route(value = "10", layout = MainLayout.class)
public class BarreGaucheClient extends AppLayout{
    
    public BarreGaucheClient(){
        creatHeader();
        createDrawer();
        this.getStyle().setBackground("#FEE59D");
    }

    private void creatHeader() {
        H1 logo = new H1("MarcoPolo");
        logo.getStyle().setColor("#030876");
        logo.addClassName("logo");
        
//        String imageName = "MarcoL.jpg";
//        Connection conn = (Connection) VaadinSession.getCurrent().getAttribute("conn");
//        Image img = new Image();
//        try {
//            ImageT image = ImageT.getImageByName(conn, imageName);
//
//            if (image != null) {
//                String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageBytes());
//                img = new Image("url(data:image/jpeg;base64," + base64Image + ")", "");
//
//            } else {
//                System.err.println("Image not found in the database.");
//            }
//        } catch (SQLException | IOException e) {
//            Notification.show("probleme style : " + e);
//        }
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setMinHeight("60px");
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.getStyle().setBackground("#ffde75");
        addToNavbar(header);
        
    }


    private void createDrawer() {
        VerticalLayout drawerLayout = new VerticalLayout();
        drawerLayout.getStyle().setBackground("#ffde75");
        drawerLayout.setMinHeight("100vh");
        RouterLink listLink = new RouterLink("Produit", ProduitClient.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        listLink.getStyle().setColor("#030876");
        drawerLayout.add(new VerticalLayout(
                listLink
        ));
        RouterLink listLink2 = new RouterLink("Mes Commandes", CommandeClient.class);
        listLink2.setHighlightCondition(HighlightConditions.sameLocation());
        listLink2.getStyle().setColor("#030876");
        drawerLayout.add(new VerticalLayout(
                listLink2
        ));
        
        RouterLink listLink3 = new RouterLink("Données Personelles", ParametreClient.class);
        listLink3.setHighlightCondition(HighlightConditions.sameLocation());
        listLink3.getStyle().setColor("#030876");
        drawerLayout.add(new VerticalLayout(
                listLink3
        ));
        
       RouterLink listLink4 = new RouterLink("Commentaire", CommentaireClient.class);
        listLink4.setHighlightCondition(HighlightConditions.sameLocation());
        listLink4.getStyle().setColor("#030876");
        drawerLayout.add(new VerticalLayout(
                listLink4
        ));
        RouterLink listLink5 = new RouterLink("Log out", Vue_principale_login.class);
        listLink5.setHighlightCondition(HighlightConditions.sameLocation());
        listLink5.getStyle().setColor("#030876");
        drawerLayout.add(new VerticalLayout(
                listLink5
        ));
        addToDrawer(drawerLayout);
    }
}
