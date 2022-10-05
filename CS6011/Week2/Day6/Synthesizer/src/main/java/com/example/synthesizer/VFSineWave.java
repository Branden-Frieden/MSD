package com.example.synthesizer;

public class VFSineWave implements AudioComponent{

    AudioComponent input_;

    @Override
    public AudioClip getClip() {
        AudioClip output = new AudioClip();
        AudioClip inputClip = input_.getClip();
        int numSamples = output.getTotalSamples() / 2;
        float phase = 0;
        for(int i = 0; i < numSamples; i++){
            phase += 2 * Math.PI * inputClip.getSample(i) / 44100;
            output.setSample(i, (int) (Short.MAX_VALUE * Math.sin(phase)));
        }

        return output;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        input_ = input;
    }
}
