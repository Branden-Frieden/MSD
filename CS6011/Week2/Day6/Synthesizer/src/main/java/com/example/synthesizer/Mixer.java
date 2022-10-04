package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent{

    ArrayList<AudioComponent> inputs_ = new ArrayList<AudioComponent>(5);
    int inputNums_ = 0;
    @Override
    public AudioClip getClip() {
        AudioClip output = new AudioClip();
        for (int j = 0; j < inputs_.size(); j++) {
            AudioClip inputClip = (inputs_.get(j)).getClip();

            for (int i = 0; i < inputClip.getTotalSamples() / 2; i++) {
                output.setSample(i, output.getSample(i) + inputClip.getSample(i));
            }
        }
        return output;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        inputs_.add(input);
        inputNums_++;
    }
}
