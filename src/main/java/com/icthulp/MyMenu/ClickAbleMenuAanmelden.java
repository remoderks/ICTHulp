package com.icthulp.MyMenu;

import com.icthulp.AanmeldenPage.AanmeldenPage;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuAanmelden extends Menu {
    //Creates the label in a pane layout
    private Label label = new Label();
    private Pane aanmelden;
    // Create a clickable label for the aanmelden text in the menu.
    public ClickAbleMenuAanmelden(String text){
        MenuItem dummy = new MenuItem();
        dummy.setVisible(false);
        label.setText(text);
        label.setOnMouseClicked(event -> {
            // Clears the screen.
            aanmelden.getChildren().clear();
            // opens the aanmelden page.
            new AanmeldenPage(aanmelden);
        });
        setGraphic(label);
    }
    public void setPane(Pane p) {
        this.aanmelden = p;
    }
}
