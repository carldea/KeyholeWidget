package com.carlfx.scene.control.keyhole;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * User: cdea
 * Date: 6/8/13
 */
public class KeyholeDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        Keyhole keyhole1 = new Keyhole();
        Group root = new Group();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(keyhole1);
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 525, 750, Color.BLACK);

        primaryStage.setTitle("Keyhole widget control");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
