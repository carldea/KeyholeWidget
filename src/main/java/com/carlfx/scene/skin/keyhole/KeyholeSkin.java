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
package com.carlfx.scene.skin.keyhole;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.SVGPathBuilder;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.ScaleBuilder;
import com.carlfx.scene.control.keyhole.Keyhole;

import static com.carlfx.scene.control.keyhole.Keyhole.PREFERRED_HEIGHT;
import static com.carlfx.scene.control.keyhole.Keyhole.PREFERRED_WIDTH;

/**
 *
 * @author cdea
 */
public class KeyholeSkin extends Region implements Skin<Keyhole>{
    private final Keyhole            CONTROL;
    private EventHandler<MouseEvent> mouseHandler;
    private EventHandler<TouchEvent> touchHandler;
    
    public KeyholeSkin(final Keyhole keyhole) {
        this.CONTROL = keyhole;

        if (CONTROL.getPrefWidth() <= 0 || CONTROL.getPrefHeight() <= 0) {
            CONTROL.setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        }


        mouseHandler = new EventHandler<MouseEvent>() {
            @Override public void handle(final MouseEvent event) {
                if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
                } else if (MouseEvent.MOUSE_DRAGGED == event.getEventType()) {
                } else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
                }
            }
        };

        touchHandler = new EventHandler<TouchEvent>() {
            @Override public void handle(final TouchEvent event) {
                if (TouchEvent.TOUCH_PRESSED == event.getEventType()) {
                    
                } else if (TouchEvent.TOUCH_MOVED == event.getEventType()) {
                    
                } else if (TouchEvent.TOUCH_RELEASED == event.getEventType()) {
                    
                }
            }
        };

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        drawControl();
        addHandlers();
        //startAnimations();
    }

    /**
     * This method builds an AnimationTimer and starts it to provide periodic animations.
     * If the skin is to call the layoutChildren() method it is important to stop and recreate if scaling changes.
     */
    private void startAnimations() {

        // create a spot light animation across the letters.
    }

    @Override public void layoutChildren() {
        // TODO MAKE THIS efficient and cache images
	drawControl();
        startAnimations();
        super.layoutChildren();
    }

    @Override public Keyhole getSkinnable() {
        return CONTROL;
    }

    @Override public Node getNode() {
        return this;
    }

    @Override public void dispose() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void addHandlers() {
        // MouseEvents
        setOnMousePressed(mouseHandler);
        setOnMouseDragged(mouseHandler);
        setOnMouseReleased(mouseHandler);
        // TouchEvents
        setOnTouchPressed(touchHandler);
        setOnTouchMoved(touchHandler);
        setOnTouchReleased(touchHandler);
    }

 


    // ******************** Drawing related ***********************************

    private void drawControl() {
        final double WIDTH          = CONTROL.getPrefWidth();
        final double HEIGHT         = CONTROL.getPrefHeight();
        final double SCALE_FACTOR_X = WIDTH / PREFERRED_WIDTH;
        final double SCALE_FACTOR_Y = HEIGHT / PREFERRED_HEIGHT;
        final Scale SCALE           = ScaleBuilder.create()
                .x(WIDTH / PREFERRED_WIDTH)
                .y(HEIGHT / PREFERRED_HEIGHT)
                .pivotX(0)
                .pivotY(0)
                .build();
/*
        Rectangle backgroundRect = RectangleBuilder.create()
                .id("slide-background")
                .width(WIDTH)
                .height(HEIGHT)
                .build();
        backgroundRect.visibleProperty().bind(CONTROL.backgroundVisibleProperty());

        Rectangle slideArea = RectangleBuilder.create()
                .id("slide-area")
                .x(0.0612476117 * WIDTH)
                .y(0.2463297872 * HEIGHT)
                .width(0.8829200973 * WIDTH)
                .height(0.5319148936 * HEIGHT)
                .arcWidth(0.079787234 * HEIGHT)
                .arcHeight(0.079787234 * HEIGHT)
                .build();

        SVGPath glareRect = SVGPathBuilder.create()
                .fill(LinearGradientBuilder.create()
                        .proportional(true)
                        .startX(0)
                        .startY(0)
                        .endX(0)
                        .endY(1)
                        .stops(new Stop(0, Color.web("f0f0f0", 1)),
                                new Stop(1, Color.web("f0f0f0", 0))
                        )
                        .build()
                )
                .opacity(.274)
                .transforms(SCALE)
                .content("m 0,0 0,94 32,0 0,-27.218747 C 30.998808,55.222973 37.761737,45.9354 46.156457,45.93665 l 431.687503,0.06427 c 8.39472,0.0013 15.15487,9.290837 15.15315,20.814756 l -0.004,27.218754 30.28125,0 0,-94.0000031 L 0,0 z")
                .id("glare-frame")
                .build();
        glareRect.visibleProperty().bind(CONTROL.backgroundVisibleProperty());

        text.setText(CONTROL.getText());
        text.setId("slide-text");
        text.getTransforms().clear();
        text.getTransforms().add(SCALE);

        drawSlideButton();
        button.translateXProperty().bind(CONTROL.endXProperty().multiply(SCALE_FACTOR_X));
        button.setTranslateY(SlideLock.BUTTON_YCOORD * SCALE_FACTOR_Y);

        text.setTranslateX(SlideLock.START_XCOORD + button.getBoundsInParent().getWidth() + 0.1063829787 * HEIGHT);
        text.setTranslateY(0.5744680851 * HEIGHT);
        text.opacityProperty().bind(CONTROL.textOpacityProperty());

        Rectangle topGlareRect = RectangleBuilder.create()
                .id("slide-top-glare")
                .fill(Color.WHITE)
                .width(WIDTH)
                .height(0.5 * HEIGHT)
                .opacity(0.0627451)
                .build();
        topGlareRect.visibleProperty().bind(CONTROL.backgroundVisibleProperty());
        getChildren().clear();
        getChildren().addAll(backgroundRect, slideArea, glareRect, text, button, topGlareRect);
*/
    }

    private void drawxyz() {
        final double WIDTH   = CONTROL.getPrefWidth();
        final double HEIGHT  = CONTROL.getPrefHeight();
        final double SCALE_X = WIDTH / PREFERRED_WIDTH;
        final double SCALE_Y = HEIGHT / PREFERRED_HEIGHT;
        Scale scale = new Scale();
        scale.setX(SCALE_X);
        scale.setY(SCALE_Y);
        scale.setPivotX(0);
        scale.setPivotY(0);

    }
}
