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
