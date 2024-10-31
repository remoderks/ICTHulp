package com.icthulp;
import com.icthulp.MyMenu.MyMenuBar;
import com.icthulp.InformatiePage.InformatiePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create MenuBar
        Pane root = new Pane();
        MyMenuBar menuBar = new MyMenuBar(root);

        // Setup initial page to InformatiePage
        new InformatiePage(root);

        // Create VBox as the main layout
        VBox vBox = new VBox(menuBar, root);

        // Setup scene and show the stage. (just makes the front blank page with the title Aanmelden voor ICTHulp)
        Scene scene = new Scene(vBox, 1600, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aanmelden voor ICTHulp");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}

