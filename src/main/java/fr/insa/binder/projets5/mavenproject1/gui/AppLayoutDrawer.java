/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayout.Section;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;

/**
 *
 * @author schmi
 */
public class AppLayoutDrawer extends AppLayout {
    
    Tab detail = new Tab("detail");
    Tab detail2 = new Tab("detail2");
    
    public AppLayoutDrawer() {
        H1 title = new H1("MyApp");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)");

        detail.addComponentAsFirst(new Button("say smtg"));
        
        
        Tabs tabs = new Tabs(detail,detail2);

        addToNavbar(title, tabs);
    }
    
    
}
