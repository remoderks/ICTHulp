package com.icthulp.DorpsgenotenInfo;

import com.icthulp.DatabaseHandler;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DorpsgenootUpdater {
    public void updateDorpsgenoot(String dorpsgenoot, String geboortedatum, String email, String besturingssysteem, String telefoonnummer, String postcode, String huisnummer, String aanwezigheidsdatum) {
        String query = "UPDATE dorpsgenotenInfo SET dorpsgenoot = ?, geboortedatum = ?, email = ?, besturingssysteem = ?, telefoonnummer = ?, postcode = ?, huisnummer = ?, aanwezigheidsdatum = ? WHERE dorpsgenoot = ?";
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, dorpsgenoot);
            pstmt.setString(2, geboortedatum);
            pstmt.setString(3, email);
            pstmt.setString(4, besturingssysteem);
            pstmt.setString(5, telefoonnummer);
            pstmt.setString(6, postcode);
            pstmt.setString(7, huisnummer);
            pstmt.setString(8, aanwezigheidsdatum);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update dorpsgenoot.");
        }


    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

