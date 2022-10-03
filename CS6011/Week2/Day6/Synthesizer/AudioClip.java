import java.lang.reflect.Array;
import java.util.Arrays;

public class AudioClip {
    static double duration_ = 2.0;
    static int sampleRate_ = 44100;
    byte[] data_;

    public int getSample( int index ){
        int output = (int) Array.get(data_, index * 2 );
        int val = (int) Array.get(data_, index * 2 + 1 );
        val <<= 8;
        output += val;
        return output;
    }
    public void setSample( int index, int value ){
        Array.set(data_, index * 2, value);
        value >>= 8;
        Array.set(data_ ,index * 2 + 1, (value) );
    }
    public byte[] getData(){
        return Arrays.copyOf(data_, (int) (sampleRate_ * duration_));
    }

}
