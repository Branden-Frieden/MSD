package com.example.synthesizer;

public class filter implements AudioComponent{

    AudioComponent input_;
    double volumeScale_;

    filter(double volumeScale){
    volumeScale_ = volumeScale;
}

    @Override
    public AudioClip getClip() {
        AudioClip output = new AudioClip();
        AudioClip inputClip = input_.getClip();

        for (int i = 0; i < inputClip.getTotalSamples() / 2; i++) {
            int out = (int) (volumeScale_ * inputClip.getSample(i));


            if (out > Short.MAX_VALUE) {
                output.setSample(i, Short.MAX_VALUE);
            } else if (out < Short.MIN_VALUE) {
                output.setSample(i, Short.MIN_VALUE);
            } else {
                output.setSample(i, out);
            }
        }
        return output;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        input_ = input;
    }
}
