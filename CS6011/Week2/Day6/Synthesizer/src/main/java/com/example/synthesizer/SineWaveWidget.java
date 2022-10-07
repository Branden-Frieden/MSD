package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SineWaveWidget extends AudioComponentWidget {
    SineWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);

        // Center panel

        Slider slider = new Slider(220, 880, 440);
        slider.setOnMouseDragged(e -> handleSlider(slider));

        leftSide_.getChildren().remove(inputCircle_);

        center_.getChildren().add(slider);
        center_.setAlignment(Pos.CENTER);
        center_.setSpacing(5);
        center_.setPadding(new Insets(5));
    }

    private void handleSlider(Slider slider) {
        int value = (int) slider.getValue();
        title_.setText("Sine Wave (" + value + "Hz)");
        audioComponent_ = new SineWave( value );
    }


}