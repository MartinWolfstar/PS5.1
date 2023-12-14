/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.login;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import java.sql.SQLException;

/**
 *
 * @author binde
 */
@Route("")

public class Vue_principale_login extends VerticalLayout {
    

//    private SessionInfo sessionInfo;
//    private HorizontalLayout entete;
    private VerticalLayout mainContent;

//    public void setEntete(Component c) {
//        this.entete.removeAll();
//        this.entete.add(c);
//    }

    public void setMainContent(Component c) {
        this.mainContent.removeAll();
        this.mainContent.add(c);
    }

    public Vue_principale_login() {
//       this.sessionInfo = new SessionInfo();
//        this.entete = new HorizontalLayout();
//        this.entete.setWidthFull();
//        this.add(this.entete);
        this.mainContent = new VerticalLayout();
        this.mainContent.setWidthFull();
        this.mainContent.setHeightFull();
        this.add(this.mainContent);
        try {
            VaadinSession.getCurrent().setAttribute("conn", connectSurServeurM3());
            this.setMainContent(new premiere_page(this));
        } catch (SQLException ex) {
//            this.setMainContent(new BdDNonAccessible(this));
        }

    }

    
    /**
     * @return the sessionInfo
     */
//    public SessionInfo getSessionInfo() {
//        return sessionInfo;
//    }    
}
