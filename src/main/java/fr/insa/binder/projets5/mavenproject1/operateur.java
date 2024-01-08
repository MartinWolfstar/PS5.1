/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;


import com.vaadin.flow.component.notification.Notification;
import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                "insert into operateur_bof (nom_operateur,prenom_operateur,login_operateur,password_operateur) values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.getNom_operateur());
            pst.setString(2, this.getPrenom_operateur());
            pst.setString(3, this.getLogin_operateur());
            pst.setString(4, this.getPassword_operateur());
            pst.executeUpdate();
            try (ResultSet ids = pst.getGeneratedKeys()) {
                ids.next();
                this.id_operateur = ids.getInt(1);
            }

        }
    }

    @Override
    public String toString() {
        return "operateur{" + "id_operateur=" + id_operateur + ", nom_operateur=" + nom_operateur + ", prenom_operateur=" + prenom_operateur + ", login_operateur=" + login_operateur + ", password_operateur=" + password_operateur + '}';
    }
    
        public void sup_operateur(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operateur_bof where id = ?")) {
            pst.setInt(1, this.id_operateur);
            pst.executeUpdate();
        }
    }
    
    public static void sup_operateur(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operateur_bof where id_operateur = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
    public static List<operateur> tousLesOperateur(Connection con) throws SQLException {
        List<operateur> liste = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operateur,nom_operateur, prenom_operateur, login_operateur, password_operateur from operateur_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_operateur");
                    String nom = rs.getString("nom_operateur");
                    String prenom = rs.getString("prenom_operateur");
                    String login = rs.getString("login_operateur");
                    String mdp = rs.getString("password_operateur");
                    liste.add(new operateur(id, nom, prenom, login, mdp));
                }
            }
        }
        return liste;
    }

    public static Optional<Integer> login_o(Connection con, String nom, String pass) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operateur from operateur_bof where login_operateur = ? and password_operateur = ?")) {

            pst.setString(1, nom);
            pst.setString(2, pass);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return Optional.of(res.getInt("id_operateur"));
            } else {
                return Optional.empty();
            }
        }
    }
    
    public static List<operateur> tousLesOperateursByPosteDeTravail(int id_poste_de_travail, Connection con) throws SQLException {
    List<operateur> res = new ArrayList<>();
    try (PreparedStatement pst = con.prepareStatement(
            "SELECT o.id_operateur, o.nom_operateur, o.prenom_operateur, o.login_operateur, o.password_operateur " +
            "FROM operateur_bof o " +
            "JOIN operations__poste_de_travail_bof op ON o.id_operateur = op.id_operateur " +
            "WHERE op.id_poste_de_travail = ?")) {
        pst.setInt(1, id_poste_de_travail);

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int id_operateur = rs.getInt("id_operateur");
                String nom = rs.getString("nom_operateur");
                String prenom = rs.getString("prenom_operateur");
                String login = rs.getString("login_operateur");
                String password = rs.getString("password_operateur");

                res.add(new operateur(id_operateur, nom, prenom, login, password));
            }
        } catch (SQLException ex) {
            Notification.show("Problème BdD : operateur_poste_de_travail 1" + ex.getMessage());
        }
    } catch (SQLException ex) {
        Notification.show("Problème BdD : operateur_poste_de_travail 2" + ex.getMessage());
    }
    return res;
}

 
    public static void setPrenom(String prenom, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update operateur_bof set nom_operateur = ? where id_operateur = ?")) {
            pst.setString(1, prenom);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    
    public static void setNom(String nom, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update operateur_bof set prenom_operateur = ? where id_operateur = ?")) {
            pst.setString(1, nom);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    
    public static void setLogin(String login, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update operateur_bof set login_operateur = ? where id_operateur = ?")) {
            pst.setString(1, login);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }

    public static void setPassword(String mdp, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update operateur_bof set password_operateur = ? where id_operateur = ?")) {
            pst.setString(1, mdp);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
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

        public static String getnom_operateur(int id, Connection con) throws SQLException{
        String nom = "Personne";
        String prenom = "Personne";
        try (PreparedStatement pst = con.prepareStatement(
                "select nom_operateur, prenom_operateur"
                + " from operateur_bof "
                + " where id_operateur = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    nom = rs.getString("nom_operateur");
                    prenom = rs.getString("prenom_operateur");
                }
            }
        }
        return nom + " " + prenom;
    }
     
        public static void main(String[] args) throws SQLException {
        operateur op = new operateur("Theo", "Stupide", "Idiot", "Aurore");
        try {
            Connection con = connectSurServeurM3();
            op.save_operateur(con);
            List<operateur> liset_c = tousLesOperateur(con);
            System.out.println(liset_c);
//           rOperateur.saveInDBV(connectSurServeurM3());
        }
        catch (SQLException ex) {
            throw new Error(ex);
        }
    }
}
