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
public class poste_de_travail {
   private int id_poste_de_travail;
   private String ref_poste_de_travail;

    public poste_de_travail(int id_poste_de_travail, String ref_poste_de_travail) {
        this.id_poste_de_travail = id_poste_de_travail;
        this.ref_poste_de_travail = ref_poste_de_travail;
    }
    public void save_poste_de_travail(Connection conn) throws SQLException{
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into poste_de_travail_bof (id_poste_de_travail,ref_poste_de_travail) values (?,?)")) {
            pst.setInt(1, this.id_poste_de_travail);
            pst.setString(2, this.ref_poste_de_travail);
            pst.executeUpdate();
        }
    }
    public int getId_poste_de_travail() {
        return id_poste_de_travail;
    }

    public String getRef_poste_de_travail() {
        return ref_poste_de_travail;
    }

    public void setRef_poste_de_travail(String ref_poste_de_travail) {
        this.ref_poste_de_travail = ref_poste_de_travail;
    }

    @Override
    public String toString() {
        return "poste_de_travail{" + "id_poste_de_travail=" + id_poste_de_travail + ", ref_poste_de_travail=" + ref_poste_de_travail + '}';
    }
   
}
