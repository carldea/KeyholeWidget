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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**  This allows the keyhole control to be a window frame to be draggable on the desktop or touch surface.
 * User: cdea
 * Date: 6/9/13
 */
public class KeyholeStage extends Keyhole {

    private Point2D anchorPt;
    private Point2D previousLocation;
    private Stage draggableStage;

    // Mouse based events.
    protected EventHandler<MouseEvent> mouseEntered;
    protected EventHandler<MouseEvent> mouseExit;
    protected EventHandler<MouseEvent> mousePressed;
    protected EventHandler<MouseEvent> mouseClicked;
    protected EventHandler<MouseEvent> mouseDragged;
    protected EventHandler<MouseEvent> mouseReleased;

    // TODO Touch based events

    public KeyholeStage(Stage stage) {
        draggableStage = stage;
        init();
    }
    @Override
    protected void init() {
        super.init(); // set default css style

        mouseEntered = e -> {
            //e.consume();
            //System.out.println(e);
        };

        mouseExit = e ->  {
            //e.consume();
            //System.out.println(e);
        };
        mousePressed = e -> {
            //e.consume();
            anchorPt = new Point2D(e.getScreenX(), e.getScreenY());
        };
        mouseClicked = e -> {
            //e.consume();
            //System.out.println(e);
        };

        mouseDragged = e -> {
            //e.consume();
            if (anchorPt != null && previousLocation != null) {
                draggableStage.setX(previousLocation.getX() + e.getScreenX() - anchorPt.getX());
                draggableStage.setY(previousLocation.getY() + e.getScreenY() - anchorPt.getY());
            }
        };

        mouseReleased = e -> {
            //e.consume();
            previousLocation = new Point2D(draggableStage.getX(), draggableStage.getY());
        };
        // build filters to intercept mouse events.
        removeAllEvents();
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
}
