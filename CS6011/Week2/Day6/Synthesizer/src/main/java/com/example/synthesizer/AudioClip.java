package com.example.synthesizer;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AudioClip {
    static double duration_ = 2.0;
    static int sampleRate_ = 44100;
    byte[] data_ = new byte[(int)(duration_ * sampleRate_) * 2 + 1];

    public int getSample( int index ){
        byte val1 = (byte)Array.get(data_, index * 2 );
        short val2 = (byte) Array.get(data_, index * 2 + 1 );

        short mask = 0xFF;
        short output = (short) ((val1 & mask) | (val2 & mask) << 8);
        return output;
    }
    public void setSample( int index, int value ){
        byte byteVal = (byte) value;
        Array.set(data_, index * 2, byteVal);
        value >>>= 8;
        byteVal = (byte) value;
        Array.set(data_ ,index * 2 + 1, byteVal );
    }
    public byte[] getData(){
        return Arrays.copyOf(data_, (int) (sampleRate_ * duration_));
    }

}
