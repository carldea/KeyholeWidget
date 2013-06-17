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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cdea
 */
public class Keyhole extends Control {
    protected static final String DEFAULT_STYLE_CLASS = "keyhole-widget";
    public static final double  PREFERRED_WIDTH = 277.438;
    public static final double  PREFERRED_HEIGHT = 128.938;

    /** #keyhole-widget-background
     * main widget background.
     */
    private ObjectProperty<Color> widgetMetalRimColor;
    private List<Node> nodeList = new ArrayList<>();
    private ObservableList<Node> content = FXCollections.observableArrayList(nodeList);

    public Keyhole () {
        System.out.println("control:Keyhole()");
        init();
    }

    protected void init() {
        System.out.println("control:init()");
        widgetMetalRimColor = new SimpleObjectProperty<Color>(Color.web("#75bac6"));
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    }

    @Override
    protected String getUserAgentStylesheet() {
        return getClass().getResource("/scene/control/keyhole_widget.css").toExternalForm();
    }

    public Color getWidgetMetalRimColor() {
        return widgetMetalRimColor.get();
    }

    public ObjectProperty<Color> widgetMetalRimColorProperty() {
        return widgetMetalRimColor;
    }

    public void setWidgetMetalRimColor(Color widgetMetalRimColor) {
        this.widgetMetalRimColor.set(widgetMetalRimColor);
    }

    public ObservableList<Node> getContent() {
        return content;
    }
}
