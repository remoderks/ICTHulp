package com.icthulp.MyMenu;

import com.icthulp.DorpsgenotenInfo.DorpsgenotenInfo;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuInzageAanmeldingen extends Menu {
        //Creates the label in a pane layout
        private Label label = new Label();
        private Pane InzageAanmeldingen;

        // Create a clickable label for the aanmelden text in the menu.
        public ClickAbleMenuInzageAanmeldingen(String text){
            MenuItem dummy = new MenuItem();
            dummy.setVisible(false);
            label.setText(text);
            label.setOnMouseClicked(event -> {
                // Clears the screen.
                InzageAanmeldingen.getChildren().clear();
                // opens the Inzage page.
                new DorpsgenotenInfo(InzageAanmeldingen);
            });
            setGraphic(label);
        }
        public void setPane(Pane p) {
            this.InzageAanmeldingen = p;
        }
}
