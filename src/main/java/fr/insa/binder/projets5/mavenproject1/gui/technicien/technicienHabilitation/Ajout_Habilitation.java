/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienHabilitation;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.operateur_poste_de_travail;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Ajout_Habilitation extends VerticalLayout{
    
    private MenuBar menu_bar;
    private MenuItem id;
    private operateur_poste_de_travail op_poste;
    
    public Ajout_Habilitation(){
        this.menu_bar = new MenuBar();
        this.id = menu_bar.addItem("Selectionner l'identifiant de l'habilitation");
        SubMenu id_sub = id.getSubMenu();
        Integer idTech = (Integer) VaadinSession.getCurrent().getAttribute("id_operateur");
        
        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
                {
            this.op_poste = new operateur_poste_de_travail(idTech, Integer.valueOf(e.getSource().getText()));
            try {
                op_poste.saveInDBV1((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            } catch (SQLException ex) {
                Notification.show("Problème BdD : ajout habilitation : " + ex);
            }
            //UI.getCurrent().getPage().reload();
        };
        try {
            List<operateur_poste_de_travail> id_liste = operateur_poste_de_travail.tousLesOpe_Poste((Connection) VaadinSession.getCurrent().getAttribute("conn"));
            for (operateur_poste_de_travail x : id_liste) { 
                id_sub.addItem(String.valueOf(x.getId_poste_de_travail()), listener);
        }      
        } 
        catch(SQLException ex) {
               Notification.show("Problème BdD : ajout habilitation 2 : " + ex);
            }
        this.add(new H3("ajouter habilitation"));
        this.add(menu_bar);
        }
    }
