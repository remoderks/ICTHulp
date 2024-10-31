package com.icthulp.DorpsgenotenInfo;

import com.icthulp.DatabaseHandler;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DorpsgenootUpdater {
    public void updateDorpsgenoot(String dorpsgenoot, String geboortedatum, String email, String besturingssysteem, String telefoonnummer, String postcode, String huisnummer, String aanwezigheidsdatum, String oldDorpsgenoot) throws SQLException {
        // Update SQL statements, first the parent tables are updated. If the value already exists, it will be updated.
        // For the child table, the dorpsgenoot is updated with the new values.
        // The old dorpsgenoot name is used to identify the person to update.
        // INSERT into is used for the parent tables so that changes can be made to the existing values due to the foreign key constraint.
        String updateParentSQLGeboortedatums = "INSERT INTO geboortedatums (geboortedatum) VALUES (?) ON DUPLICATE KEY UPDATE geboortedatum = VALUES(geboortedatum)";
        String updateParentSQLAanwezigheidsdatums = "INSERT INTO aanwezigheidsdatums (aanwezigheidsdatum) VALUES (?) ON DUPLICATE KEY UPDATE aanwezigheidsdatum = VALUES(aanwezigheidsdatum)";
        String updateParentSQLBesturingssystemen = "INSERT INTO besturingssystemen (besturingssysteem) VALUES (?) ON DUPLICATE KEY UPDATE besturingssysteem = VALUES(besturingssysteem)";
        String updateSQLDorpsgenotenInfo = "UPDATE dorpsgenotenInfo SET dorpsgenoot = ?, geboortedatum = ?, email = ?, besturingssysteem = ?, telefoonnummer = ?, postcode = ?, huisnummer = ?, aanwezigheidsdatum = ? WHERE dorpsgenoot = ?";

        try (Connection conn = DatabaseHandler.getConnection()) {
            // Start transaction
            conn.setAutoCommit(false);

            // Update geboortedatums table
            try (PreparedStatement pstmtGeboortedatums = conn.prepareStatement(updateParentSQLGeboortedatums)) {
                pstmtGeboortedatums.setString(1, geboortedatum); // new value
                pstmtGeboortedatums.executeUpdate();
            }

            // Update aanwezigheidsdatums table
            try (PreparedStatement pstmtAanwezigheidsdatums = conn.prepareStatement(updateParentSQLAanwezigheidsdatums)) {
                pstmtAanwezigheidsdatums.setString(1, aanwezigheidsdatum); // new value
                pstmtAanwezigheidsdatums.executeUpdate();
            }

            // Update besturingssystemen table
            try (PreparedStatement pstmtBesturingssystemen = conn.prepareStatement(updateParentSQLBesturingssystemen)) {
                pstmtBesturingssystemen.setString(1, besturingssysteem); // new value
                pstmtBesturingssystemen.executeUpdate();
            }

            // Update dorpsgenotenInfo table
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQLDorpsgenotenInfo)) {
                pstmt.setString(1, dorpsgenoot); // Updated name
                pstmt.setString(2, geboortedatum); // Updated geboortedatum
                pstmt.setString(3, email); // ...
                pstmt.setString(4, besturingssysteem); // ...
                pstmt.setString(5, telefoonnummer);
                pstmt.setString(6, postcode);
                pstmt.setString(7, huisnummer);
                pstmt.setString(8, aanwezigheidsdatum);
                pstmt.setString(9, oldDorpsgenoot);  // old dorpsgenoot name to identify the person to update
                pstmt.executeUpdate();

                // Commit transaction
                conn.commit();
                // show if successful
                showAlert("Success", "Dorpsgenoot information updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to update dorpsgenootinformatie.");
                // Rollback in case of an error
                conn.rollback();
            }
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
