/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.binder.projets5.mavenproject1.gui.utilities.UploadArea;
import java.io.File;

/**
 *
 * @author schmi
 */
@PageTitle("Test")
@Route(value = "666", layout = BarreGaucheClient.class)
public class ZoneTest extends VerticalLayout{
    
    private Label test;
    private UploadArea area;
    
    public ZoneTest() {

        test = new Label("ceci est une zone de test");
        
        File uploadFolder = new File("images");
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        this.area = new UploadArea(uploadFolder);

        add(test,area);
    }
}
