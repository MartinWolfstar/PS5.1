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
public class exemplaire {

    private int id_exemplaire;
    private String des_exemplaire;
    private int id_produit;

    public exemplaire(int id_exemplaire, String des_exemplaire, int id_produit) {
        this.id_exemplaire = id_exemplaire;
        this.des_exemplaire = des_exemplaire;
        this.id_produit = id_produit;
    }

    public exemplaire(String des_exemplaire, int id_produit) {
        this(-1, des_exemplaire, id_produit);
    }

    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into exemplaire_bof (des_exemplaire,id_produit) values (?,?)")) {
            pst.setString(1, this.des_exemplaire);
            pst.setInt(2, this.id_produit);
            pst.executeUpdate();
        }
    }

    public void supExemplaire(Connection con) throws SQLException {
        try (PreparedStatement pst1 = con.prepareStatement(
                    "delete from exemplaire_bof where id_exemplaire = ?")){
            pst1.setInt(1, this.id_exemplaire);
            pst1.executeUpdate();
        }
    }

    public static void supExemplaire(Connection con, int id_exemplaire) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from exemplaire_bof where id_exemplaire = ?")) {
            pst.setInt(1, id_exemplaire);
            pst.executeUpdate();
        }
    }

    public static List<exemplaire> tousLesExemplaires(Connection con) throws SQLException {
        List<exemplaire> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_exemplaire,des_exemplaire,id_produit from exemplaire_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_exemplaire = rs.getInt("id_exemplaire");
                    String des_exemplaire = rs.getString("des_exemplaire");
                    int id_produit = rs.getInt("id_produit");
                    res.add(new exemplaire(id_exemplaire, des_exemplaire, id_produit));
                }
            }
        }
        return res;
    }

    public static List<exemplaire> tousLesxemplaires_produit(Connection con, int id_produit) throws SQLException {
        List<exemplaire> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_exemplaire,des_exemplaire,id_produit from exemplaire_bof where id_produit=?")) {
            pst.setInt(1, id_produit);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_exemplaire = rs.getInt("id_exemplaire");
                    String des_exemplaire = rs.getString("des_exemplaire");
                    res.add(new exemplaire(id_exemplaire, des_exemplaire, id_produit));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Exemplaire" + "id_exemplaire=" + getId_exemplaire() + ", des_exemplaire=" + getDes_exemplaire() + ", id_produit=" + getId_produit() + '}';
    }

    public int getId_exemplaire() {
        return id_exemplaire;
    }

    public String getDes_exemplaire() {
        return des_exemplaire;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_exemplaire(int id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public void setDes_exemplaire(String des_exemplaire) {
        this.des_exemplaire = des_exemplaire;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }


}
