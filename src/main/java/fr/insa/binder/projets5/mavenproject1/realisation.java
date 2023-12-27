/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import com.vaadin.flow.component.notification.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melan
 */
public class realisation {

    private int id_realisation;
    private float duree;
    private int id_type_operation;
    private int id_machine;

    private realisation(int id_realisation, float duree, int id_type_operation, int id_machine) {
        this.id_realisation = id_realisation;
        this.duree = duree;
        this.id_type_operation = id_type_operation;
        this.id_machine = id_machine;
    }

    public realisation(float duree, int id_type_operation, int id_machine) {
        this(-1, duree, id_type_operation, id_machine);
    }

    public void save_realisation(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into realise_bof (duree, id_type_operation, id_machine) values (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setFloat(1, this.duree);
            pst.setInt(2, this.id_type_operation);
            pst.setInt(3, this.id_machine);
            pst.executeUpdate();
            try (ResultSet ids = pst.getGeneratedKeys()) {
                if (ids.next()) {
                    this.id_realisation = ids.getInt(1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "realisation{" + "id_realisation=" + id_realisation + "duree=" + duree + ", id_type_operation=" + id_type_operation + ", id_machine=" + id_machine + '}';
    }

    public static List<realisation> tousLesRealisation(Connection con) throws SQLException {
        List<realisation> liste = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_realisation,duree,id_type_operation, id_machine from realise_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_realisation = rs.getInt("id_realisation");
                    float duree = rs.getFloat("duree");
                    int id_type_operation = rs.getInt("id_type_operation");
                    int id_machine = rs.getInt("id_machine");
                    liste.add(new realisation(id_realisation, duree, id_type_operation, id_machine));
                }
            }
        }
        return liste;
    }

    public void supRealisation(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from realise_bof where id_realisation = ?")) {
            pst.setInt(1, this.id_realisation);
            pst.executeUpdate();
        }
    }

    public static void supRealisation(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from realise_bof where id_realisation = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public void setId_type_operation(int id_type_operation) {
        this.id_type_operation = id_type_operation;
    }

    public static void setTypeOperation(int id_typeOperation, int id_realisation, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update realise_bof set id_type_operation = ? where id_realisation = ?")) {
            pst.setInt(1, id_typeOperation);
            pst.setInt(2, id_realisation);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Notification.show("Problème : 85 : realisation");
        }
    }

    public static void setMachine(int id_Machine, int id_realisation, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update realise_bof set id_machine = ? where id_realisation = ?")) {
            pst.setInt(1, id_Machine);
            pst.setInt(2, id_realisation);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Notification.show("Problème : 86 : realisation :" + ex);
        }
    }

    public static realisation getDuree(int id_type_operation, Connection con) throws SQLException {
        List<realisation> liste = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select * from realise_bof where duree = (select MIN(duree) from realise_bof where id_type_operation = ?)")) {
            pst.setInt(1, id_type_operation);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_realisation = rs.getInt("id_realisation");
                    float duree = rs.getFloat("duree");
                    int id_type = rs.getInt("id_type_operation");
                    int id_machine = rs.getInt("id_machine");
                    liste.add(new realisation(id_realisation, duree, id_type_operation, id_machine));
                }
            }
        }
        return liste.get(0);
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
    }

    public float getDuree() {
        return duree;
    }

    public int getId_type_operation() {
        return id_type_operation;
    }

    public int getId_machine() {
        return id_machine;
    }

    public void setId_realisation(int id_realisation) {
        this.id_realisation = id_realisation;
    }

    public int getId_realisation() {
        return id_realisation;
    }
}
