public class Main {

    public static void main(String[] args){
        AudioClip clip = new AudioClip();
        clip.setSample(0, 32767);
        clip.setSample(1, -32768);
        for(int i = 2; i < (88200); i++){
            int randNum = (int)(Math.random() * (32767 + 32768) - 32768);
            clip.setSample( i, randNum);
            int getNum = clip.getSample(i);
            if(randNum != getNum){
                System.out.printf("error at index %d expected: %d actual: %d", i, randNum, getNum);
            }
        }
    }

}
