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
public class Operateur__etat {
    private int id_operateur;
    private int id_etat;

    public Operateur__etat(int id_operateur, int id_etat) {
        this.id_operateur = id_operateur;
        this.id_etat = id_etat;

    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into operateur__etat_bof (id_operateur,id_etat) values (?,?)")) {
            pst.setInt(1, this.id_operateur);
            pst.setInt(2, this.id_etat);
            pst.executeUpdate();
        }
    } 
    
    public void supOperateur__etat(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operateur__etat_bof where id_operateur = ?")) {
            pst.setInt(1, this.id_operateur);
            pst.executeUpdate();
        }
    }
    
    public static void supOperateur__etat(Connection con, int id_operateur) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operateur__etat_bof where id_operateur = ?")) {
            pst.setInt(1, id_operateur);
            pst.executeUpdate();
        }
    }
    
    public static List<Operateur__etat> tousLesOperateur__etat(Connection con) throws SQLException {
        List<Operateur__etat> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operateur,id_etat from operateur__etat_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operateur = rs.getInt("id_operateur");
                    int id_etat = rs.getInt("id_etat");
                    res.add(new Operateur__etat(id_operateur, id_etat));
                }
            }
        }
        return res;
    }

    public int getId_operateur() {
        return id_operateur;
    }

    public int getId_etat() {
        return id_etat;
    }

    @Override
    public String toString() {
        return "Operateur__etat{" + "id_operateur=" + id_operateur + ", id_etat=" + id_etat + '}';
    }
    
}
