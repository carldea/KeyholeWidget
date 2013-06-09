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

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author cdea
 */
public class Keyhole extends Control {
    private static final String DEFAULT_STYLE_CLASS = "keyhole-widget";

    public static final double  PREFERRED_WIDTH = 277.438;
    public static final double  PREFERRED_HEIGHT = 128.938;

    public Point2D anchorPt;
    public Point2D previousLocation;
    public Stage draggableStage;
    protected EventHandler<MouseEvent> mouseEntered = e -> {
        e.consume();
        System.out.println(e);
    };
    protected EventHandler<MouseEvent> mouseExit = e ->  {
        e.consume();
        System.out.println(e);
    };
    protected EventHandler<MouseEvent> mousePressed = e -> {
        e.consume();
        anchorPt = new Point2D(e.getScreenX(), e.getScreenY());
    };
    protected EventHandler<MouseEvent> mouseClicked = e -> {
        e.consume();
        System.out.println(e);
    };
    protected EventHandler<MouseEvent> mouseDragged = e -> {
        e.consume();
        if (anchorPt != null && previousLocation != null) {
            draggableStage.setX(previousLocation.getX() + e.getScreenX() - anchorPt.getX());
            draggableStage.setY(previousLocation.getY() + e.getScreenY() - anchorPt.getY());
        }
    };

    protected EventHandler<MouseEvent> mouseReleased = e -> {
        e.consume();
        previousLocation = new Point2D(draggableStage.getX(), draggableStage.getY());
    };


    public Keyhole () {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        init();
    }

    public Keyhole (String displayText) {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);

        init();
    }
    public Keyhole(Stage stage) {
        draggableStage = stage;
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        init();
    }

    protected void init() {
        // build filters to intercept mouse events.
        addAllDefaultEvents();

        // when the stage is shown initialize the previous location
        // app developer will invoke the stage.show()
        if (draggableStage != null) {
            draggableStage.addEventHandler(WindowEvent.WINDOW_SHOWN, (e) -> {
                previousLocation = new Point2D(draggableStage.getX(), draggableStage.getY());
            });
        }

    }
    public void removeAllEvents() {
        removeEventFilter(MouseEvent.MOUSE_ENTERED, mouseEntered);
        removeEventFilter(MouseEvent.MOUSE_PRESSED, mousePressed);
        removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseClicked);
        removeEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDragged);
        removeEventFilter(MouseEvent.MOUSE_EXITED, mouseExit);
        removeEventFilter(MouseEvent.MOUSE_RELEASED, mouseReleased);

    }
    public void addAllDefaultEvents() {
        // build filters to intercept mouse events
        addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEntered);
        addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressed);
        addEventFilter(MouseEvent.MOUSE_CLICKED, mouseClicked);
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDragged);
        addEventFilter(MouseEvent.MOUSE_EXITED, mouseExit);
        addEventFilter(MouseEvent.MOUSE_RELEASED, mouseReleased);
    }

    @Override
    protected String getUserAgentStylesheet() {
        return getClass().getResource("/scene/control/keyhole_widget.css").toExternalForm();
    }

}
