package com.icthulp.MyMenu;

import com.icthulp.InformatiePage.InformatiePage;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ClickAbleMenuInformatie extends Menu {
        private Label label = new Label();
        private Pane informatie;

        public ClickAbleMenuInformatie(String text){
            MenuItem dummy = new MenuItem();
            dummy.setVisible(false);
            label.setText(text);
            label.setOnMouseClicked(event -> {
                informatie.getChildren().clear();
                new InformatiePage(informatie);
            });
            setGraphic(label);
        }
        public void setPane(Pane p) {
            this.informatie = p;
        }
    }

