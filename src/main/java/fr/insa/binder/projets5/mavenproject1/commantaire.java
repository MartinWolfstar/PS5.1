/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class commantaire {
    
    private int id_commentaire;   
    private int id_produit;
    private String message;
    private int id_client;
    
    public commantaire(int id_commentaire, int id_produit, String message,int id_client) {
        this.id_commentaire = id_commentaire;
        this.id_produit = id_produit;
        this.message = message;
        this.id_client = id_client;
    }
    
    public commantaire(int id_produit, String message,int id_client) {
        this(-1, id_produit, message,id_client);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into commentaire_bof (id_produit, id_client, message) values (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, this.id_produit);
            pst.setInt(2, this.id_client);
            pst.setString(3, this.message);
            pst.executeUpdate();

            // Récupérer l'ID généré
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id_commentaire = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("La récupération de l'ID généré a échoué.");
                }
            }
        }
    }

    public void supCommentaire(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from commentaire_bof where id_commentaire = ?")) {
            pst.setInt(1, this.id_commentaire);
            pst.executeUpdate();
        }
    }
    
    public static void supCommentaire(Connection con, int id_commentaire) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from commentaire_bof where id_commentaire = ?")) {
            pst.setInt(1, id_commentaire);
            pst.executeUpdate();
        }
    }
    
    public static List<commantaire> tousLescommentairesParProduit(int idProduit, Connection con) throws SQLException {
        List<commantaire> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_commentaire, id_produit, message, id_client from commentaire_bof where id_produit=?")) {
            pst.setInt(1, idProduit);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_commentaire = rs.getInt("id_commentaire");
                    int id_produit = rs.getInt("id_produit");
                    String message = rs.getString("message");
                    int id_client = rs.getInt("id_client");
                    res.add(new commantaire(id_commentaire, id_produit, message, id_client));
                }
            }
        }
        return res;
    }

    
    public static List<commantaire> tousLescommentaires(Connection con) throws SQLException {
        List<commantaire> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_commentaire,id_produit,message,id_client from commentaire_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_commentaire = rs.getInt("id_commentaire");
                    int id_produit = rs.getInt("id_produit");
                    String message = rs.getString("message");
                    int id_client = rs.getInt("id_client");
                    res.add(new commantaire(id_commentaire, id_produit, message, id_client));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "commentaire enregistrée :{" + "id_commentaire=" + getId_commentaire() + ", id_produit=" + getId_produit()+ ", message=" + getMessage()  + ", Id_client=" + getId_client() + '}';
    }

    public static void setNom_c(String id_produit, int id_commentaire, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update commentaire_bof set id_produit = ? where id_commentaire = ?")) {
            pst.setString(1, id_produit);
            pst.setInt(2, id_commentaire);            
            pst.executeUpdate();
        }
    }

    public static void setDes(String message, int id_commentaire, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update commentaire_bof set message = ? where id_commentaire = ?")) {
            pst.setString(1, message);
            pst.setInt(2, id_commentaire);            
            pst.executeUpdate();
        }
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getMessage() {
        return message;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    public void updateMessageInDB(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update commentaire_bof set message = ? where id_commentaire = ?")) {
            pst.setString(1, this.message);
            pst.setInt(2, this.id_commentaire);
            pst.executeUpdate();
        }
    }

}


