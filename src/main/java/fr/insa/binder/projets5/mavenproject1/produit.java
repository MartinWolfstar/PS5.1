/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import com.vaadin.flow.component.html.Image;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import java.io.Serializable;
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
public class produit implements Serializable{
    
    private int id_p;   
    private int ref_p;
    private String des_p;
    private Image image;
    
    public produit(int id_p, String des_p, int ref_p) {
        this.id_p = id_p;
        this.ref_p = ref_p;
        this.des_p = des_p;
        this.image = new Image("images/chat.jpg", "image");
    }
    public produit(int id_p, String des_p, int ref_p, Image img) {
        this.id_p = id_p;
        this.ref_p = ref_p;
        this.des_p = des_p;
        this.image = new Image("images/chat.jpg", "image");
    }
    
    public produit(String des_p, int ref_p) {
        this(-1, des_p, ref_p, new Image("images/chat.jpg", "Pas de semoule pour Théo"));
    }
    
    public static produit demande() {
        int ref_p = ConsoleFdB.entreeInt("ref_p : ");
        String des_p = ConsoleFdB.entreeString("des_p : ");
        return new produit(des_p, ref_p);
    }
    //salut
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into produit_bof (ref_produit,des_produit) values (?,?)")) {
            pst.setInt(1, this.ref_p);
            pst.setString(2, this.des_p);
            pst.executeUpdate();
        }
    } 
    
    public void supProduit(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from produit_bof where id_produit = ?")) {
            pst.setInt(1, this.id_p);
            pst.executeUpdate();
        }
    }
    
    public static void supProduit(Connection con, int id_p) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from produit_bof where id_produit = ?")) {
            pst.setInt(1, id_p);
            pst.executeUpdate();
        }
    }
    
    public static List<produit> tousLesProduits(Connection con) throws SQLException {
        List<produit> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_produit,des_produit,ref_produit from produit_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_p = rs.getInt("id_produit");
                    String des_p = rs.getString("des_produit");
                    int ref_p = rs.getInt("ref_produit");
                    res.add(new produit(id_p, des_p, ref_p));
                }
            }
        }
        return res;
    }
    
    public static List<produit> tousLesProduitsrecherche(String mot, Connection con) throws SQLException {
        List<produit> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_produit,des_produit,ref_produit from produit_bof where des_produit LIKE ?")) {
                pst.setString(1,mot);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_p = rs.getInt("id_produit");
                    String des_p = rs.getString("des_produit");
                    int ref_p = rs.getInt("ref_produit");
                    res.add(new produit(id_p, des_p, ref_p));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_p=" + getId() + ", ref_p=" + getRef() + ", des_p=" + getDes() + '}';
    }

    public void setRef(int ref_p) {
        this.ref_p = ref_p;
    }

    public static void setRef(int ref_p, int id_p, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update produit_bof set ref_produit = ? where id_produit = ?")) {
            pst.setInt(1, ref_p);
            pst.setInt(2, id_p);            
            pst.executeUpdate();
        }
    }
    
    public static void setDes(String des_p, int id_p, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update produit_bof set des_produit = ? where id_produit = ?")) {
            pst.setString(1, des_p);
            pst.setInt(2, id_p);            
            pst.executeUpdate();
        }
    }
    
    
    public void setDes(String des_p) {
        this.des_p = des_p;
    }

//    public static void setDes(String des_p, int id_p, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "update produit_bof set des_produit = ? where id_produit = ?")) {
//            pst.setString(1, des_p);
//            pst.setInt(2, id_p);            
//            pst.executeUpdate();
//        }
//    }
//    
    public void setId(int id_p) {
        this.id_p = id_p;
    }

    public int getRef() {
        return ref_p;
    }

    public String getDes() {
        return des_p;
    }

    public int getId() {
        return id_p;
    }
    
        public static void main(String[] args) throws SQLException {
        produit p = new produit(1, "Theo", 1);
        produit p1 = new produit(1, "Aurore", 122);
        try {
            Connection con = connectSurServeurM3();
            p.saveInDBV1(con);
            p1.saveInDBV1(con);
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
        

}
