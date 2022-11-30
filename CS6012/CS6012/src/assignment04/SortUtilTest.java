package assignment04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {
    int initialPower = 8, max_power = 14, power;
    ArrayList<Integer> best = SortUtil.generateBestCase((int) Math.pow(2, max_power + 4));
    ArrayList<Integer> average = SortUtil.generateAverageCase((int) Math.pow(2, max_power + 4));
    ArrayList<Integer> worst = SortUtil.generateWorstCase((int) Math.pow(2, max_power + 4));
    ArrayList<Integer> emptyTest;
    IntComparator comparator = new IntComparator();

    public class IntComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 - o2);
        }
    }


    @BeforeEach
    void setup() {

        power = initialPower;

        emptyTest = new ArrayList<>();
    }

    @Test
    void mergesort() {


        SortUtil.mergesort(emptyTest, comparator);

        for (int j = 0; j < 1000; j++) {
            int testSize = 100;
            ArrayList<Integer> testArray = SortUtil.generateAverageCase(testSize);
            SortUtil.mergesort(testArray, comparator);
            for (int i = 0; i < testSize; i++) {
                assertEquals(i + 1, testArray.get(i));
            }
        }

//        int loops = 3000;
//        for (int k = 0; k <= 47; k+= 7) {
//            SortUtil.setThreshold(k);
//            for(power = initialPower; power <= max_power; power++){
//                ArrayList<Integer> tempArray = new ArrayList<>();
//                for(int i = 0; i < Math.pow(2,power); i++){
//                    tempArray.add(average.get(i));
//                }
//
//                long totalTime = 0;
//                for(int i = 0; i < loops; i++){
//                    long startTime = System.nanoTime();
//                    SortUtil.mergesort(tempArray, comparator);
//                    long endTime = System.nanoTime();
//                    totalTime+= endTime - startTime;
//                }
//                long averageTime = totalTime / loops;
//                System.out.println(averageTime);
//            }
//            System.out.println();
//        }
    }

    @Test
    void quicksort() {

        //SortUtil.quicksort(emptyTest, comparator, 1);
        for (int j = 0; j < 1000; j++) {
            int testSize = 1000;
            ArrayList<Integer> testArray = SortUtil.generateAverageCase(testSize);
            SortUtil.quicksort(testArray, comparator);
            for (int i = 0; i < testSize; i++) {
                assertEquals(i + 1, testArray.get(i));
            }
        }

//        int loops = 3000;
//        for (int strategy = 1; strategy <= 3; strategy++) {
//            for(power = initialPower; power <= max_power + 4; power++){
//                ArrayList<Integer> tempArray = new ArrayList<>();
//                for(int i = 0; i < Math.pow(2,power); i++){
//                    tempArray.add(average.get(i));
//                }
//
//                long totalTime = 0;
//                for(int i = 0; i < loops; i++){
//                    long startTime = System.nanoTime();
//                    SortUtil.quicksort(tempArray, comparator, strategy);
//                    long endTime = System.nanoTime();
//                    totalTime+= endTime - startTime;
//                }
//                long averageTime = totalTime / loops;
//                System.out.println(averageTime);
//            }
//            System.out.println();
//
//        }
    }

    @Test
    void mergeVSquick() {
//        SortUtil.setThreshold(35);
//        int loops = 3000;
//
//        for (power = initialPower; power <= max_power + 2; power++) {
//            ArrayList<Integer> tempArray = new ArrayList<>();
//            for (int i = 0; i < Math.pow(2, power); i++) {
//                tempArray.add(average.get(i));
//            }
//
//            long totalTime = 0;
//            for (int i = 0; i < loops; i++) {
//                long startTime = System.nanoTime();
//                SortUtil.mergesort(tempArray, comparator);
//                long endTime = System.nanoTime();
//                totalTime += endTime - startTime;
//            }
//            long averageTime = totalTime / loops;
//            System.out.println(averageTime);
//        }
//
//        System.out.println();
//
//        for(power = initialPower; power <= max_power + 2; power++){
//                ArrayList<Integer> tempArray = new ArrayList<>();
//                for(int i = 0; i < Math.pow(2,power); i++){
//                    tempArray.add(average.get(i));
//                }
//
//                long totalTime = 0;
//                for(int i = 0; i < loops; i++){
//                    long startTime = System.nanoTime();
//                    SortUtil.quicksort(tempArray, comparator);
//                    long endTime = System.nanoTime();
//                    totalTime+= endTime - startTime;
//                }
//                long averageTime = totalTime / loops;
//                System.out.println(averageTime);
//            }
    }
}




