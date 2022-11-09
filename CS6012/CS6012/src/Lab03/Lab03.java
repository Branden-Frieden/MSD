package Lab03;

import java.util.ArrayList;
import java.util.Collections;

public class Lab03 {

    public static void main(String[] args) {

        ArrayList<Float> list = new ArrayList<>();

        for(int i = 0; i < Math.pow(2,10); i++){
            list.add((float) (Math.random() * 100000));
        }

        int loops = 2000;


        for (int power = 10; power <= 21; power++) {
            long totalTime = 0;
            for(int i = 0; i < loops; i++){
                long startTime = System.nanoTime();
                Collections.shuffle(list);
                long endTime = System.nanoTime();
                totalTime += endTime - startTime;
            }
            long averageTime = totalTime / loops;
            System.out.println(averageTime);

            for(int i = (int) Math.pow(2,power); i< Math.pow(2,power+1); i++){
                list.add( (float) (Math.random() * 100000) );
            }
        }
    }
}
