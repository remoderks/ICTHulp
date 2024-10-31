package com.icthulp.DorpsgenotenInfo;

import com.icthulp.DatabaseHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DorpsgenotenInfo {
    private ListView<String> aanwezigenListView;
    private DorpsgenootDeleter dorpsgenootDeleter = new DorpsgenootDeleter();
    private DorpsgenootUpdater dorpsgenootUpdater = new DorpsgenootUpdater();

    public DorpsgenotenInfo(Pane root) {
        // Create a VBox to hold the header, ListView, and delete button
        VBox vbox = new VBox();
        // Create a header label
        Label header = new Label("Aanmeldingen");
        // Initialize the ListView
        aanwezigenListView = new ListView<>();
        aanwezigenListView.setPrefSize(1200, 800);
        // Load data from the database
        loadDorpsgenoten();
        // Create a delete button
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");
        // Set the action for the delete button
        deleteButton.setOnAction(event -> {
            String selectedItem = aanwezigenListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Remove the selected item from the ListView
                aanwezigenListView.getItems().remove(selectedItem);
                // Call a method to delete the item from the database
                String [] parts = selectedItem.split(" ");
                if (parts.length > 0) {
                    String dorpsgenoot = parts [0];
                    dorpsgenootDeleter.deleteDorpsgenotenFromDatabase(dorpsgenoot);
                }
            }
        });
        updateButton.setOnAction(event -> {
            // Try and catch has to be done here because of the SQLException. This is because the updateDorpsgenootbutton method throws a SQLException.
            updateDorpsgenootbutton();
        });
        // Add the header, ListView, and delete button to the VBox
        vbox.getChildren().addAll(header, aanwezigenListView, deleteButton, updateButton);
        root.getChildren().add(vbox); // Add the VBox to the root pane
    }

    // Load patients from database into the listview.
    private void loadDorpsgenoten() {
        // SQL query to select all dorpsgenoten
        String query = "SELECT dorpsgenoot, geboortedatum, email, besturingssysteem, telefoonnummer, postcode, huisnummer, aanwezigheidsdatum FROM dorpsgenotenInfo";
        aanwezigenListView.getItems().clear(); // Clear existing items
        try (Connection conn = DatabaseHandler.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String dorpsgenoot = rs.getString("dorpsgenoot");
                String geboortedatum = rs.getString("geboortedatum");
                String email = rs.getString("email");
                String besturingssysteem = rs.getString("besturingssysteem");
                String telefoonnummer = rs.getString("telefoonnummer");
                String postcode = rs.getString("postcode");
                String huisnummer = rs.getString("huisnummer");
                String aanwezigheidsdatum = rs.getString("aanwezigheidsdatum");
                // Add dorpsgenoot details to the ListView
                aanwezigenListView.getItems().add(dorpsgenoot + "," + geboortedatum +  "," + email + "," +  besturingssysteem + "," +  telefoonnummer + "," +  postcode + "," +  huisnummer + "," +  aanwezigheidsdatum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load dorpsgenoten.");
        }
    }

    private void updateDorpsgenootbutton() {
        // Update the selected dorpsgenoot in the database
        String selectedItem = aanwezigenListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Extract the dorpsgenoot's name for deletion
            String[] parts = selectedItem.split(",");
            if (parts.length >= 8) {
                String dorpsgenoot = parts[0];
                String geboortedatum = parts[1];
                String email = parts[2];
                String besturingssysteem = parts[3];
                String telefoonnummer = parts[4];
                String postcode = parts[5];
                String huisnummer = parts[6];
                String aanwezigheidsdatum = parts[7];

                // create a dialog popup to update the dorpsgenoot
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Update Dorpsgenoot");
                dialog.setHeaderText("Update Dorpsgenoot");

                // setup dialog content
                Label nameLabel = new Label("Naam:");
                TextField nameField = new TextField(dorpsgenoot);
                Label geboortedatumLabel = new Label("Geboortedatum:");
                DatePicker geboortedatumField = new DatePicker(LocalDate.parse(geboortedatum));
                Label emailLabel = new Label("Email:");
                TextField emailField = new TextField(email);
                // Besturingssysteem combobox (4 options to choose from)
                Label besturingssysteemLabel = new Label("Besturingssysteem:");
                ComboBox<String> besturingssysteemBox = new ComboBox<>();
                besturingssysteemBox.getItems().addAll("Windows", "MacOS", "Android", "iOS");
                besturingssysteemBox.setValue(besturingssysteem);
                Label telefoonnummerLabel = new Label("Telefoonnummer:");
                TextField telefoonnummerField = new TextField(telefoonnummer);
                Label postcodeLabel = new Label("Postcode:");
                TextField postcodeField = new TextField(postcode);
                Label huisnummerLabel = new Label("Huisnummer:");
                TextField huisnummerField = new TextField(huisnummer);
                Label aanwezigheidsdatumLabel = new Label("Aanwezigheidsdatum:");
                DatePicker aanwezigheidsdatumField = new DatePicker(LocalDate.parse(aanwezigheidsdatum));

                // GridPane setup to hold the dialog content
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));
                grid.add(nameLabel, 0,0);
                grid.add(nameField, 1,0);
                grid.add(geboortedatumLabel, 0,1);
                grid.add(geboortedatumField, 1,1);
                grid.add(emailLabel, 0,2);
                grid.add(emailField, 1,2);
                grid.add(besturingssysteemLabel, 0,3);
                grid.add(besturingssysteemBox, 1,3);
                grid.add(telefoonnummerLabel, 0,4);
                grid.add(telefoonnummerField, 1,4);
                grid.add(postcodeLabel, 0,5);
                grid.add(postcodeField, 1,5);
                grid.add(huisnummerLabel, 0,6);
                grid.add(huisnummerField, 1,6);
                grid.add(aanwezigheidsdatumLabel, 0,7);
                grid.add(aanwezigheidsdatumField, 1,7);

                dialog.getDialogPane().setContent(grid);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                // Handle dialog result and use the dorpsgenootUpdater using DorpsgenootUpdater.java
                dialog.showAndWait().ifPresent(result -> {
                    if (result == ButtonType.OK) {
                        String updatedDorpsgenoot = nameField.getText();
                        String updatedGeboortedatum = geboortedatumField.getValue().toString();
                        String updatedEmail = emailField.getText();
                        String updatedBesturingssysteem = besturingssysteemBox.getValue();
                        String updatedTelefoonnummer = telefoonnummerField.getText();
                        String updatedPostcode = postcodeField.getText();
                        String updatedHuisnummer = huisnummerField.getText();
                        String updatedAanwezigheidsdatum = aanwezigheidsdatumField.getValue().toString();
                        try {
                            // call the dorpsgenootUpdater to update the dorpsgenoot
                            dorpsgenootUpdater.updateDorpsgenoot(updatedDorpsgenoot, updatedGeboortedatum, updatedEmail, updatedBesturingssysteem, updatedTelefoonnummer, updatedPostcode, updatedHuisnummer, updatedAanwezigheidsdatum, dorpsgenoot);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            showAlert("Error", "Failed to update dorpsgenoot.");
                        }
                    }
                    loadDorpsgenoten();
                });

            }
        }
    }
    private void showAlert (String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}