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


import com.carlfx.scene.behavior.KeyholeBehavior;
import com.carlfx.scene.control.keyhole.Keyhole;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;

import static com.carlfx.scene.control.keyhole.Keyhole.PREFERRED_HEIGHT;
import static com.carlfx.scene.control.keyhole.Keyhole.PREFERRED_WIDTH;

/**
 * This represents the JavaFX Skin class mainly comprised of the drawings of
 * the control's face. The styling is done in the keyhole_widget.css file.
 *
 * @author cdea
 */
public class KeyholeSkin extends com.sun.javafx.scene.control.skin.BehaviorSkinBase<Keyhole, KeyholeBehavior>{
    private boolean     isDirty;
    private boolean     initialized;
    private Node        widgetBackground;

    public KeyholeSkin(final Keyhole keyhole) {
        super(keyhole, new KeyholeBehavior(keyhole));
        System.out.println("skin:KeyholeSkin()");
        initialized  = false;
        isDirty = false;

        init();
    }
    private void init() {
        System.out.println("skin:init()");
        Keyhole control = getSkinnable();
        if (control.getPrefWidth() <= 0 && control.getPrefHeight() <= 0) {
            control.setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        } else if (control.getPrefWidth() <= 0) {
            control.setPrefSize(PREFERRED_WIDTH, control.getPrefHeight());
        } else if (control.getPrefHeight() <= 0) {
            control.setPrefSize(control.getPrefWidth(), PREFERRED_HEIGHT);
        }

        // Register listeners
        registerChangeListener(control.widgetMetalRimColorProperty(), "OUTER_RIM_COLOR");
//        mouseHandler = new EventHandler<MouseEvent>() {
//            @Override public void handle(final MouseEvent event) {
//                if (MouseEvent.MOUSE_PRESSED == event.getEventType()) {
//                } else if (MouseEvent.MOUSE_DRAGGED == event.getEventType()) {
//                } else if (MouseEvent.MOUSE_RELEASED == event.getEventType()) {
//                }
//            }
//        };
//
//        touchHandler = new EventHandler<TouchEvent>() {
//            @Override public void handle(final TouchEvent event) {
//                if (TouchEvent.TOUCH_PRESSED == event.getEventType()) {
//
//                } else if (TouchEvent.TOUCH_MOVED == event.getEventType()) {
//
//                } else if (TouchEvent.TOUCH_RELEASED == event.getEventType()) {
//
//                }
//            }
//        };

        //setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        addHandlers();

        initialized = true;
        repaint();
    }
    @Override protected void handleControlPropertyChanged(final String PROPERTY) {
        System.out.println("skin:handleControlPropertyChanged()");
        super.handleControlPropertyChanged(PROPERTY);
        if ("OUTER_RIM_COLOR".equals(PROPERTY)) {
            widgetBackground.setStyle("-fx-fill: " + colorToCssColor(getSkinnable().getWidgetMetalRimColor()));
        } else if ("PREF_WIDTH".equals(PROPERTY)) {
            repaint();
        } else if ("PREF_HEIGHT".equals(PROPERTY)) {
            repaint();
        }
    }

    public final void repaint() {
        System.out.println("skin:repaint()");
        isDirty = true;
        getSkinnable().requestLayout();
    }

