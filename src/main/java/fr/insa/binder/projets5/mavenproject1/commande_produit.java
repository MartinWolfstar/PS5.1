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
 * @author abinder01
 */
public class commande_produit {
    private int id_commande;
    private int id_produit;

    public commande_produit(int id_commande, int id_produit) {
        this.id_commande = id_commande;
        this.id_produit = id_produit;

    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into produit_commande_bof (id_commande,id_produit) values (?,?)")) {
            pst.setInt(1, this.id_commande);
            pst.setInt(2, this.id_produit);
            pst.executeUpdate();
        }
    } 
    
    public void supMachine(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from produit_commande_bof where id_commande = ?")) {
            pst.setInt(1, this.id_commande);
            pst.executeUpdate();
        }
    }
    
    public static void supMachine(Connection con, int id_commande) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from produit_commande_bof where id_commande = ?")) {
            pst.setInt(1, id_commande);
            pst.executeUpdate();
        }
    }
    
    public static List<commande_produit> tousLesMachines(Connection con) throws SQLException {
        List<commande_produit> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_commande,id_produit from produit_commande_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_commande = rs.getInt("id_commande");
                    int id_produit = rs.getInt("id_produit");
                    res.add(new commande_produit(id_commande, id_produit));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "produit_commande_bof{" + "id_commande=" + id_commande + ", id_produit=" + id_produit + '}';
    }

}
