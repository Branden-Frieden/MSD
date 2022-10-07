package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

import java.awt.event.MouseEvent;

public class VFSineWaveWidget extends AudioComponentWidget{
    VFSineWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);

        minVal_ = 50;
        maxVal_ = 2000;

        ramp_ = new LinearRamp(minVal_, maxVal_);
        audioComponent_.connectInput(ramp_);

        minSlider_ = new Slider(-10000, 10000, minVal_);
        maxSlider_ = new Slider(-10000, 10000, maxVal_);

        minSlider_.setOnMouseDragged(e -> handleSlider());
        maxSlider_.setOnMouseDragged(e -> handleSlider());

        leftSide_.getChildren().remove(inputCircle_);

        center_.setPrefWidth(300);
        center_.getChildren().add(minSlider_);
        center_.getChildren().add(maxSlider_);
        center_.setAlignment(Pos.CENTER);
        center_.setSpacing(5);
        center_.setPadding(new Insets(5));
    }

    private void handleSlider() {
        minVal_ = (int) minSlider_.getValue();
        maxVal_ = (int) maxSlider_.getValue();

        if(minVal_ >= maxVal_){
            minVal_ = maxVal_ - 1;
        }

        title_.setText("VFSineWave min: " + minVal_ + ", max: " + maxVal_);

        ramp_ = new LinearRamp(minVal_,maxVal_);
        audioComponent_ = new VFSineWave();
        audioComponent_.connectInput(ramp_);
    }
    Slider minSlider_, maxSlider_;
    LinearRamp ramp_;
    int minVal_, maxVal_;
}
