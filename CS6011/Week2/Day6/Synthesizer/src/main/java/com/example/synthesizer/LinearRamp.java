package com.example.synthesizer;

public class LinearRamp implements AudioComponent{

    float start_;
    float stop_;

    LinearRamp( int start, int stop){
        start_ = start;
        stop_ = stop;
    }
    @Override
    public AudioClip getClip() {
        AudioClip output = new AudioClip();
        float numSamples = output.getTotalSamples() / 2;
        for(float i = 0; i < numSamples; i++){
            output.setSample((int) i, (int) ((start_ * ( numSamples - i) + stop_ * i) / numSamples));
        }
        return output;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }
}
