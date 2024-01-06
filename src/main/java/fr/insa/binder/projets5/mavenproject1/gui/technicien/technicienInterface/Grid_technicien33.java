/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienInterface;

import com.vaadin.flow.component.grid.Grid;
import fr.insa.binder.projets5.mavenproject1.etat;
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
public class Grid_technicien33 extends Grid<etat>{
    public Grid_technicien33(List<etat> list_etat){
        this.setItems(list_etat);
        this.addColumn(etat::getId_etat).setHeader("Id_etat");
        this.addColumn(etat::getDes_type_etat).setHeader("getDes_type_etat");  
        
        this.addColumn(etat::getDebut).setHeader("Debut");
        this.addColumn(etat::getFin).setHeader("Fin");
        this.getStyle().setBackground("PowderBlue");
    }
    public static List<etat> get_etat_d_un_operateur(Connection conn,int id_operateur)throws SQLException{
        List<etat> out = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement("""
                                            SELECT etat_bof.id_etat,des_type_etat, debut, fin 
                                            FROM etat_bof 
                                            JOIN operateur__etat_bof ON etat_bof.id_etat = operateur__etat_bof.id_etat
                                            JOIN type_etat_bof ON etat_bof.id_type_etat = type_etat_bof.id_type_etat
                                            WHERE operateur__etat_bof.id_operateur = ?
                                            """)){
            pst.setInt(1,id_operateur);
            ResultSet res = pst.executeQuery();
            while (res.next()){
                out.add(new etat(res.getInt("id_etat"),-1,res.getTimestamp("debut"),res.getTimestamp("fin"), res.getString("des_type_etat")));
            }
        }
        return out;
    }
        
    
    
}