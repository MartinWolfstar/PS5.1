/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author melan
 */
public class operateur {

    private int id_operateur;
    private String nom_operateur;
    private String prenom_operateur;
    private String login_operateur;
    private String password_operateur;

    private operateur(int id_operateur, String nom_operateur, String prenom_operateur, String login_operateur, String password_operateur) {
        this.id_operateur = id_operateur;
        this.nom_operateur = nom_operateur;
        this.prenom_operateur = prenom_operateur;
        this.login_operateur = login_operateur;
        this.password_operateur = password_operateur;
    }

    public operateur(String nom_operateur, String prenom_operateur, String login_operateur, String password_operateur) {
        this(-1,nom_operateur, prenom_operateur, login_operateur, password_operateur);
    }
    

    public void save_operateur(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into operateur_bof (id_operateur,nom_operateur,prenom_operateur,login_operateur,password_operateur) values (?,?,?,?,?)")) {
            pst.setInt(1, this.getId_operateur());
            pst.setString(2, this.getNom_operateur());
            pst.setString(3, this.getPrenom_operateur());
            pst.setString(4, this.getLogin_operateur());
            pst.setString(5, this.getPassword_operateur());
            pst.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "operateur{" + "id_operateur=" + id_operateur + ", nom_operateur=" + nom_operateur + ", prenom_operateur=" + prenom_operateur + ", login_operateur=" + login_operateur + ", password_operateur=" + password_operateur + '}';
    }
    
    /**
     * @return the id_operateur
     */
    public int getId_operateur() {
        return id_operateur;
    }

    /**
     * @return the nom_operateur
     */
    public String getNom_operateur() {
        return nom_operateur;
    }

    /**
     * @param nom_operateur the nom_operateur to set
     */
    public void setNom_operateur(String nom_operateur) {
        this.nom_operateur = nom_operateur;
    }

    /**
     * @return the prenom_operateur
     */
    public String getPrenom_operateur() {
        return prenom_operateur;
    }

    /**
     * @param prenom_operateur the prenom_operateur to set
     */
    public void setPrenom_operateur(String prenom_operateur) {
        this.prenom_operateur = prenom_operateur;
    }

    /**
     * @return the login_operateur
     */
    public String getLogin_operateur() {
        return login_operateur;
    }

    /**
     * @param login_operateur the login_operateur to set
     */
    public void setLogin_operateur(String login_operateur) {
        this.login_operateur = login_operateur;
    }

    /**
     * @return the password_operateur
     */
    public String getPassword_operateur() {
        return password_operateur;
    }

    /**
     * @param password_operateur the password_operateur to set
     */
    public void setPassword_operateur(String password_operateur) {
        this.password_operateur = password_operateur;
    }

}
