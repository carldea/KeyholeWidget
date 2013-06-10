/*
 * Copyright (c) 2013. Carl Dea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carlfx.scene.control.keyhole;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** This demonstrates the Keyhole widget control as an irregular shaped window on the desktop.
 * User: cdea
 * Date: 6/8/13
 */
public class KeyholeDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Keyhole keyhole1 = new KeyholeStage(primaryStage);

        Group root = new Group();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(keyhole1);
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, keyhole1.getPrefWidth(), keyhole1.getPrefHeight(), null);

        primaryStage.setTitle("Keyhole widget control");
        primaryStage.setScene(scene);
        primaryStage.show();

        // change listeners work after control is realized (shown).
        keyhole1.widgetMetalRimColorProperty().setValue(Color.STEELBLUE);
        System.out.println("setcolor property");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
