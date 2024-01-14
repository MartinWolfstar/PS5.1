/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author melan
 */
public class etat {
    private int id_etat;
    private int id_type_etat;
    private Timestamp debut;
    private Timestamp fin;
    private String des_type_etat;

    public etat(int id_etat, int id_type_etat, Timestamp debut, Timestamp fin, String des_type_etat) {
        this.id_etat = id_etat;
        this.id_type_etat = id_type_etat;
        this.debut = debut;
        this.fin = fin;
        this.des_type_etat = des_type_etat;
    }

    public etat(int id_type_etat, Timestamp debut, Timestamp fin) {
        this(-1, id_type_etat, debut, fin, null);
    }
    
    public etat(int id_etat, int id_type_etat, Timestamp debut, Timestamp fin) {
        this(id_etat, id_type_etat, debut,fin, null);
    }
    
    public void save_etat(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into etat_bof (id_type_etat,debut,fin) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, this.id_type_etat);
            pst.setTimestamp(2, this.debut);
            pst.setTimestamp(3, this.fin);
            pst.executeUpdate();
            try (ResultSet ids = pst.getGeneratedKeys()) {
                ids.next();
                this.id_etat = ids.getInt(1);
            }
        }
    }
    
    public static List<etat> tousLesEtats(Connection con) throws SQLException {
        List<etat> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_etat,id_type_etat,debut,fin from etat_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_etat");
                    int ite = rs.getInt("id_type_etat");
                    Timestamp d=rs.getTimestamp("debut");
                    Timestamp f=rs.getTimestamp("debut");
                    res.add(new etat(id,ite,d,f));
                }
            }
        }
        return res;
    }
    
    public static List<etat> tousLesEtats_op(Connection con, int id_op) throws SQLException {
        List<etat> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select etat_bof.id_etat,id_type_etat,debut,fin from etat_bof join operateur__etat_bof on operateur__etat_bof.id_etat = etat_bof.id_etat where id_operateur = ?")) {

            pst.setInt(1, id_op);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_etat");
                    int ite = rs.getInt("id_type_etat");
                    Timestamp d=rs.getTimestamp("debut");
                    Timestamp f=rs.getTimestamp("fin");
                    res.add(new etat(id,ite,d,f));
                }
            }
        }
        return res;
    }
    
    public static List<etat> tousLesEtats_m(Connection con, int id_m) throws SQLException {
        List<etat> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select etat_bof.id_etat,id_type_etat,debut,fin from etat_bof join machine__etat_bof on machine__etat_bof.id_etat = etat_bof.id_etat where id_machine = ?")) {

            pst.setInt(1, id_m);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_etat");
                    int ite = rs.getInt("id_type_etat");
                    Timestamp d=rs.getTimestamp("debut");
                    Timestamp f=rs.getTimestamp("fin");
                    res.add(new etat(id,ite,d,f));
                }
            }
        }
        return res;
    }
    public void supEtat(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                " DELETE FROM machine__etat_bof WHERE id_etat=? ")) {
            pst.setInt(1, this.id_etat);
            pst.executeUpdate();
        }
        try (PreparedStatement pst = con.prepareStatement(
                "DELETE FROM operateur__etat_bof WHERE id_etat = ?")) {
            pst.setInt(1, this.id_etat);
            pst.executeUpdate();
        }
        try (PreparedStatement pst = con.prepareStatement(
                "DELETE FROM etat_bof WHERE id_etat = ?")) {
            pst.setInt(1, this.id_etat);
            pst.executeUpdate();
        }
    }
    
    public static void supEtat(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from etat_bof where id_etat = ?")) {
            pst.setInt(1, id);
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

    public String getDes_type_etat() {
        return des_type_etat;
    }
    
    
}
