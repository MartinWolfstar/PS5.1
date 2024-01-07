/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienPlanUsine;



import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.poste_de_travail;
import java.sql.Connection;
import java.sql.SQLException;
import org.vaadin.pekkam.Canvas;
import org.vaadin.pekkam.CanvasRenderingContext2D;
import org.vaadin.pekkam.event.MouseEvent;
//import org.vaadin.hezamu.canvas.Canvas;

@PageTitle("Plan")
@Route(value = "22", layout = BarreGaucheTechnicien.class)
public class technicien_PlanUsine extends VerticalLayout {

    private Canvas canvas;
    private CanvasRenderingContext2D ctx;

//    private HorizontalLayout canvas;
    private int x;
    private int y;
    private poste_de_travail pdt;
    
    public technicien_PlanUsine() {
        // Title
        add(new H3("Plan de l'usine"));
        x = -1;
        y = -1;
        // Drawing area (Canvas)
        canvas = new Canvas(1000, 850);
        canvas.getStyle().set("border", "1px solid");
        ctx = canvas.getContext();
        ctx.setFillStyle("yellow");
        add(canvas);
        //ctx.drawImage("images/atelier1.jpg", 0, 0);
        
        
        canvas.addMouseClickListener(e -> logEvent("click", e));
        canvas.addMouseDblClickListener(e -> logEvent("dblClick", e));

    }

    private void logEvent(String eventType, MouseEvent me) {
//        drawHouse();
        //ctx.setStrokeStyle("#000000"); // Black color
        //ctx.strokeRect(me.getOffsetX() , me.getOffsetY(), 100, 100);
        Ajouter_poste_de_travail(me);
        System.out.println("mouse " + eventType + ": x=" + me.getOffsetX() + ", y=" + me.getOffsetY() + ", btn=" + me.getButton());
    }
    
    private void Ajouter_poste_de_travail(MouseEvent me){
        if ((this.x == -1)&&(this.y==-1)){
            this.x = me.getOffsetX();
            this.y = me.getOffsetY();
//        }else if (IsIn(me.getOffsetX(),me.getOffsetY())){
//            //verifier qu'aucune coordonée ne se trouver dans un poste de travail existant
//            Notification.show("impossible de creer le pdt ");
        }else{
            showDialog(me);
            
            ctx.setStrokeStyle("#FFC300");
            ctx.beginPath();
            ctx.moveTo(this.x, this.y);
            ctx.lineTo(me.getOffsetX(), this.y);
            ctx.lineTo(me.getOffsetX(), me.getOffsetY());
            ctx.lineTo(this.x, me.getOffsetY());
            ctx.lineTo(this.x, this.y);
            ctx.stroke();
        }
        
    }
    private void showDialog(MouseEvent me) {
        // Créer une fenêtre modale
        Dialog enterDialog = new Dialog();
        enterDialog.setCloseOnOutsideClick(true);
        enterDialog.setWidth("700px"); // Ajustez la largeur selon vos besoins
        enterDialog.setModal(true);

        VerticalLayout V1 = new VerticalLayout();
        V1.add(new H4("Nom de la zone de travail :"));
        enterDialog.add(V1);
        TextField rech = new TextField();
        Button okButton = new Button("valider");
        okButton.addClickListener(e -> {
            this.pdt = new poste_de_travail(rech.getValue(), this.x , this.y , me.getOffsetX() , me.getOffsetY());
            try {
                pdt.save_poste_de_travail((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                Notification.show("nouveau pdt : " + pdt);
                //UI.getCurrent().getPage().reload();
            } catch(SQLException ex) {
                Notification.show("Problème BdD : ajout pdt : " + ex);
            }
            this.x= -1;
            this.y = -1;
            enterDialog.close();
        });
        okButton.addClickShortcut(Key.ENTER);
        V1.add(rech,okButton);
        // Close button
        Button closeButton = new Button("Fermer", event -> enterDialog.close());
        enterDialog.add(closeButton);

        // Ouvrir la fenêtre modale
        enterDialog.open();
        
    }
    
    private void drawGrid() {
        int gridSize = 50; // Adjust the grid size as needed

        // Set the stroke style for the grid lines
        ctx.setStrokeStyle("#DAF7A6"); // Black color

        // Draw vertical grid lines
        for (int x = 0; x <= 1000; x += gridSize) {
            ctx.beginPath();
            ctx.moveTo(x, 0);
            ctx.lineTo(x, 1000);
            ctx.stroke();
        }

        // Draw horizontal grid lines
        for (int y = 0; y <= 1000; y += gridSize) {
            ctx.beginPath();
            ctx.moveTo(0, y);
            ctx.lineTo(1000, y);
            ctx.stroke();
        }
    }

//    private void drawHouse() {
//        ctx.save();
//
//        ctx.setFillStyle("yellow");
//        ctx.strokeRect(200, 200, 100, 100);
//        ctx.fillRect(200, 200, 100, 100);
//       
//        ctx.beginPath();
//        ctx.moveTo(180, 200);
//        ctx.lineTo(250, 150);
//        ctx.lineTo(320, 200);
//        ctx.closePath();
//        ctx.stroke();
//        ctx.setFillStyle("orange");
//        ctx.fill();
//        
//
//        ctx.restore();
//    }

    //Salut !
    
}
