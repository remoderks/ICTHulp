package com.icthulp.InformatiePage;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class InformatiePage {
    public InformatiePage(Pane p) {
        VBox layout = new VBox();
        layout.setSpacing(10);
        Label infoLabel = new Label("Dit is een applicatie waar dorpsgenoten zich kunnen aanmelden voor evenementen.");
        Label infoTextbox = new Label("Heeft u problemen met uw apparaten? Denk hierbij aan problemen met updates, opslag, programma's installeren of andere zaken. Dan kunnen jullie op deze twee dagen terecht!!");
        Label infoTextbox2 = new Label("Ook kan de organisatie inzien wie er zich allemaal aangemeld hebben. Personen kunnen ook aangepast of verwijderd worden.");
        infoTextbox2.setWrapText(true);
        infoTextbox2.setMaxWidth(450);
        infoTextbox.setWrapText(true);  // Enables text wrapping.
        infoTextbox.setMaxWidth(450);   // Sets how wide the text will go before wrapping.
        layout.getChildren().addAll(infoLabel, infoTextbox, infoTextbox2);
        p.getChildren().add(layout);
    }
}
