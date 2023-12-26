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

/**
 *
 * @author binde
 */
public class commande {
    
    private int id_commande;   
    private String nom_commande;
    private String des_commande;
    private int id_client;
    
    public commande(int id_commande, String nom_commande, String des_commande,int id_client) {
        this.id_commande = id_commande;
        this.nom_commande = nom_commande;
        this.des_commande = des_commande;
        this.id_client = id_client;
    }
    
    public commande(String nom_commande, String des_commande,int id_client) {
        this(-1, nom_commande, des_commande,id_client);
    }
    
    public static commande demande() {
        int id_client = ConsoleFdB.entreeInt("id_client : ");
        String nom_commande = ConsoleFdB.entreeString("nom_commande : ");
        String des_commande = ConsoleFdB.entreeString("des_commande ");
        return new commande(nom_commande,des_commande,id_client);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into commande_bof (nom_commande, des_commande, id_client) values (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.nom_commande);
            pst.setString(2, this.des_commande);
            pst.setInt(3, this.id_client);
            pst.executeUpdate();

            // Récupérer l'ID généré
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id_commande = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("La récupération de l'ID généré a échoué.");
                }
            }
        }
    }

    public int getTheID() {
        return this.id_commande;
    }

    public void supCommande(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from commande_bof where id_commande = ?")) {
            pst.setInt(1, this.id_commande);
            pst.executeUpdate();
        }
    }
    
    public static void supCommande(Connection con, int id_commande) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from commande_bof where id_commande = ?")) {
            pst.setInt(1, id_commande);
            pst.executeUpdate();
        }
    }
    
    public static List<commande> tousLesCommandes(int idc, Connection con) throws SQLException {
        List<commande> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_commande,nom_commande,des_commande,id_client from commande_bof where id_client=?")) {
            pst.setInt(1, idc);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_commande = rs.getInt("id_commande");
                    String nom_commande = rs.getString("nom_commande");
                    String des_commande = rs.getString("des_commande");
                    int id_client = rs.getInt("id_client");
                    res.add(new commande(id_commande, nom_commande, des_commande, id_client));
                }
            }
        }
        return res;
    }
    
    public static List<commande> tousLesCommandes(Connection con) throws SQLException {
        List<commande> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_commande,nom_commande,des_commande,id_client from commande_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_commande = rs.getInt("id_commande");
                    String nom_commande = rs.getString("nom_commande");
                    String des_commande = rs.getString("des_commande");
                    int id_client = rs.getInt("id_client");
                    res.add(new commande(id_commande, nom_commande, des_commande, id_client));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Commande enregistrée :{" + "id_commande=" + getId_commande() + ", nom_commande=" + getNom_commande()+ ", des_commande=" + getDes_commande()  + ", Id_client=" + getId_client() + '}';
    }

    public static void setNom_c(String nom_commande, int id_commande, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update commande_bof set nom_commande = ? where id_commande = ?")) {
            pst.setString(1, nom_commande);
            pst.setInt(2, id_commande);            
            pst.executeUpdate();
        }
    }

    public static void setDes(String des_commande, int id_commande, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update commande_bof set des_commande = ? where id_commande = ?")) {
            pst.setString(1, des_commande);
            pst.setInt(2, id_commande);            
            pst.executeUpdate();
        }
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setNom_commande(String nom_commande) {
        this.nom_commande = nom_commande;
    }

    public void setDes_commande(String des_commande) {
        this.des_commande = des_commande;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    
    public int getId_commande() {
        return id_commande;
    }

    public String getNom_commande() {
        return nom_commande;
    }

    public String getDes_commande() {
        return des_commande;
    }

    public int getId_client() {
        return id_client;
    }

    public static void main(String[] args) throws SQLException {
        commande commande = new commande("Serviette", "POur moi", 1);
        commande commande1 = new commande("Pull", "POur moi", 1);
        try {
            Connection con = connectSurServeurM3();
            commande.saveInDBV1(con);
            commande1.saveInDBV1(con);
//            client.saveInDBV(con);
//            List<Client> liset_c = tousLesClients(con);
//            List<Integer> liste = condition(con);
//            System.out.println(liste);
//            System.out.println(liset_c);
//            client.saveInDBV(connectSurServeurM3());
        }
        catch (SQLException ex) {
            throw new Error(ex);
        }
    }
}
