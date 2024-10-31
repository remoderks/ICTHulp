package com.icthulp.DorpsgenotenInfo;

import com.icthulp.DatabaseHandler;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DorpsgenootDeleter {

    public void deleteDorpsgenotenFromDatabase(String item) {
        // Delete SQL statement for the dorpsgenotenInfo table
        String query = "DELETE FROM dorpsgenotenInfo WHERE dorpsgenoot = ?";
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item);
            pstmt.executeUpdate();
            // success alert
            showAlert("Succes", "Dorpsgenoot deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
            // error alert
            showAlert("Error", "Failed to delete dorpsgenoot.");
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