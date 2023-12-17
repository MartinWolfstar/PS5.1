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
public class machine__etat {
    private int id_machine;
    private int id_etat;

    public machine__etat(int id_machine, int id_etat) {
        this.id_machine = id_machine;
        this.id_etat = id_etat;
    }
    public void save_machine__etat(Connection conn) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(
                "insert machine__etat_bof (id_machine,id_etat) values (?,?)")) {
            pst.setInt(1, this.id_machine);
            pst.setInt(2, this.id_etat);
            pst.executeUpdate();
        }
    }
    public int getId_machine() {
        return id_machine;
    }

    public int getId_etat() {
        return id_etat;
    }

    @Override
    public String toString() {
        return "machine__etat{" + "id_machine=" + id_machine + ", id_etat=" + id_etat + '}';
    }
    
}
