/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.etat;
import static fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface.Grid_technicien33.get_etat_d_un_operateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author melan
 */
public class Supp_etat extends VerticalLayout {

    private MenuBar menu_bar;
    private MenuItem id;
    private int id_operateur;

    public Supp_etat() {
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de l'état à supprimer");
        SubMenu id_sub = id.getSubMenu();

        // Recuperer id_operateur
        id_operateur = (int) VaadinSession.getCurrent().getAttribute("id_operateur");

        ComponentEventListener<ClickEvent<MenuItem>> listener = e
                -> {
            try {
                etat.supEtat((Connection) VaadinSession.getCurrent().getAttribute("conn"), Integer.valueOf(e.getSource().getText()));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : sto1");
            }
            UI.getCurrent().getPage().reload();
        };
        
        System.out.println("test");
        
        try {
            List<etat> id_liste = get_etat_d_un_operateur((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_operateur);
            for (etat x : id_liste) {
                id_sub.addItem(String.valueOf(x.getId_etat()), listener);
            }
        } catch (SQLException ex) {
            Notification.show("Problème BdD : sto2");
        }
        this.add(new H3("Supprimer un état"));
        this.add(menu_bar);
    }
}
