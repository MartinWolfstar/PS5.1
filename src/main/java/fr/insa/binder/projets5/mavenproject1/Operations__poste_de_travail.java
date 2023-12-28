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
public class Operations__poste_de_travail {
    private int id_operateur;
    private int id_poste_de_travail;
    
    public Operations__poste_de_travail(int id_operateur, int id_poste_de_travail) {
        this.id_operateur = id_operateur;
        this.id_poste_de_travail = id_poste_de_travail;
    }
    
    public void save_operations__poste_de_travail(Connection conn) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(
                "insert operations_poste_de_travail_bof (id_operateur,id_poste_de_travail) values (?,?)")) {
            pst.setInt(1, this.id_operateur);
            pst.setInt(2, this.id_poste_de_travail);
            pst.executeUpdate();
        }
    }
    public void supOperations__poste_de_travail(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations__poste_de_travail_bof where id_operateur = ?")) {
            pst.setInt(1, this.id_operateur);
            pst.executeUpdate();
        }
    }
    
    public static void supOperations__poste_de_travail(Connection con, int id_operateur) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations__poste_de_travail_bof where id_operateur = ?")) {
            pst.setInt(1, id_operateur);
            pst.executeUpdate();
        }
    }
    
    public static List<Operations__poste_de_travail> tousLesOperations__poste_de_travail(Connection con) throws SQLException {
        List<Operations__poste_de_travail> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operateur,id_poste_de_travail from operations__poste_de_travail_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operateur = rs.getInt("id_operateur");
                    int id_poste_de_travail = rs.getInt("id_poste_de_travail");
                    res.add(new Operations__poste_de_travail(id_operateur, id_poste_de_travail));
                }
            }
        }
        return res;
    }
    
    @Override
    public String toString() {
        return "Operations__poste_de_travail{" + "id_operateur=" + getId_operateur() + ", id_poste_de_travail=" + getId_poste_de_travail() + '}';
    }

    /**
     * @return the id_operateur
     */
    public int getId_operateur() {
        return id_operateur;
    }

    /**
     * @return the id_poste_de_travail
     */
    public int getId_poste_de_travail() {
        return id_poste_de_travail;
    }
    
}
