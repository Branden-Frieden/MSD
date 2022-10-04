package com.example.synthesizer;

public class SineWave implements AudioComponent{

    int frequency_;
    int sampleRate_;

    double sampleTime_;

    SineWave(int frequency){
        frequency_ = frequency;
        sampleRate_ = 44100;
        sampleTime_ = 2.0;
    }
    @Override
    public AudioClip getClip() {

        AudioClip output = new AudioClip();
        for(int i = 0; i < sampleRate_ * sampleTime_; i++){
            output.setSample(i, (int)(Short.MAX_VALUE * Math.sin(2*Math.PI*frequency_ * i / sampleRate_)));
        }

        return output;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        assert(false);
    }
}
