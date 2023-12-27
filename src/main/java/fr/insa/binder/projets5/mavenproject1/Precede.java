/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import static fr.insa.binder.projets5.mavenproject1.Operation.tousLesOperations_produit;
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
public class Precede {

    private int id_operation_1;
    private int id_operation_2;

    public Precede(int id_operation_1, int id_operation_2) {
        this.id_operation_1 = id_operation_1;
        this.id_operation_2 = id_operation_2;
    }

//    public Operation(int id_operation_2, int id_produit) {
//        this(-1, id_operation_2, id_produit);
//    }
//    public static Operation demande() {
//        int id_produit = ConsoleFdB.entreeInt("id_produitroduit : ");
//        int id_operation_2 = ConsoleFdB.entreeInt("id_type_operation : ");
//        return new Operation(id_operation_2, id_produit);
//    }
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into precede_bof (operation_1,operation_2) values (?,?)")) {
            pst.setInt(1, this.id_operation_1);
            pst.setInt(2, this.id_operation_2);
            pst.executeUpdate();
        }
    }

    public void supPrecede(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from precede_bof where id_operation_1 = ?")) {
            pst.setInt(1, this.id_operation_1);
            pst.executeUpdate();
        }
    }

    public static void supPrecede(Connection con, int id_operation_1) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from precede_bof where operation_1 = ?")) {
            pst.setInt(1, id_operation_1);
            pst.executeUpdate();
        }
    }

    public static void supPrecede1(Connection con, int id_operation_1) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from precede_bof where operation_2 = ?")) {
            pst.setInt(1, id_operation_1);
            pst.executeUpdate();
        }
    }

    public static void supPrecede(Connection con, int id_operation_1, int id_operation_2) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from precede_bof where operation_1 = ? and operation_2 = ?")) {
            pst.setInt(1, id_operation_1);
            pst.setInt(2, id_operation_2);
            pst.executeUpdate();
        }
    }

    public static List<Precede> tousLesPrecede(Connection con) throws SQLException {
        List<Precede> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select operation_1, operation_2 from precede_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation_1 = rs.getInt("operation_1");
                    int id_operation_2 = rs.getInt("operation_2");
                    res.add(new Precede(id_operation_1, id_operation_2));
                }
            }
        }
        return res;
    }

    public static List<Precede> tousLesPrecede_operation(Connection con, int id_operation_1) throws SQLException {
        List<Precede> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select operation_2 from precede_bof where operation_1=?")) {
            pst.setInt(1, id_operation_1);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation_2 = rs.getInt("operation_2");
                    res.add(new Precede(id_operation_1, id_operation_2));
                }
            }
        }
        return res;
    }

    public static List<Operation> tousLesPrecede_operation_op(Connection con, int id_operation_1, int id_produit) throws SQLException {
        List<Operation> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select * from operation_bof join precede_bof on id_operation = operation_2 where operation_1=?")) {
            pst.setInt(1, id_operation_1);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation = rs.getInt("id_operation");
                    int id_type = rs.getInt("id_type_operation");
                    res.add(new Operation(id_operation, id_type, id_produit));
                }
            }
        }
        return res;
    }

    public static List<Precede> tousLesPrecede_operation2(Connection con, int id_operation_2) throws SQLException {
        List<Precede> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select * from precede_bof where operation_2=?")) {
            pst.setInt(1, id_operation_2);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation_1 = rs.getInt("operation_1");
                    res.add(new Precede(id_operation_1, id_operation_2));
                }
            }
        }
        return res;
    }

    public static List<List<Operation>> tousLesOrdreOperations_produit(Connection con, int id_produit) throws SQLException {
        List<Operation> op_produit = new ArrayList<>(); // La liste de toutes les operations pour fabiquer ce produit
        op_produit = tousLesOperations_produit(con, id_produit);
        List<List<Operation>> toutes_les_combinaisons = new ArrayList<List<Operation>>(); // La liste de toutes les compinaisons d'operation
        for (Operation op : op_produit) {
            if (tousLesPrecede_operation2(con, op.getId_operation()).isEmpty()) {
                System.out.println(op);
                toutes_les_combinaisons.addAll(tousLesOrdreOperations_produit2(con, op, id_produit));
            }
        }

        return toutes_les_combinaisons;
    }

    public static List<List<Operation>> tousLesOrdreOperations_produit2(Connection con, Operation op, int id_produit) throws SQLException {
        List<List<Operation>> resultats = new ArrayList<>();
        if (tousLesPrecede_operation_op(con, op.getId_operation(), id_produit).isEmpty()) {
            List<Operation> combinaison = new ArrayList<>();
            combinaison.add(op);
            resultats.add(combinaison);
        } else {
            for (Operation oper : tousLesPrecede_operation_op(con, op.getId_operation(), id_produit)) {

                List<List<Operation>> combinaisons_suivantes = tousLesOrdreOperations_produit2(con, oper, id_produit);
                for (List<Operation> combinaison_suivante : combinaisons_suivantes) {
                    List<Operation> nouvelleCombinaison = new ArrayList<>();
                    nouvelleCombinaison.add(op);
                    nouvelleCombinaison.addAll(combinaison_suivante);
                    resultats.add(nouvelleCombinaison);
                }
            }

        }
        return resultats;
    }

    public static int dernier_element(List<Operation> liste) {
        int i = liste.size();
        Operation op = liste.get(i - 1);
        return op.getId_operation();
    }

    public static String liste_to_string(List<Precede> liste) {
        String st = "";
        for (Precede pre : liste) {
            st = st + "  " + String.valueOf(pre.getId_operation_2());
        }
        return st;
    }

//    @Override
//    public String toString() {
//        return "Operation" + "id_operation_1=" + getid_operation_1() + ", id_produit=" + getId_produit() + ", id_operation_2=" + getid_operation_2() + '}';
//    }
//    public static void setProduit(int id_produit, int id_operation_1, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "update operation_bof set id_produit = ? where id_operation_1 = ?")) {
//            pst.setInt(1, id_produit);
//            pst.setInt(2, id_operation_1);            
//            pst.executeUpdate();
//        }
//    }
//
//    public static void setTypeOperation(int id_operation_2, int id_operation_1, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "update operation_bof set id_type_operation = ? where id_operation_1 = ?")) {
//            pst.setInt(1, id_operation_2);
//            pst.setInt(2, id_operation_1);            
//            pst.executeUpdate();
//        }
//    }
    public int getId_operation_1() {
        return id_operation_1;
    }

    public void setId_operation_1(int id_operation_1) {
        this.id_operation_1 = id_operation_1;
    }

    public int getId_operation_2() {
        return id_operation_2;
    }

    public void setId_operation_2(int id_operation_2) {
        this.id_operation_2 = id_operation_2;
    }
    
    public static void main(String[] args) throws SQLException {
        try {
            Connection con = connectSurServeurM3();
            List<List<Operation>> liste = tousLesOrdreOperations_produit(con, 1);
//            client.saveInDBV(con);
//            List<Client> liset_c = tousLesClients(con);
//            List<Integer> liste = condition(con);
            System.out.println(liste);
//            System.out.println(liset_c);
//            client.saveInDBV(connectSurServeurM3());
        }
        catch (SQLException ex) {
            throw new Error(ex);
        }
    }

}
