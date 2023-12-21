/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

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
public class type_etat {

    private int id_type_etat;
    private String des_type_etat;

    private type_etat(int id_type_etat, String des_type_etat) {
        this.id_type_etat = id_type_etat;
        this.des_type_etat = des_type_etat;
    }

    public type_etat(String des_type_etat) {
        this(-1, des_type_etat);
    }

    public void save_type_etat(Connection conn) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into type_etat_bof (des_type_etat) values (?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.des_type_etat);
            pst.executeUpdate();
       
        try (ResultSet ids = pst.getGeneratedKeys()) {
                ids.next();
                this.id_type_etat = ids.getInt(1);
            }
        }
    }
        public static void supTypeEtat(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from type_etat_bof where id_type_etat = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
    public static List<type_etat> tousLesTypeEtats(Connection con) throws SQLException {
        List<type_etat> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_type_etat,des_type_etat from type_etat_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_type_etat = rs.getInt("id_type_etat");
                    String des_type_etat = rs.getString("des_type_etat");
                    res.add(new type_etat(id_type_etat, des_type_etat));
                }
            }
        }
        return res;
    }
    @Override
    public String toString() {
        return "type_etat{" + "id_type_etat=" + id_type_etat + ", des_type_etat=" + des_type_etat + '}';
    }
    public static void setDes(String des, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update type_etat_bof set des_type_etat = ? where id_type_etat = ?")) {
            pst.setString(1, des);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    public int getId_type_etat() {
        return id_type_etat;
    }
    public String getDes_type_etat() {
        return des_type_etat;
    }
    public void setDes_type_etat(String des_type_etat) {
        this.des_type_etat = des_type_etat;
    }

}
