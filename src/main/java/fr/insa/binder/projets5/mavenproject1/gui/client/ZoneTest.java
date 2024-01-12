/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.client;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.UploadArea;
import fr.insa.binder.projets5.mavenproject1.produit;
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
    private Button maj;
    
    public ZoneTest() {

        test = new Label("ceci est une zone de test");
        this.maj = new Button("maj de l'image des produits");
        
        //File uploadFolder = new File("C:\\Users\\schmi\\Documents\\NetBeansProjects\\PS5.1\\src\\main\\resources\\META-INF\\resources\\images");
//        File uploadFolder = new File("src\\main\\resources\\META-INF\\resources\\images");
//        if (!uploadFolder.exists()) {
//            uploadFolder.mkdirs();
//        }
        this.area = new UploadArea();

        
        
        add(test,area,maj);
    }
}
