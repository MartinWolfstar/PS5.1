/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import static fr.insa.binder.projets5.mavenproject1.Precede.tousLesOrdreOperations_produit;
import static fr.insa.binder.projets5.mavenproject1.exemplaire.get_ex;
import static fr.insa.binder.projets5.mavenproject1.operateur.tousLesOperateur;
import static fr.insa.binder.projets5.mavenproject1.realisation.getDuree;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author binde
 */
public class operation_effectuee {

    private int id_operation;
    private int id_exemplaire;
    private int id_machine;

    private operation_effectuee(int id_operation, int id_exemplaire, int id_machine) {
        this.id_operation = id_operation;
        this.id_exemplaire = id_exemplaire;
        this.id_machine = id_machine;
    }

    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into operations_effectuees_bof (id_operation, id_exemplaire, id_machine) values (?,?,?)")) {
            pst.setInt(1, this.id_operation);
            pst.setInt(2, this.id_exemplaire);
            pst.setInt(3, this.id_machine);
            pst.executeUpdate();
            
        }
    }

    public void sup_operation_effectuee_op(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_operation = ?")) {
            pst.setInt(1, this.id_operation);
            pst.executeUpdate();
        }
    }

    public void sup_operation_effectuee_ex(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_exemplaire = ?")) {
            pst.setInt(1, this.id_operation);
            pst.executeUpdate();
        }
    }

    public void sup_operation_effectuee_ma(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_machine = ?")) {
            pst.setInt(1, this.id_operation);
            pst.executeUpdate();
        }
    }

    public static void sup_operation_effectuee_op(Connection con, int id_operation) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_operation = ?")) {
            pst.setInt(1, id_operation);
            pst.executeUpdate();
        }
    }

    public static List<operation_effectuee> tous_les_operation_effectuees(Connection con) throws SQLException {
        List<operation_effectuee> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operation, id_machine, id_exemplaire from operations_effectuees_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation = rs.getInt("id_operation");
                    int id_machine = rs.getInt("id_machine");
                    int id_exemplaire = rs.getInt("id_exemplaire");
                    res.add(new operation_effectuee(id_operation, id_exemplaire, id_machine));
                }
            }
        }
        return res;
    }

    public static List<operation_effectuee> Meilleurs_operation_produit(Connection con, exemplaire exempl) throws SQLException {
        List<List<Operation>> liste = tousLesOrdreOperations_produit(con, exempl.getId_produit());
        List<operation_effectuee> meilleur_liste = new ArrayList<>();
        Operation operation = liste.get(0).get(0);
        float min = getDuree(operation.getId_typeOperation(), con).getDuree();
        System.out.print(min);
        for (List<Operation> liste_op : liste) {
            float duree = 0;
            List<operation_effectuee> operation_eff = new ArrayList<>();
            for (Operation op : liste_op) {
                realisation realise = getDuree(op.getId_typeOperation(), con);
                duree = duree + realise.getDuree();
                operation_eff.add(new operation_effectuee(op.getId_operation(), exempl.getId_exemplaire(), realise.getId_machine()));
                System.out.println(duree);
            }
            if (duree < min) {
                min = duree;
                System.out.print("Nouveau min" + min);

                meilleur_liste = operation_eff;
            }
        }
        return meilleur_liste;
    }

    @Override
    public String toString() {

        return "operation_effectuee" + "id_operation=" + getId_operation() + ", id_exemplaire=" + getId_exemplaire() + ", id_machine=" + getId_machine() + '}';
    }

//     public static void set_id_operation(int id_operation, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "updatoperation_effectuee_bof set reoperation_effectuee = ? where ioperation_effectuee = ?")) {
//            pst.setInt(1, id_exemplaire);
//            pst.setInt(2, id_operation);            
//            pst.executeUpdate();
//        }
//    }
//    
//
//    public static void set_id_exemplaire(int id_exemplaire, int id_operation, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "updatoperation_effectuee_bof set reoperation_effectuee = ? where ioperation_effectuee = ?")) {
//            pst.setInt(1, id_exemplaire);
//            pst.setInt(2, id_operation);            
//            pst.executeUpdate();
//        }
//    }
//    public static void set_id_machine(String des, int id_operation, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "updatoperation_effectuee_bof set deoperation_effectuee = ? where ioperation_effectuee = ?")) {
//            pst.setString(1, des);
//            pst.setInt(2, id_operation);            
//            pst.executeUpdate();
//        }
//    }
    public int getId_operation() {
        return id_operation;
    }

    public void setId_operation(int id_operation) {
        this.id_operation = id_operation;
    }

    public int getId_exemplaire() {
        return id_exemplaire;
    }

    public void setId_exemplaire(int id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public int getId_machine() {
        return id_machine;
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
    }

    public static void main(String[] args) throws SQLException {
        try {
            Connection con = connectSurServeurM3();
            List<operation_effectuee> liset_c = Meilleurs_operation_produit(con, get_ex(con, 1));
            System.out.println(liset_c + "Salut");
//           rOperateur.saveInDBV(connectSurServeurM3());
        } catch (SQLException ex) {
            throw new Error(ex);
        }
    }

}
