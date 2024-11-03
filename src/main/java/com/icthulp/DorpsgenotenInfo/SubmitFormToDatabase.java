package com.icthulp.DorpsgenotenInfo;

import com.icthulp.DatabaseHandler;

import java.sql.*;
import java.time.LocalDate;

public class SubmitFormToDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ICTHulp";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Gq831px9!";

    public static void submitForm(String dorpsgenoot, LocalDate geboortedatum, String postcode, String huisnummer,
                                  String email, String telefoonnummer, String besturingssysteem, LocalDate aanwezigheidsdatum) {
        // Insert SQL statements, first the parent tables are updated. If the value already exists, it will be updated.
        // For the table dorpsgenotenInfo new values are inserted.
        String insertParentSQLGeboortedatums = "INSERT INTO geboortedatums (geboortedatum) VALUES (?) ON DUPLICATE KEY UPDATE geboortedatum = VALUES(geboortedatum)";
        String insertParentSQLAanwezigheidsdatums = "INSERT INTO aanwezigheidsdatums (aanwezigheidsdatum) VALUES (?) ON DUPLICATE KEY UPDATE aanwezigheidsdatum = VALUES(aanwezigheidsdatum)";
        String insertParentSQLBesturingssystemen = "INSERT INTO besturingssystemen (besturingssysteem) VALUES (?) ON DUPLICATE KEY UPDATE besturingssysteem = VALUES(besturingssysteem)";
        String insertSQL = "INSERT INTO dorpsgenotenInfo (dorpsgenoot, geboortedatum, postcode, huisnummer, email, telefoonnummer, besturingssysteem, aanwezigheidsdatum) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseHandler.getConnection()) {
            // Start transaction
            conn.setAutoCommit(false);

            // Insert or update parent table geboortedatums
            try (PreparedStatement pstmtParent = conn.prepareStatement(insertParentSQLGeboortedatums)) {
                pstmtParent.setString(1, geboortedatum.toString());
                pstmtParent.executeUpdate();
            }

            // Insert or update parent table aanwezigheidsdatums
            try (PreparedStatement pstmtParent = conn.prepareStatement(insertParentSQLAanwezigheidsdatums)) {
                pstmtParent.setString(1, aanwezigheidsdatum.toString());
                pstmtParent.executeUpdate();
            }

            // Insert or update parent table aanwezigheidsdatums
            try (PreparedStatement pstmtParent = conn.prepareStatement(insertParentSQLBesturingssystemen)) {
                pstmtParent.setString(1, besturingssysteem.toString());
                pstmtParent.executeUpdate();
            }

            // Insert into child table dorpsgenotenInfo
            try (PreparedStatement pstmtChild = conn.prepareStatement(insertSQL)) {
                pstmtChild.setString(1, dorpsgenoot);
                pstmtChild.setString(2, geboortedatum.toString());
                pstmtChild.setString(3, postcode);
                pstmtChild.setString(4, huisnummer);
                pstmtChild.setString(5, email);
                pstmtChild.setString(6, telefoonnummer);
                pstmtChild.setString(7, besturingssysteem);
                pstmtChild.setString(8, aanwezigheidsdatum.toString());
                pstmtChild.executeUpdate();
            }

            // Commit transaction
            conn.commit();

        } catch (SQLException ex) {
            System.out.println("An error occurred while inserting into the database");
            ex.printStackTrace();
        }
    }
}