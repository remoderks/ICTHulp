package com.icthulp.AanmeldenPage;

import com.icthulp.DorpsgenotenInfo.SubmitFormToDatabase;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class AanmeldenPage {
    private Button submitButton = new Button("Versturen");

    // Sets the layout of the screen
    public AanmeldenPage(Pane p) {
        VBox layout = new VBox();
        layout.setSpacing(10);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        // Labels and Input Controls for the form
        TextField dorpsgenootField = new TextField();
        DatePicker geboortedatumPicker = new DatePicker();  // DatePicker for Geboortedatum
        TextField postcodeField = new TextField();
        TextField huisnummerField = new TextField();
        TextField emailField = new TextField();
        TextField telefoonnummerField = new TextField();
        // Besturingssysteem combobox (4 options to choose from)
        ComboBox<String> besturingssysteemBox = new ComboBox<>();
        besturingssysteemBox.getItems().addAll("Windows", "MacOS", "Android", "iOS");
        // Aanwezigheidsdatum with ComboBox (2 options to choose from)
        DatePicker datumPicker = new DatePicker();
        // Adds all the Labels to the grid (i.e. the screen)
        grid.add(new Label("Naam"), 0, 0);
        grid.add(dorpsgenootField, 1, 0);

        grid.add(new Label("Geboortedatum"), 0, 1);
        grid.add(geboortedatumPicker, 1, 1);

        grid.add(new Label("Postcode"), 0, 2);
        grid.add(postcodeField, 1, 2);
        grid.add(new Label("Huisnummer"), 2, 2);
        grid.add(huisnummerField, 3, 2);

        grid.add(new Label("Email"), 0, 3);
        grid.add(emailField, 1, 3);

        grid.add(new Label("Telefoonnummer"), 0, 4);
        grid.add(telefoonnummerField, 1, 4);

        grid.add(new Label("Besturingssysteem"), 0, 5);
        grid.add(besturingssysteemBox, 1, 5);

        grid.add(new Label("Aanwezigheidsdatum"), 0, 6);
        grid.add(datumPicker, 1, 6);
        grid.add(submitButton, 1, 7);
        // adds the grid and options to the pane(page)
        p.getChildren().add(grid);
        // Clear the inserted information after the submitbutton was pressed. (next line makes the event)
        submitButton.setOnAction(actionEvent -> {
            // Get the input from the fields and converts it to the datatype to those used in the submitForm method. (i.e. String, LocalDate)
            String dorpsgenoot = dorpsgenootField.getText();
            LocalDate geboortedatum = geboortedatumPicker.getValue();
            String postcode = postcodeField.getText();
            String huisnummer = huisnummerField.getText();
            String email = emailField.getText();
            String telefoonnummer = telefoonnummerField.getText();
            String besturingssysteem = besturingssysteemBox.getValue();
            LocalDate aanwezigheidsdatum = datumPicker.getValue();
            // sends the data to database using submitformtodatabase class
            SubmitFormToDatabase.submitForm(dorpsgenoot, geboortedatum, postcode, huisnummer, email, telefoonnummer, besturingssysteem, aanwezigheidsdatum);
            // clears the fields so it can be filled again.
            dorpsgenootField.clear();
            geboortedatumPicker.setValue(null);
            postcodeField.clear();
            huisnummerField.clear();
            emailField.clear();
            telefoonnummerField.clear();
            besturingssysteemBox.getSelectionModel().clearSelection();
            datumPicker.setValue(null);
            showAlert("Super tof!", "Leuk dat je je hebt aangemeld!");
        });
    }

    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}