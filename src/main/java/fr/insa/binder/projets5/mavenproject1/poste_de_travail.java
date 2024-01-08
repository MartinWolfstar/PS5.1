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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author melan
 */
public class poste_de_travail {
   private int id_poste_de_travail;
   private String ref_poste_de_travail; 
   private int x1;
   private int x2;
   private int y1;
   private int y2;

    public poste_de_travail(int id_poste_de_travail, String ref_poste_de_travail) {
        this.id_poste_de_travail = id_poste_de_travail;
        this.ref_poste_de_travail = ref_poste_de_travail;
    }
    public poste_de_travail(int id_poste_de_travail, String ref_poste_de_travail, int x1, int x2, int y1, int y2) {
        this.id_poste_de_travail = id_poste_de_travail;
        this.ref_poste_de_travail = ref_poste_de_travail;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    public poste_de_travail(String ref_poste_de_travail) {
        this(-1, ref_poste_de_travail);
    }
    public poste_de_travail(String ref_poste_de_travail, int x1, int x2, int y1, int y2) {
        this(-1, ref_poste_de_travail,x1,x2,y1,y2);
    }
    
    
    public void save_poste_de_travail(Connection conn) throws SQLException{
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into poste_de_travail_bof (ref_poste_de_travail,x1,x2,y1,y2) values (?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.ref_poste_de_travail);
            pst.setInt(2, this.x1);
            pst.setInt(3, this.x2);
            pst.setInt(4, this.y1);
            pst.setInt(5, this.y2);
            pst.executeUpdate();
            try (ResultSet ids = pst.getGeneratedKeys()) {
                if (ids.next()) {
                    this.id_poste_de_travail = ids.getInt(1);
                }
            }
        } 
    }
    
    public static List<poste_de_travail> tousLesPostes(Connection con) throws SQLException {
        List<poste_de_travail> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_poste_de_travail,ref_poste_de_travail from poste_de_travail_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_poste_de_travail = rs.getInt("id_poste_de_travail");
                    String ref_poste_de_travail = rs.getString("ref_poste_de_travail");
                    res.add(new poste_de_travail(id_poste_de_travail, ref_poste_de_travail));
                }
            }
        }
        return res;
    }
    public static List<String> tousLesPostes_String(Connection con) throws SQLException {
        List<String> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select ref_poste_de_travail from poste_de_travail_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String ref_poste_de_travail = rs.getString("ref_poste_de_travail");
                    res.add(ref_poste_de_travail);
                }
            }
        }catch (SQLException ex){
            Notification.show("Problème BdD : poste_de_travail" + ex);
        }
        return res;
    }
    
    public static boolean IsIn(int x, int y, Connection con) {
        List<String> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "SELECT ref_poste_de_travail FROM poste_de_travail_bof WHERE x1 < ? AND ? < x2 AND y1 < ? AND ? < y2")) {
            pst.setInt(1, x);
            pst.setInt(2, x);
            pst.setInt(3, y);
            pst.setInt(4, y);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String ref_poste_de_travail = rs.getString("ref_poste_de_travail");
                    res.add(ref_poste_de_travail);
                }
            }catch (SQLException ex) {
                Notification.show("Problème BdD : poste_de_travail 3" + ex);
        }
        } catch (SQLException ex) {
            Notification.show("Problème BdD : poste_de_travail 4" + ex);
        }

        return !res.isEmpty();
    }
    
    public static List<Integer> getAll(int x, int y, Connection con) {
    List<Integer> res = new ArrayList<>();
    try (PreparedStatement pst = con.prepareStatement(
            "SELECT id_poste_de_travail FROM poste_de_travail_bof WHERE x1 < ? AND ? < x2 AND y1 < ? AND ? < y2")) {
        pst.setInt(1, x);
        pst.setInt(2, x);
        pst.setInt(3, y);
        pst.setInt(4, y);

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int ref_poste_de_travail = rs.getInt("id_poste_de_travail");
                res.add(ref_poste_de_travail);
            }
        } catch (SQLException ex) {
            Notification.show("Problème BdD : poste_de_travail 3" + ex.getMessage());
        }
    } catch (SQLException ex) {
        Notification.show("Problème BdD : poste_de_travail 4" + ex.getMessage());
    }

    return res;
}

    
    public static void sup(int x, int y, Connection con) {
        try (PreparedStatement pst = con.prepareStatement(
                "DELETE FROM poste_de_travail_bof WHERE x1 < ? AND ? < x2 AND y1 < ? AND ? < y2")) {
            pst.setInt(1, x);
            pst.setInt(2, x);
            pst.setInt(3, y);
            pst.setInt(4, y);
            pst.executeUpdate();
            Notification.show("supprimer le pdt");
            Notification.show(String.valueOf(x) + " " + String.valueOf(y));
        } catch (SQLException ex) {
            Notification.show("Problème BdD : poste_de_travail 6" + ex);
        }
    }

