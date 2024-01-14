package fr.insa.binder.projets5.mavenproject1;

import com.vaadin.flow.component.notification.Notification;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageT {

    private int idImage;
    private String nom;
    private byte[] imageBytes;

    private ImageT(int idImage, String nom, byte[] imageBytes) {
        this.idImage = idImage;
        this.nom = nom;
        this.imageBytes = imageBytes;
    }
    private ImageT() {
        this.idImage = -1;
        this.nom = "";
    }

    public ImageT(String nom, byte[] imageBytes) {
        this(-1,nom,imageBytes);
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public void saveImage(Connection conn) throws SQLException {
        // Vérifier si une image avec le même nom existe déjà
        int existingImageId = getExistingImageId(conn, this.nom);
        //int existingImageId = 1;

        if (existingImageId != -1) {
            deleteImage(conn, existingImageId);
        }

        // Insérer la nouvelle image
        try (PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO ImageT (nom, image) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.nom);
            pst.setBytes(2, this.imageBytes);
            pst.executeUpdate();

            try (ResultSet ids = pst.getGeneratedKeys()) {
                ids.next();
                this.idImage = ids.getInt(1);
            }
        }
    }

    private int getExistingImageId(Connection conn, String nom) throws SQLException {
        int existingImageId = -1;
        try (PreparedStatement pst = conn.prepareStatement("SELECT id_image FROM ImageT WHERE nom = ?")) {
            pst.setString(1, nom);
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    existingImageId = resultSet.getInt("id_image");
                }
            }
        }
        return existingImageId;
    }

    private void deleteImage(Connection conn, int imageId) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("DELETE FROM ImageT WHERE id_image = ?")) {
            pst.setInt(1, imageId);
            pst.executeUpdate();
        }
    }

    public static ImageT getImageByName(Connection conn, String nom) throws SQLException, IOException {
        String sql = "SELECT * FROM ImageT WHERE nom = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nom);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    ImageT image = new ImageT();
                    image.setIdImage(rs.getInt("id_image"));
                    image.setNom(rs.getString("nom"));
                    image.setImageBytes(rs.getBytes("image"));
                    return image;
                }
            }
        }
        return null;
    }
}

