package com.icthulp.MyMenu;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;

public class MyMenuBar extends MenuBar {
    ClickAbleMenuAanmelden menuAanmelden = new ClickAbleMenuAanmelden("Aanmelden");
    ClickAbleMenuInformatie menuInformatie = new ClickAbleMenuInformatie("Informatie");
    ClickAbleMenuInzageAanmeldingen menuInzageAanmeldingen = new ClickAbleMenuInzageAanmeldingen("Inzage aanmeldingen");

    public MyMenuBar(Pane root) {
        menuAanmelden.setPane(root);
        menuInformatie.setPane(root);
        menuInzageAanmeldingen.setPane(root);
        this.getMenus().addAll(menuInformatie, menuAanmelden, menuInzageAanmeldingen);
    }
}
