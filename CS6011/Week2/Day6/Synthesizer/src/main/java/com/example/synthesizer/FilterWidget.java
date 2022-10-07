package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FilterWidget extends AudioComponentWidget{
    FilterWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);

        // Center panel
        Slider slider = new Slider(0.0, 2, 1);
        slider.setOnMouseDragged(e -> handleSlider(slider));

        center_.getChildren().add(slider);
        center_.setAlignment(Pos.CENTER);
        center_.setSpacing(5);
        center_.setPadding(new Insets(5));
    }
    private void handleSlider(Slider slider) {
        Double value = slider.getValue() * 100;
        title_.setText("Volume: " + (float) Math.round(value) / 100.0);
        audioComponent_ = new filter( value );
    }
}
