package com.carlfx.scene.control.keyhole;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * User: cdea
 * Date: 6/9/13
 */
public class KeyholeStage extends Keyhole {

    public Point2D anchorPt;
    public Point2D previousLocation;
    public Stage draggableStage;

    protected EventHandler<MouseEvent> mouseEntered;
    protected EventHandler<MouseEvent> mouseExit;
    protected EventHandler<MouseEvent> mousePressed;
    protected EventHandler<MouseEvent> mouseClicked;
    protected EventHandler<MouseEvent> mouseDragged;
    protected EventHandler<MouseEvent> mouseReleased;


    public KeyholeStage(Stage stage) {

        draggableStage = stage;
        init();
    }
    @Override
    protected void init() {
        super.init();

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