    @Override public void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        System.out.println("skin:layoutChildren()");
        if (!isDirty) {
            return;
        }
        if (!initialized) {
            init();
        }
        if (getSkinnable().getScene() != null) {
            drawControl();
        }
        isDirty = false;
    }

    @Override public void dispose() {
        System.out.println("skin:dispose()");
        getChildren().clear();
    }

    private void addHandlers() {
        System.out.println("skin:addHandlers()");
//        // MouseEvents
//        setOnMousePressed(mouseHandler);
//        setOnMouseDragged(mouseHandler);
//        setOnMouseReleased(mouseHandler);
//        // TouchEvents
//        setOnTouchPressed(touchHandler);
//        setOnTouchMoved(touchHandler);
//        setOnTouchReleased(touchHandler);
    }


    // ******************** Drawing related ***********************************

    private void drawControl() {
        System.out.println("skin:drawControl()");
        getChildren().clear();
        Keyhole control = getSkinnable();
        final double WIDTH          = control.getPrefWidth();
        final double HEIGHT         = control.getPrefHeight();
        final double SCALE_FACTOR_X = WIDTH / PREFERRED_WIDTH;
        final double SCALE_FACTOR_Y = HEIGHT / PREFERRED_HEIGHT;
        final Scale SCALE           = new Scale();
        SCALE.setX(SCALE_FACTOR_X);
        SCALE.setY(SCALE_FACTOR_Y);
        SCALE.setPivotX(0);
        SCALE.setPivotY(0);

        // draw #keyhole-widget-background
        widgetBackground = drawWidgetBackground(SCALE);

        // draw #keyhole-outer-metal-rim
        Node outerMetalRim = drawOuterMetalRim(SCALE);
        // draw #keyhole-content-background
        Node contentBackground = drawContentBackground(SCALE);
        // draw #keyhole-top-glare-group
        // draw #keyhole-circle-glare-group
        // draw #keyhole-lower-right-circle
        Node  lowerRightCircleGlare = drawLowerCircleGlare(SCALE);
        // draw #keyhole-upper-left-circle-glare
        Node upperLeftCircleGlare = drawUpperCircleGlare(SCALE);

        // draw #keyhole-right-glare-group
        // draw #keyhole-right-bottom-glare
        Node rightBottomGlare = drawRightBottomGlare(SCALE);
        // draw #keyhole-right-top-glare
        Node rightTopGlare = drawRightTopGlare(SCALE);

        getChildren().addAll(widgetBackground,
                outerMetalRim,
                contentBackground,
                // content goes here
                rightTopGlare,
                rightBottomGlare,
                upperLeftCircleGlare,
                lowerRightCircleGlare
        );
    }




    private Node drawWidgetBackground(Scale scale) {
        SVGPath svgPath = new SVGPath();
        svgPath.getTransforms().add(scale);
        svgPath.setContent("m 65.545965,0.350505 c -36.130555,0 -65.420156,28.815813 -65.420156,64.350793 0,35.534982 29.289601,64.335072 65.420156,64.335072 22.49148,0 42.323155,-11.1652 54.097435,-28.1653 l 146.15745,0 c 6.37235,0 11.51142,-5.123262 11.51142,-11.495662 l 0,-47.44535 c 0,-6.37234 -5.13907,-11.51144 -11.51142,-11.51144 l -144.88364,0 C 109.21711,12.151138 88.561815,0.350505 65.545965,0.350505 z");
        svgPath.setId("keyhole-widget-background");
        svgPath.setStyle("-fx-fill: " + colorToCssColor(getSkinnable().getWidgetMetalRimColor()));
        return svgPath;
    }
    private Node drawOuterMetalRim(Scale scale) {
        SVGPath svgPath = new SVGPath();
        svgPath.getTransforms().add(scale);
        svgPath.setContent("m 65.545965,0.350505 c -36.13055,0 -65.420157,28.815813 -65.420157,64.350793 0,35.534982 29.289607,64.335072 65.420157,64.335072 22.49148,0 42.323155,-11.1652 54.097435,-28.1653 l 146.15745,0 c 6.37235,0 11.51142,-5.123262 11.51142,-11.495662 l 0,-47.44535 c 0,-6.37234 -5.13907,-11.51144 -11.51142,-11.51144 l -144.88364,0 C 109.21711,12.151138 88.561815,0.350505 65.545965,0.350505 z");
        svgPath.setId("keyhole-outer-metal-rim");

        return svgPath;
    }

    private Node drawContentBackground(Scale scale) {
        SVGPath svgPath = new SVGPath();
        svgPath.getTransforms().add(scale);
        svgPath.setContent("m 65.608875,7.1834649 c -32.295481,0 -58.469271,25.7468531 -58.469271,57.5099531 0,31.76319 26.17379,57.509952 58.469271,57.509952 21.23968,0 39.829435,-11.143 50.071575,-27.803462 l 149.41272,0 c 2.87731,0 5.20531,-2.3123 5.20531,-5.1896 l 0,-47.85427 c 0,-2.87728 -2.328,-5.18953 -5.20531,-5.18953 l -148.70505,0 C 106.20743,18.633498 87.060795,7.1834649 65.608875,7.1834649 z");
        svgPath.setId("keyhole-content-background");

        return svgPath;
    }

    private Node drawRightBottomGlare(Scale scale) {
        SVGPath svgPath = new SVGPath();
        svgPath.getTransforms().add(scale);
        svgPath.setContent("m 116.15625,94.083841 c 2.86983,-5.068457 4.98785,-10.617495 6.19975,-16.543751 l 1.14164,-11.259816 c 0,-4.020552 -0.41607,-7.943783 -1.22093,-11.731595 l 143.92595,0 c 1.80705,0 3.26636,1.44733 3.26636,3.239556 l 0,33.05605 c 0,1.792226 -1.45931,3.239556 -3.26636,3.239556 l -150.04641,0 z");
        svgPath.setId("keyhole-right-bottom-glare");

        return svgPath;
    }

    private Node drawRightTopGlare(Scale scale) {
        SVGPath svgPath = new SVGPath();
        svgPath.getTransforms().add(scale);
        svgPath.setContent("m 116.15625,36.699448 c 2.8631,5.068457 4.97616,10.617495 6.18521,16.543751 l 1.13896,11.259816 c 0,4.020552 -0.41509,7.943783 -1.21805,11.731595 l 143.5885,0 c 1.80282,0 3.2587,-1.44733 3.2587,-3.239555 l 0,-33.056051 c 0,-1.792226 -1.45588,-3.239556 -3.2587,-3.239556 l -149.69462,0 z");
        svgPath.setId("keyhole-right-top-glare");

        return svgPath;
    }
    private Node drawLowerCircleGlare(Scale scale) {
        SVGPath svgPath = new SVGPath();
        svgPath.getTransforms().add(scale);
        svgPath.setContent("m 504,381.5 c 0,68.09616 -47.0101,123.29901 -105,123.29901 -57.9899,0 -105,-55.20285 -105,-123.29901 0,-68.09616 47.0101,-123.29901 105,-123.29901 57.9899,0 105,55.20285 105,123.29901 z");
        svgPath.setId("keyhole-lower-right-circle-glare");
        svgPath.getTransforms().add(Transform.affine(0.07542637,0.22779962,-0.32633786,0.05279491,166.52815,-14.136685));
        return svgPath;
    }

    private Node drawUpperCircleGlare(Scale scale) {
        SVGPath svgPath = new SVGPath();
        svgPath.getTransforms().add(scale);
        svgPath.setContent("m 187,173 c 0,74.00616 -37.60808,134 -84,134 C 56.608081,307 19,247.00616 19,173 19,98.993844 56.608081,39 103,39 c 46.39192,0 84,59.993844 84,134 z");
        svgPath.setId("keyhole-upper-left-circle-glare");
        svgPath.getTransforms().add(Transform.affine(0.27654374,0.13369495,-0.21113577,0.17511246,60.050271,-10.349852));
        return svgPath;
    }

    public static String colorToCssColor(final Color COLOR) {
        final StringBuilder CSS_COLOR = new StringBuilder(19);
        CSS_COLOR.append("rgba(");
        CSS_COLOR.append((int) (COLOR.getRed() * 255)).append(", ");
        CSS_COLOR.append((int) (COLOR.getGreen() * 255)).append(", ");
        CSS_COLOR.append((int) (COLOR.getBlue() * 255)).append(", ");
        CSS_COLOR.append(COLOR.getOpacity()).append(");");
        return CSS_COLOR.toString();
    }
}
