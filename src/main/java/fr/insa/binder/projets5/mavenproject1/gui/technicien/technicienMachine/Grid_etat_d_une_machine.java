/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienMachine;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import fr.insa.binder.projets5.mavenproject1.etat;
//import fr.insa.binder.projets5.mavenproject1.Machine__etat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melan
 */
public class Grid_etat_d_une_machine extends Grid<etat>{
    private List<etat> etats;
    public Grid_etat_d_une_machine(List<etat> list_etat){
        this.etats = list_etat;
        this.setItems(list_etat);
        this.addColumn(etat::getId_etat).setHeader("Id_etat");
        this.addColumn(etat::getDes_type_etat).setHeader("Des_type_etat");  
        
        this.addColumn(etat::getDebut).setHeader("Debut");
        this.addColumn(etat::getFin).setHeader("Fin");
        this.getStyle().setBackground("PowderBlue");
        
        this.addComponentColumn(etat -> {
            Button button = new Button("Supprimer", clickEvent -> {
                try {
                    etat.supEtat((Connection) VaadinSession.getCurrent().getAttribute("conn"));
                    UI.getCurrent().getPage().reload();
                    
                } catch (SQLException ex) {
                    Notification.show("Problème BdD : grid Machine__etat : " + ex);
                    // Gérez les erreurs ici
                }
            });
            return button;
        }).setHeader("");
        
    }
    public static List<etat> get_etat_d_une_machine(Connection conn,int id_machine)throws SQLException{
        List<etat> out = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement("""
                                            SELECT etat_bof.id_etat,des_type_etat, debut, fin 
                                            FROM etat_bof 
                                            JOIN machine__etat_bof ON etat_bof.id_etat = machine__etat_bof.id_etat
                                            JOIN type_etat_bof ON etat_bof.id_type_etat = type_etat_bof.id_type_etat
                                            WHERE machine__etat_bof.id_machine = ?
                                            ORDER BY etat_bof.id_etat
                                            """)){
            pst.setInt(1,id_machine);
            ResultSet res = pst.executeQuery();
            while (res.next()){
                out.add(new etat(res.getInt("id_etat"),-1,res.getTimestamp("debut"),res.getTimestamp("fin"), res.getString("des_type_etat")));
                System.out.print(res.getInt("id_etat"));
                System.out.print("  ");
                System.out.print(res.getTimestamp("debut"));
                System.out.print("  ");
                System.out.print(res.getTimestamp("fin"));
                System.out.print("  ");
                System.out.println(res.getString("des_type_etat"));
            }
        }
        return out;
    }
    public void setEtat(List<etat> etats){
        this.etats = etats;
        this.setItems(etats);
        
    }
    public Grid_etat_d_une_machine(){
        this(new ArrayList<>());
    }
}
