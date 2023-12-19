/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import static fr.insa.binder.projets5.mavenproject1.Gestion.debut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author abinder01
 */
public class Client {
    
    private int id_client;
    private String nom_client;
    private String prenom_client;
    private String login_client;
    private String password_client;
    
    public Client (int id, String nom_client, String prenom, String login, String mdp) {
        this.id_client = id ;
        this.nom_client = nom_client;
        this.prenom_client = prenom;
        this.login_client = login;
        this.password_client = mdp;
    }
    
    public Client (String nom_client,  String prenom, String login, String mdp) {
        this(-1, nom_client, prenom, login, mdp);
    }
    
//    public static Client demande() {
//        String nom_client = ConsoleFdB.entreeString("Nom : ");
//        String mdp = ConsoleFdB.entreeString("mdp : ");
//        return new Client(nom_client, mdp);
//    }
    
    public void saveInDBV(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into client_bof (nom_client, prenom_client, login_client, password_client) values (?,?, ? , ?)")) {
            pst.setString(1, this.nom_client);
            pst.setString(2, this.prenom_client);
            pst.setString(3, this.login_client);
            pst.setString(4, this.password_client);
            pst.executeUpdate();
        }
    } 
    
    public static void creerClient(Connection con) throws SQLException {
        Client client1 = new Client("Binder", "Aurore", "Auroraa", "Aurore");
        Client client2 = new Client("Schmitt", "Theo", "Theo", "Theo");
        Client client3 = new Client("Dalibard", "Melanie", "Melanie", "Melanie");
        client1.saveInDBV(con);
        client2.saveInDBV(con);
        client3.saveInDBV(con);
    }
    
    public void supClient(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from client_bof where id = ?")) {
            pst.setInt(1, this.id_client);
            pst.executeUpdate();
        }
    }
    
    public static void supClient(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from client_bof where id_client = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
    public static List<Client> tousLesClients(Connection con) throws SQLException {
        List<Client> liste = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_client,nom_client, prenom_client, login_client, password_client from client_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_client");
                    String nom = rs.getString("nom_client");
                    String prenom = rs.getString("prenom_client");
                    String login = rs.getString("login_client");
                    String mdp = rs.getString("password_client");
                    liste.add(new Client(id, nom, prenom, login, mdp));
                }
            }
        }
        return liste;
    }

    public static Optional<Integer> login_c(Connection con, String nom, String pass) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "select id_client from client_bof where login_client = ? and password_client = ?")) {

            pst.setString(1, nom);
            pst.setString(2, pass);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return Optional.of(res.getInt("id_client"));
            } else {
                return Optional.empty();
            }
        }
    }
    
    public static List<Integer> condition (Connection con) throws SQLException {
        List<Integer> liste = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_client"
                + " from client_bof "
                + " where login_client = ?")) {
            pst.setString(1, "Aurore");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_client");
                    liste.add(id);
                }
            }
        }
        return liste;
    }
    
    @Override
    public String toString() {
        return "Client{" + "Id=" + getId_client() + ", prenom=" + getPrenom_client() + ", nom=" + getNom_client() + ", login=" + getLogin_client() +", mdp=" + getPassword_client() +'}';
    }
    
 
    public static void setPrenom(String prenom, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update client_bof set nom_client = ? where id_client = ?")) {
            pst.setString(1, prenom);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    
    public static void setNom(String nom, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update client_bof set prenom_client = ? where id_client = ?")) {
            pst.setString(1, nom);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    
    public static void setLogin(String login, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update client_bof set login_client = ? where id_client = ?")) {
            pst.setString(1, login);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }

    public static void setPassword(String mdp, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update client_bof set password_client = ? where id_client = ?")) {
            pst.setString(1, mdp);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public void setLogin_client(String login_client) {
        this.login_client = login_client;
    }

    public void setPassword_client(String password_client) {
        this.password_client = password_client;
    }

    public int getId_client() {
        return id_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public String getLogin_client() {
        return login_client;
    }

    public String getPassword_client() {
        return password_client;
    }
    
    public static String getnom_client(int id, Connection con) throws SQLException{
        String nom = "Personne";
        String prenom = "Personne";
        try (PreparedStatement pst = con.prepareStatement(
                "select nom_client, prenom_client"
                + " from client_bof "
                + " where id_client = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    nom = rs.getString("nom_client");
                    prenom = rs.getString("prenom_client");
                }
            }
        }
        return nom + " " + prenom;
    }
    
    public static void main(String[] args) throws SQLException {
//        Client client = new Client("Theo", "Aurore", "Aurora", "Aurore");
//        Client client1 = new Client ("Melanie", "Salut", "Bof", "Aurore"); 
        try {
            Connection con = connectSurServeurM3();
            creerClient(con);
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
