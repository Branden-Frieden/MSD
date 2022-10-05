package com.example.synthesizer;


import javafx.application.Application;
import javafx.stage.Stage;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) throws LineUnavailableException {

        // testing
        /*
        AudioClip clip = new AudioClip();

        //set the first 2 to max and min val
        clip.setSample(0, 32767);
        clip.setSample(1, -32768);
        int sample1 = clip.getSample(0);
        int sample2 = clip.getSample(1);

        // check max and min val for correctness
        if(sample1 != 32767){
            System.out.printf("error in AudioClip sample1");
        }
        if(sample2 != -32768){
            System.out.printf("error in AudioClip sample2");
        }

        // randomize the rest of the data points and check for accuracy
        for(int i = 2; i < (88200); i++){
            int randNum = (int)(Math.random() * (32767 + 32768) - 32768);
            clip.setSample( i, randNum);
            int getNum = clip.getSample(i);
            if(randNum != getNum){
                System.out.printf("error at index %d expected: %d actual: %d %n", i, randNum, getNum);
            }
        }


        // Get properties from the system about samples rates, etc.
    // AudioSystem is a class from the Java standard library.
        Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

    // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

        AudioComponent sin1 = new SineWave(440);
        AudioComponent sin2 = new SineWave(690);

        AudioComponent mixer = new Mixer();
        mixer.connectInput(sin1);
        mixer.connectInput(sin2);

        AudioComponent volume = new filter(.5);
        volume.connectInput(mixer);

        // linear ramp test

        AudioComponent linramp = new LinearRamp(50, 2000);
        AudioComponent vfSineWave = new VFSineWave();
        vfSineWave.connectInput(linramp);

        AudioClip clip = vfSineWave.getClip();
        c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

        System.out.println( "About to play..." );
        c.start(); // Plays it.
        c.loop( 0 ); // Plays it 2 more times if desired, so 6 seconds total

    // Makes sure the program doesn't quit before the sound plays.
        while( c.getFramePosition() < c.getFrameLength() || c.isActive() || c.isRunning() ){
            // Do nothing while we wait for the note to play.
        }

        System.out.println( "Done." );
        c.close();
        */

    }



}
