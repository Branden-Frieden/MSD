package com.example.synthesizer;

public class Main {

    public static void main(String[] args){
        AudioClip clip = new AudioClip();

        //set the first 2 to max and min val
        clip.setSample(0, 32767);
        clip.setSample(1, -32768);
        int sample1 = clip.getSample(0);
        int sample2 = clip.getSample(1);

        // check max and min val for correctness
        if(sample1 != 32767){
            System.out.printf("error in Audioclip sample1");
        }
        if(sample2 != -32768){
            System.out.printf("error in Audioclip sample2");
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
    }

}
