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
public class Operateur__etat {
    private int id_operateur;
    private int id_etat;

    public Operateur__etat(int id_operateur, int id_etat) {
        this.id_operateur = id_operateur;
        this.id_etat = id_etat;
    }
    public void save_operateur__etat(Connection conn) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(
                "insert operateur__etat_bof (id_operateur,id_etat) values (?,?)")) {
            pst.setInt(1, this.id_operateur);
            pst.setInt(2, this.id_etat);
            pst.executeUpdate();
        }
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
