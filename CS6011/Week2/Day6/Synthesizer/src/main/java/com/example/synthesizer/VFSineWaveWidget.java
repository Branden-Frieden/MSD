package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class VFSineWaveWidget extends AudioComponentWidget{
    VFSineWaveWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);

        rampVal1_ = 50;
        rampVal2_ = 2000;

        ramp_ = new LinearRamp(rampVal1_, rampVal2_);
        audioComponent_.connectInput(ramp_);

        minSlider_ = new Slider(-10000, 10000, rampVal1_);
        maxSlider_ = new Slider(-10000, 10000, rampVal2_);

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
        rampVal1_ = (int) minSlider_.getValue();
        rampVal2_ = (int) maxSlider_.getValue();

        title_.setText("VFSineWave val1: " + rampVal1_ + ", val2: " + rampVal2_);

        ramp_ = new LinearRamp(rampVal1_,rampVal2_);
        audioComponent_ = new VFSineWave();
        audioComponent_.connectInput(ramp_);
    }
    Slider minSlider_, maxSlider_;
    LinearRamp ramp_;
    int rampVal1_, rampVal2_;
}
