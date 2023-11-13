/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author binde
 */
public class Supp_machine extends VerticalLayout{
    private MenuBar menu_bar ;
    private MenuItem liste;
    
    
    public Supp_machine(){
        this.menu_bar = new MenuBar();
        this.liste = menu_bar.addItem("Machines");
        SubMenu liste_sub = this.liste.getSubMenu();
        List<String> messages = Arrays.asList("Hello", "World!", "How", "Are", "You");
        for (String x : messages) { 
            liste_sub.addItem(x);
        }
        ComponentEventListener<ClickEvent<MenuItem>> listener = e -> selected.setText(e.getSource().getText());
        this.add(this.menu_bar);
    }
}