//   public static List<Integer> getCoordonneesPostesTravail(Connection con) {
//        List<Integer> coordonneesList = new ArrayList<>();
//
//        try (PreparedStatement pst = con.prepareStatement(
//                "SELECT x1, y1, x2, y2 FROM poste_de_travail_bof")) {
//            try (ResultSet rs = pst.executeQuery()) {
//                while (rs.next()) {
//                    int x1 = rs.getInt("x1");
//                    int y1 = rs.getInt("y1");
//                    int x2 = rs.getInt("x2");
//                    int y2 = rs.getInt("y2");
//                    
//                    // Ajouter les coordonnées à la liste
//                    coordonneesList.add(x1);
//                    coordonneesList.add(x2);
//                    coordonneesList.add(y1);
//                    coordonneesList.add(y2);
//                }
//            }
//        } catch (SQLException ex) {
//            Notification.show("Problème BdD : poste_de_travail7" + ex);
//        }
//
//        return coordonneesList;
//    } 
    
    public static Map<String, List<Integer>> getCoordonneesEtNomPostesTravail(Connection con) {
        Map<String, List<Integer>> coordonneesMap = new HashMap<>();

        try (PreparedStatement pst = con.prepareStatement(
                "SELECT ref_poste_de_travail, x1, y1, x2, y2 FROM poste_de_travail_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String refPosteDeTravail = rs.getString("ref_poste_de_travail");
                    int x1 = rs.getInt("x1");
                    int y1 = rs.getInt("y1");
                    int x2 = rs.getInt("x2");
                    int y2 = rs.getInt("y2");

                    // Ajouter les coordonnées à la liste
                    List<Integer> coordonneesList = new ArrayList<>();
                    coordonneesList.add(x1);
                    coordonneesList.add(x2);
                    coordonneesList.add(y1);
                    coordonneesList.add(y2);

                    // Ajouter la référence du poste de travail et les coordonnées à la map
                    coordonneesMap.put(refPosteDeTravail, coordonneesList);
                }
            }
        } catch (SQLException ex) {
            Notification.show("Problème BdD : poste_de_travail7" + ex);
        }

        return coordonneesMap;
    }

    
    
    public int getId_poste_de_travail() {
        return id_poste_de_travail;
    }
    public static int getId_poste_de_travail(String ref, Connection con) throws SQLException {
        int id = 0;
        try (PreparedStatement pst = con.prepareStatement(
                "select id_poste_de_travail from poste_de_travail_bof where ref_poste_de_travail = ?")) {
            pst.setString(1, ref);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id_poste_de_travail");
                }
            }
        }
        return id;
    }

    public String getRef_poste_de_travail() {
        return ref_poste_de_travail;
    }
    
    public static String getRef_poste_de_travail(int Id, Connection con) throws SQLException {
        String pt = "Erreur1";
        try (PreparedStatement pst = con.prepareStatement(
                "select ref_poste_de_travail from poste_de_travail_bof where id_poste_de_travail = ?")) {
            pst.setInt(1, Id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    pt = rs.getString("ref_poste_de_travail");
                }
            }
            catch(SQLException ex){
                Notification.show("Problème BdD : getRef_poste_de_travail 1");
            }
        }
        catch(SQLException ex){
            Notification.show("Problème BdD : getRef_poste_de_travail");
        }
        return pt;
    }

    public void setRef_poste_de_travail(String ref_poste_de_travail) {
        this.ref_poste_de_travail = ref_poste_de_travail;
    }

    public void setId_poste_de_travail(int id_poste_de_travail) {
        this.id_poste_de_travail = id_poste_de_travail;
    }

    @Override
    public String toString() {
        return "poste_de_travail{" + "id_poste_de_travail=" + id_poste_de_travail + ", ref_poste_de_travail=" + ref_poste_de_travail +'}';
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }



   
}
