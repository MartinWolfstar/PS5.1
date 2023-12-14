/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/*
 *
 * @author melan
 */
public class etat {
    /*private int id_etat;
    private int id_type_etat;
    private Timestamp debut;
    private Timestamp fin;

    public etat(int id_etat, int id_type_etat, Timestamp debut, Timestamp fin) {
        this.id_etat = id_etat;
        this.id_type_etat = id_type_etat;
        this.debut = debut;
        this.fin = fin;
    }

    public etat(int id_type_etat, Timestamp debut, Timestamp fin) {
        this(-1, id_type_etat, debut, fin);
    }
    
    public void save_etat(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into etat_bof (id_etat,id_type_etat,debut,fin) values (?,?,?,?)")) {
            pst.setInt(1, this.id_etat);
            pst.setInt(2, this.id_type_etat);
            pst.setTimestamp(3, this.debut);
            pst.setTimestamp(4, this.fin);
            pst.executeUpdate();
        }
    } 
    
    
    @Override
    public String toString() {
        return "etat{" + "id_etat=" + id_etat + ", id_type_etat=" + id_type_etat + ", debut=" + debut + ", fin=" + fin + '}';
    }
    
    
    public int getId_etat() {
        return id_etat;
    }

   
    public int getId_type_etat() {
        return id_type_etat;
    }

    
    public void setId_type_etat(int id_type_etat) {
        this.id_type_etat = id_type_etat;
    }

    
    public Timestamp getDebut() {
        return debut;
    }

    
    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    
    public Timestamp getFin() {
        return fin;
    }

    
    public void setFin(Timestamp fin) {
        this.fin = fin;
    }
    */
    
}
