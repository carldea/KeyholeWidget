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

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/** This demonstrates the Keyhole widget control as an irregular shaped window on the desktop.
 * User: cdea
 * Date: 6/8/13
 */
public class KeyholeDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Keyhole keyhole1 = new KeyholeStage(primaryStage);
        int speed = 250;
//        keyhole1.setWidgetMetalRimColor(Color.rgb(233, 20, 20, .7));
//        keyhole1.setWidgetMetalRimColor(Color.rgb(20, 233, 20, .7));
//        keyhole1.setWidgetMetalRimColor(Color.rgb(20, 20, 233, .7));
//        keyhole1.setWidgetMetalRimColor(Color.rgb(200, 200, 200, .7));
//        keyhole1.setWidgetMetalRimColor(Color.STEELBLUE);
        keyhole1.setWidgetMetalRimColor(Color.OLIVE);

        Image image = new Image("sunny.png");
        final ImageView imageView = new ImageView(image);
        keyhole1.getContent().add(imageView);
        ContextMenu move = new ContextMenu();
        SimpleBooleanProperty exitFlag = new SimpleBooleanProperty(false);
        Menu exitMenu = new Menu("Exit");
        MenuItem exitLeft = new MenuItem("< Left");
        exitLeft.setOnAction( ae -> {
            System.out.println("exit left " + exitFlag.get());
            if (! exitFlag.get()) {
                // move left
                imageView.setTranslateX(0);
                imageView.setTranslateY(0);
                imageView.setOpacity(1);
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(imageView);
                tt.setFromX(0);
                tt.setToX(-image.getWidth());
                tt.setDuration(Duration.millis(speed));
                tt.setInterpolator(Interpolator.EASE_OUT);
                tt.setOnFinished( e -> {
                    exitFlag.setValue(true);
                });
                tt.playFromStart();
            }
        });
        MenuItem exitUp = new MenuItem("^ Up");
        exitUp.setOnAction( ae -> {
            System.out.println("exit up " + exitFlag.get());
            if (! exitFlag.get()) {
                // move up
                imageView.setTranslateX(0);
                imageView.setTranslateY(0);
                imageView.setOpacity(1);
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(imageView);
                tt.setFromY(0);
                tt.setToY(-image.getHeight());
                tt.setDuration(Duration.millis(speed));
                tt.setInterpolator(Interpolator.EASE_OUT);
                tt.setOnFinished( e -> {
                    exitFlag.setValue(true);
                });
                tt.playFromStart();
            }
        });
        MenuItem exitDown = new MenuItem("V Down");
        exitDown.setOnAction( ae -> {
            System.out.println("exit down " + exitFlag.get());
            if (! exitFlag.get()) {
                // move down
                imageView.setTranslateX(0);
                imageView.setTranslateY(0);
                imageView.setOpacity(1);
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(imageView);
                tt.setFromY(0);
                tt.setToY(image.getHeight() * 2);
                tt.setDuration(Duration.millis(speed));
                tt.setInterpolator(Interpolator.EASE_OUT);
                tt.setOnFinished( e -> {
                    exitFlag.setValue(true);
                });
                tt.playFromStart();
            }
        });

        MenuItem exitFade = new MenuItem("Fade out");
        exitFade.setOnAction( ae -> {
            System.out.println("Fade out " + exitFlag.get());
            if (! exitFlag.get()) {
                // fade out
                imageView.setTranslateX(0);
                imageView.setTranslateY(0);
                imageView.setOpacity(1);
                FadeTransition tt = new FadeTransition();
                tt.setNode(imageView);
                tt.setFromValue(1);
                tt.setToValue(0);
                tt.setDuration(Duration.millis(speed));

                tt.setOnFinished( e -> {
                    exitFlag.setValue(true);
                });
                tt.playFromStart();
            }
        });


        exitMenu.getItems().addAll(exitLeft, exitUp, exitDown, exitFade);

        Menu enterMenu = new Menu("Enter");
        enterMenu.setDisable(true);
        MenuItem enterRight = new MenuItem("> Right");
        enterRight.setOnAction( ae -> {
            System.out.println("enter right " + exitFlag.get());
            if (exitFlag.get()) {
                // move right
                imageView.setOpacity(1);
                imageView.setTranslateX(-image.getWidth());
                imageView.setTranslateY(0);
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(imageView);
                tt.setFromX(-image.getWidth());
                tt.setToX(0);
                tt.setDuration(Duration.millis(speed));
                tt.setInterpolator(Interpolator.EASE_IN);
                tt.setOnFinished( e -> {
                    exitFlag.setValue(false);
                });
                tt.playFromStart();
            }
        });
        MenuItem enterDown = new MenuItem("V Down");
        enterDown.setOnAction( ae -> {
            System.out.println("enter down " + exitFlag.get());
            if (exitFlag.get()) {
                // move down
                imageView.setOpacity(1);
                imageView.setTranslateX(0);
                imageView.setTranslateY(-image.getHeight());
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(imageView);
                tt.setFromY(-image.getHeight());
                tt.setToY(0);
                tt.setDuration(Duration.millis(speed));
                tt.setInterpolator(Interpolator.EASE_IN);
                tt.setOnFinished( e -> {
                    exitFlag.setValue(false);
                });
                tt.playFromStart();
            }
        });

        MenuItem enterUp = new MenuItem("^ Up");
        enterUp.setOnAction( ae -> {
            System.out.println("enter up " + exitFlag.get());
            if (exitFlag.get()) {
                // move down
                imageView.setOpacity(1);
                imageView.setTranslateX(0);
                imageView.setTranslateY(image.getHeight() * 2);
                TranslateTransition tt = new TranslateTransition();
                tt.setNode(imageView);
                tt.setFromY(image.getHeight() * 2);
                tt.setToY(0);
                tt.setDuration(Duration.millis(speed));
                tt.setInterpolator(Interpolator.EASE_IN);
                tt.setOnFinished( e -> {
                    exitFlag.setValue(false);
                });
                tt.playFromStart();
            }
        });

        MenuItem enterFade = new MenuItem("Fade in");
        enterFade.setOnAction( ae -> {
            System.out.println("Fade in " + exitFlag.get());
            if (exitFlag.get()) {
                // move left
                imageView.setOpacity(0);
                imageView.setTranslateX(0);
                imageView.setTranslateY(0);

                FadeTransition tt = new FadeTransition();
                tt.setNode(imageView);
                tt.setFromValue(0);
                tt.setToValue(1);
                tt.setDuration(Duration.millis(speed));

                tt.setOnFinished( e -> {
                    exitFlag.setValue(false);
                });
                tt.playFromStart();
            }
        });
        enterMenu.getItems().addAll(enterRight, enterDown, enterUp, enterFade);

        MenuItem quit = new MenuItem(":! Quit");
        quit.setOnAction( ae -> {
            Platform.exit();
        });

        move.getItems().addAll(exitMenu, enterMenu, quit);
        keyhole1.setContextMenu(move);
        exitFlag.addListener( cl -> {
            boolean exit = exitFlag.get();
            enterMenu.setDisable(!exit);
            exitMenu.setDisable(exit);
        });
        keyhole1.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent contextMenuEvent) {
                System.out.println(contextMenuEvent);
            }
        });


        Group root = new Group();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(keyhole1);
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, keyhole1.getPrefWidth(), keyhole1.getPrefHeight(), null);

        primaryStage.setTitle("Keyhole widget control");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
