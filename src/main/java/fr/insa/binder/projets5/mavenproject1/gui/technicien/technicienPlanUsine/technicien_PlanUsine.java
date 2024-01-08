/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienPlanUsine;



import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.BarreGaucheTechnicien;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienHabilitation.Ajout_Habilitation;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.Ajout_machine;
import fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine.Grid_machine;
import fr.insa.binder.projets5.mavenproject1.machine;
import fr.insa.binder.projets5.mavenproject1.operateur;
import fr.insa.binder.projets5.mavenproject1.poste_de_travail;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
    private String action;
    
    public technicien_PlanUsine() {
        // Title
        add(new H3("Plan de l'usine"));
        x = -1;
        y = -1;

        // Drawing area (Canvas)
        canvas = new Canvas(1000, 850);
        canvas.getStyle().set("border", "10px solid gray"); // Bordure de 10px en gris
        //canvas.getStyle().set("background-color", "ash"); 
        ctx = canvas.getContext();
        rebout();

        Button ajoutB = new Button("ajouter PDT", event -> this.action = "ajout" );
        Button modifB = new Button("modifier PDT", event -> this.action = "modifier" );
        Button suppB = new Button("supprimer PDT", event -> this.action = "supprimer" );
        HorizontalLayout H1 = new HorizontalLayout();
        H1.add(ajoutB,modifB,suppB);
        
        add(H1,canvas);

        canvas.addMouseClickListener(e -> logEvent("click", e));
        //canvas.addMouseDblClickListener(e -> logEvent("dblClick", e));
    }

    public void rebout(){ 
        ctx.clearRect(0, 0, 1000, 1000);
        // Dessiner le cadrillage orange foncé espacé de 10px
        //ctx.drawImage("images/porte.png", 350, 0);
        drawGrid("orange", 50);
        affiche_PDT_existant();
        
    }
    
    private void affiche_PDT_existant() {
        Map<String, List<Integer>> coordonneesEtNomMap = poste_de_travail.getCoordonneesEtNomPostesTravail(
                (Connection) VaadinSession.getCurrent().getAttribute("conn"));

        for (Map.Entry<String, List<Integer>> entry : coordonneesEtNomMap.entrySet()) {
            String refPoste = entry.getKey();
            List<Integer> coordinates = entry.getValue();

            int x1 = coordinates.get(0);
            int x2 = coordinates.get(1);
            int y1 = coordinates.get(2);
            int y2 = coordinates.get(3);

            ctx.setStrokeStyle("#000000");
            ctx.beginPath();
            ctx.moveTo(x1, y1);
            ctx.lineTo(x2, y1);
            ctx.lineTo(x2, y2);
            ctx.lineTo(x1, y2);
            ctx.lineTo(x1, y1);
            ctx.stroke();

            // Display the name of the workstation at x1
            ctx.setFillStyle("#000000");
            ctx.fillText(refPoste, x1, y1 - 5); // Adjust the -5 based on your preference for the vertical position

            // Check if there are machines for this workstation
            try {
                Connection connection = (Connection) VaadinSession.getCurrent().getAttribute("conn");
                int posteId = poste_de_travail.getId_poste_de_travail(refPoste, connection);
                List<machine> machines = machine.tousLesMachinesByPosteDeTravail(posteId, connection);

                // Draw a blue rectangle if there are machines
                if (!machines.isEmpty()) {
                    ctx.setFillStyle("#0000FF"); // Blue color
                    ctx.beginPath();
                    ctx.fillRect(x2, y1, 5, 5); // Adjust the position and size based on your preference
                    ctx.fill();
                }

                // Check if there are operators for this workstation
                List<operateur> operateurs = operateur.tousLesOperateursByPosteDeTravail(posteId, connection);

                // Draw a red rectangle if there are operators
                if (!operateurs.isEmpty()) {
                    ctx.setFillStyle("#FF0000"); // Red color
                    ctx.beginPath();
                    ctx.fillRect(x2 , y1 +5, 5, 5); // Adjust the position and size based on your preference
                    ctx.fill();
                }
            } catch (NumberFormatException | SQLException ex) {
                Notification.show("Erreur lors de la récupération des données : " + ex.getMessage());
            }
        }
    }





    
    private void logEvent(String eventType, MouseEvent me) {
//        drawHouse();
        //ctx.setStrokeStyle("#000000"); // Black color
        //ctx.strokeRect(me.getOffsetX() , me.getOffsetY(), 100, 100);
        Ajouter_poste_de_travail(me);
        Modifier_poste_de_travail(me);
        Supprimer_poste_de_travail(me);
        //UI.getCurrent().getPage().reload();
        
        
        System.out.println("mouse " + eventType + ": x=" + me.getOffsetX() + ", y=" + me.getOffsetY() + ", btn=" + me.getButton());
    }
    
    private void Ajouter_poste_de_travail(MouseEvent me){
        if ((this.x == -1)&&(this.y==-1)&&(this.action == "ajout")){
            this.x = me.getOffsetX();
            this.y = me.getOffsetY();
        }else if (poste_de_travail.IsIn(me.getOffsetX(),me.getOffsetY(),(Connection) VaadinSession.getCurrent().getAttribute("conn"))&&(this.action == "ajout")){
            //verifier qu'aucune coordonée ne se trouve dans un poste de travail existant
            Notification.show("impossible de creer le pdt ");
            this.x= -1;
            this.y = -1;
        }else if((poste_de_travail.IsIn(this.x,this.y,(Connection) VaadinSession.getCurrent().getAttribute("conn")))||(poste_de_travail.IsIn(this.x,me.getOffsetY(),(Connection) VaadinSession.getCurrent().getAttribute("conn")))||(poste_de_travail.IsIn(me.getOffsetX(),this.y,(Connection) VaadinSession.getCurrent().getAttribute("conn")))&&(this.action == "ajout")){
            Notification.show("impossible de creer le pdt ");
            this.x= -1;
            this.y = -1;
        }else if (this.action == "ajout"){
           
                showDialogAjout(me);
            
                ctx.setStrokeStyle("#000000");
                ctx.beginPath();
                ctx.moveTo(this.x, this.y);
                ctx.lineTo(me.getOffsetX(), this.y);
                ctx.lineTo(me.getOffsetX(), me.getOffsetY());
                ctx.lineTo(this.x, me.getOffsetY());
                ctx.lineTo(this.x, this.y);
                ctx.stroke();
            
        }
        
    }
    private void Modifier_poste_de_travail(MouseEvent me){
        if (poste_de_travail.IsIn(me.getOffsetX(),me.getOffsetY(),(Connection) VaadinSession.getCurrent().getAttribute("conn"))&&(this.action == "modifier")){
            
            
            List<Integer> id_pdt_l = poste_de_travail.getAll(me.getOffsetX(), me.getOffsetY(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
            int id_pdt = id_pdt_l.get(0);
            Notification.show("essai de modifier le pdt " + id_pdt);
            showDialogModif(id_pdt);
            
            
            this.x= -1;
            this.y = -1;   
            //UI.getCurrent().getPage().reload();
        }
    }
    
    private void showDialogModif(int id_pdt) {
        Dialog enterDialog = new Dialog();
        enterDialog.setCloseOnOutsideClick(true);
        enterDialog.setWidth("700px");
        enterDialog.setModal(true);

        enterDialog.add(new H4("Nom de la zone de travail : " + id_pdt));
        enterDialog.add(new H6("Machines contenues : "));

        try {
            Connection connection = (Connection) VaadinSession.getCurrent().getAttribute("conn");
            List<machine> machines = machine.tousLesMachinesByPosteDeTravail(id_pdt, connection);
//            Grid<machine> gridMachines = new Grid<>();
//            gridMachines.setItems(machines);
//
//            gridMachines.addColumn(machine::getId).setHeader("ID machine");
//            gridMachines.addColumn(machine::getDes).setHeader("Description");
//            gridMachines.addColumn(machine::getRef).setHeader("Référence");
            Grid_machine gridMachines = new Grid_machine(machines);

            // Set a fixed or maximum height for the Grid
            gridMachines.setMaxHeight("200px");

            enterDialog.add(gridMachines);

            // Button to add a machine
            Button addMachineButton = new Button("Ajouter une machine");
            addMachineButton.addClickListener(event -> {
                // Open a sub-dialog for adding a machine
                openAddMachineDialog(id_pdt);
            });
            Button addOperateurButton = new Button("ajouter son habilitation");
            addOperateurButton.addClickListener(event -> {
                //openAddOperateurDialog(id_pdt);
                Ajout_Habilitation ajout_habi = new Ajout_Habilitation(id_pdt);
                Notification.show("bclick");
                
            });
            enterDialog.add(addMachineButton,addOperateurButton);
        } catch (SQLException ex) {
            Notification.show("Essai de modif échoué pour les machines : " + ex.getMessage());
        }

        enterDialog.add(new H6("Opérateurs habilités : "));

        try {
            Connection connection = (Connection) VaadinSession.getCurrent().getAttribute("conn");
            List<operateur> operateurs = operateur.tousLesOperateursByPosteDeTravail(id_pdt, connection);

            Grid<operateur> gridOperateurs = new Grid<>(operateur.class);
            gridOperateurs.setItems(operateurs);

            // Adjust the columns based on your operateur class properties
            gridOperateurs.setColumns("id_operateur", "nom_operateur", "prenom_operateur");

            // Set a fixed or maximum height for the Grid
            gridOperateurs.setMaxHeight("200px");

            enterDialog.add(gridOperateurs);
        } catch (SQLException ex) {
            Notification.show("Essai de modif échoué pour les opérateurs : " + ex.getMessage());
        }

        // Close button
        Button closeButton = new Button("Fermer", event -> enterDialog.close());
        enterDialog.add(closeButton);

        // Open the modal window
        enterDialog.open();
    }

    // Method to open a sub-dialog for adding a machine
    private void openAddMachineDialog(int id_pdt) {
        Dialog addMachineDialog = new Dialog();
        addMachineDialog.setCloseOnOutsideClick(true);
        addMachineDialog.setWidth("700px");
        addMachineDialog.setModal(true);

        Ajout_machine ajoutMachineLayout = new Ajout_machine(id_pdt);

        addMachineDialog.add(ajoutMachineLayout);
        addMachineDialog.open();
    }
    // Method to open a sub-dialog for adding a machine
    private void openAddOperateurDialog(int id_pdt) {
        Dialog addMachineDialog = new Dialog();
        addMachineDialog.setCloseOnOutsideClick(true);
        addMachineDialog.setWidth("700px");
        addMachineDialog.setModal(true);

        Ajout_machine ajoutMachineLayout = new Ajout_machine(id_pdt);

        addMachineDialog.add(ajoutMachineLayout);
        addMachineDialog.open();
    }



    private void Supprimer_poste_de_travail(MouseEvent me){
        //Notification.show("s");
        if (poste_de_travail.IsIn(me.getOffsetX(),me.getOffsetY(),(Connection) VaadinSession.getCurrent().getAttribute("conn"))&&(this.action == "supprimer")){
            Notification.show("essai de supprimer le pdt ");
            poste_de_travail.sup(me.getOffsetX(),me.getOffsetY(),(Connection) VaadinSession.getCurrent().getAttribute("conn"));
            this.x= -1;
            this.y = -1;   
            UI.getCurrent().getPage().reload();
        }
    }
    
    private void showDialogAjout(MouseEvent me) {
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
            this.pdt = new poste_de_travail(rech.getValue(), this.x , me.getOffsetX(), this.y, me.getOffsetY());
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
    
    private void drawGrid(String couleur, int espacement) {
        int gridSize = espacement; // Adjust the grid size as needed

        // Set the stroke style for the grid lines
        ctx.setStrokeStyle(couleur); // Black color

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
