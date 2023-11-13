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
 * @author binde
 */
public class produit {
    
    private int id_p;   
    private int ref_p;
    private String des_p;
    
    public produit(int id_p, String des_p, int ref_p) {
        this.id_p = id_p;
        this.ref_p = ref_p;
        this.des_p = des_p;
    }
    
    public produit(String des_p, int ref_p) {
        this(-1, des_p, ref_p);
    }
    
    public static produit demande() {
        int ref_p = ConsoleFdB.entreeInt("ref_p : ");
        String des_p = ConsoleFdB.entreeString("des_p : ");
        return new produit(des_p, ref_p);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into macchhiinnee (ref_p,des_p) values (?,?)")) {
            pst.setInt(1, this.ref_p);
            pst.setString(2, this.des_p);
            pst.executeUpdate();
        }
    } 
    
    public void supMachine(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from macchhiinnee where id_p = ?")) {
            pst.setInt(1, this.id_p);
            pst.executeUpdate();
        }
    }
    
    public static void supMachine(Connection con, int id_p) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from macchhiinnee where id_p = ?")) {
            pst.setInt(1, id_p);
            pst.executeUpdate();
        }
    }
    
    public static List<produit> tousLesMachines(Connection con) throws SQLException {
        List<produit> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_p,des_p,ref_p from macchhiinnee")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_p = rs.getInt("id_p");
                    String des_p = rs.getString("des_p");
                    int ref_p = rs.getInt("ref_p");
                    res.add(new produit(id_p, des_p, ref_p));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Machine{" + "id_p=" + getId() + ", ref_p=" + getRef() + ", des_p=" + getDes() + '}';
    }

    public void setRef(int ref_p) {
        this.ref_p = ref_p;
    }

    public static void setRef(int ref_p, int id_p, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update macchhiinnee set ref_p = ? where id_p = ?")) {
            pst.setInt(1, ref_p);
            pst.setInt(2, id_p);            
            pst.executeUpdate();
        }
    }
    
    public void setDes(String des_p) {
        this.des_p = des_p;
    }

    public static void setDes(String des_p, int id_p, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update macchhiinnee set des_p = ? where id_p = ?")) {
            pst.setString(1, des_p);
            pst.setInt(2, id_p);            
            pst.executeUpdate();
        }
    }
    
    public void setId(int id_p) {
        this.id_p = id_p;
    }

    public int getRef() {
        return ref_p;
    }

    public String getDes() {
        return des_p;
    }

    public int getId() {
        return id_p;
    }
}
