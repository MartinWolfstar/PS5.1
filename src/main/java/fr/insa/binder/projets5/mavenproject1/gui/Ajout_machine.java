/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui;

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
public class Ajout_machine extends VerticalLayout{
    private MenuBar menu_bar;
    private MenuItem des;
    private MenuItem ref;
    
    public Ajout_machine(){
        this.menu_bar = new MenuBar();
        this.des = menu_bar.addItem("Description");
        this.ref = menu_bar.addItem("Reference");
        SubMenu des_sub = des.getSubMenu();
        List<String> messages = Arrays.asList("Hello", "World!", "How", "Are", "You");
        for (String x : messages) { 
            des_sub.addItem(x);
        }
        this.add(this.menu_bar);
    }
}
