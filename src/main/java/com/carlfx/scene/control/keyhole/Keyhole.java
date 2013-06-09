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

import javafx.scene.control.Control;

/**
 *
 * @author cdea
 */
public class Keyhole extends Control {
    private static final String DEFAULT_STYLE_CLASS = "keyhole-widget";

    public static final double  PREFERRED_WIDTH = 277.438;
    public static final double  PREFERRED_HEIGHT = 128.938;
    
    public Keyhole () {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        init();
    }

    public Keyhole (String displayText) {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);

        init();
    }

    private void init() {
    }

    @Override
    protected String getUserAgentStylesheet() {
        return getClass().getResource("/scene/control/keyhole_widget.css").toExternalForm();
    }

}
