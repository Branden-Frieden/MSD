package assignment03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {
    BinarySearchSet<Integer> intNaturalSearchSet1, intReversedSearchSet1, intEmptySearchSet;
    BinarySearchSet<Double> doubleNaturalSeachSet;
    Comparator<Integer> reverseInt;
    public class reverseIntCompare implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    @BeforeEach
    void setUp() {
        intEmptySearchSet = new BinarySearchSet<>();

        intNaturalSearchSet1 = new BinarySearchSet<>();
        intNaturalSearchSet1.add(5);
        intNaturalSearchSet1.add(12);
        intNaturalSearchSet1.add(3);
        intNaturalSearchSet1.add(-8);
        intNaturalSearchSet1.add(4);
        intNaturalSearchSet1.add(2);
        intNaturalSearchSet1.add(1);
        intNaturalSearchSet1.add(0);
        intNaturalSearchSet1.add(14);
        intNaturalSearchSet1.add(19);

        reverseInt = new reverseIntCompare();
        intReversedSearchSet1 = new BinarySearchSet<>(reverseInt);
        intReversedSearchSet1.add( 3 );
        intReversedSearchSet1.add( 5 );
        intReversedSearchSet1.add( 9 );
        intReversedSearchSet1.add( 6 );
        intReversedSearchSet1.add( -12 );

        doubleNaturalSeachSet = new BinarySearchSet<Double>();
        doubleNaturalSeachSet.add(4.3);
        doubleNaturalSeachSet.add(4.2);
        doubleNaturalSeachSet.add(-8.6);
        doubleNaturalSeachSet.add(452.1);
        doubleNaturalSeachSet.add(0.0);
        doubleNaturalSeachSet.add(1.1);


    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void add() {

        assertTrue(intEmptySearchSet.add(35));
        assertFalse(intNaturalSearchSet1.add(3));
        assertTrue(intNaturalSearchSet1.add(1000));
        assertTrue(intNaturalSearchSet1.add(-10));
        assertEquals(-10, intNaturalSearchSet1.first());
        assertEquals(1000, intNaturalSearchSet1.last());
        assertFalse(intNaturalSearchSet1.add(25.443));
        assertFalse(intReversedSearchSet1.add(3));
        assertTrue(intReversedSearchSet1.add(1000));
        assertTrue(intReversedSearchSet1.add(-500));
        assertEquals(1000, intReversedSearchSet1.first());
        assertEquals(-500, intReversedSearchSet1.last());

        assertTrue(doubleNaturalSeachSet.add(-81.2));
        assertTrue(doubleNaturalSeachSet.add(453.8));
        assertFalse(doubleNaturalSeachSet.add(-8.6));
        assertFalse(doubleNaturalSeachSet.add(452.1));
    }

    @Test
    void comparator() {
        assertEquals(reverseInt, intReversedSearchSet1.comparator());
        assertNull(intNaturalSearchSet1.comparator());
        assertNull(intEmptySearchSet.comparator());
        assertNull(doubleNaturalSeachSet.comparator());
    }

    @Test
    void first() {
        try{ intEmptySearchSet.first();}
        catch(NoSuchElementException e){
            System.out.println("First exception thrown correctly");
        }
        assertEquals(-8, intNaturalSearchSet1.first());
        assertEquals(9, intReversedSearchSet1.first());
        assertEquals(-8.6, doubleNaturalSeachSet.first());
    }

    @Test
    void last() {
        try{intEmptySearchSet.last();}
        catch(NoSuchElementException e){
            System.out.println("Last exception thrown correctly");
        }
        assertEquals(19, intNaturalSearchSet1.last());
        assertEquals(-12, intReversedSearchSet1.last());
        assertEquals(452.1, doubleNaturalSeachSet.last());
    }


    @Test
    void addAll() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add( 0 );
        ints.add( 3 );
        ints.add( 5 );

        assertTrue(intEmptySearchSet.addAll(ints));
        assertTrue(intReversedSearchSet1.addAll(ints));
        assertFalse(intNaturalSearchSet1.addAll(ints));

        assertEquals(3, intEmptySearchSet.size());
        assertEquals(6, intReversedSearchSet1.size());
        assertEquals(10, intNaturalSearchSet1.size());
    }

    @Test
    void clear() {
        intEmptySearchSet.clear();
        intReversedSearchSet1.clear();
        intNaturalSearchSet1.clear();
        doubleNaturalSeachSet.clear();
        assertTrue(intEmptySearchSet.isEmpty());
        assertTrue(intReversedSearchSet1.isEmpty());
        assertTrue(intNaturalSearchSet1.isEmpty());
        assertTrue(doubleNaturalSeachSet.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(intEmptySearchSet.contains( 1 ));
        assertFalse(intNaturalSearchSet1.contains( -4 ));
        assertFalse(intReversedSearchSet1.contains( 2 ));

        assertTrue(intNaturalSearchSet1.contains( 0 ));
        assertTrue(intReversedSearchSet1.contains( 9 ));
        assertTrue(doubleNaturalSeachSet.contains( 1.1 ));
        assertTrue(doubleNaturalSeachSet.contains( 0.0 ));
        assertTrue(doubleNaturalSeachSet.contains( 452.1 ));
        assertFalse(doubleNaturalSeachSet.contains( 1.1001 ));
    }

//    @Test
//    void containsTiming() {
//
//        int power = 2;
//        int maxPower = 25;
//
//        for(int i = 0; i < Math.pow(2,power); i++){
//            intEmptySearchSet.add(i);
//        }
//
//        int loops = 10000;
//
//        for (; power <= maxPower; power++) {
//            long totalTime = 0;
//            for (int i = 0; i < loops; i++) {
//                long startTime = System.nanoTime();
//                intEmptySearchSet.contains((int)(Math.random() * Math.pow(2,5)));
//                long endTime = System.nanoTime();
//                totalTime += endTime - startTime;
//            }
//            long averageTime = totalTime / loops;
//            System.out.println(averageTime);
//
//            for (int i = (int) Math.pow(2, power); i < Math.pow(2, power + 1); i++) {
//                intEmptySearchSet.add(i);
//            }
//        }
//    }

//    @Test
//    void addTiming(){
//
//        int power = 2;
//        int maxPower = 25;
//
//        for(int i = 0; i < Math.pow(2,power); i++){
//            intEmptySearchSet.add(i);
//        }
//
//        int loops = 2000;
//
//        for (; power <= maxPower; power++) {
//            long totalTime = 0;
//            for (int i = 0; i < loops; i++) {
//                int randVal = (int)(Math.random() * Math.pow(2,power));
//                intEmptySearchSet.remove(randVal);
//                long startTime = System.nanoTime();
//                intEmptySearchSet.add(randVal);
//                long endTime = System.nanoTime();
//                totalTime += endTime - startTime;
//            }
//            long averageTime = totalTime / loops;
//            System.out.println(averageTime);
//
//            for (int i = (int) Math.pow(2, power); i < Math.pow(2, power + 1); i++) {
//                intEmptySearchSet.add(i);
//            }
//        }
//
//    }

    @Test
    void containsAll() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add( 0 );
        ints.add( 3 );
        ints.add( 5 );

        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(452.1);
        doubles.add(1.1);
        doubles.add(0.1);

        assertFalse(intEmptySearchSet.containsAll(ints));
        assertFalse(intReversedSearchSet1.containsAll(ints) );
        assertTrue(intNaturalSearchSet1.containsAll(ints));
        assertFalse(doubleNaturalSeachSet.containsAll(doubles));

    }

    @Test
    void isEmpty() {
        assertTrue(intEmptySearchSet.isEmpty());
        assertFalse(intReversedSearchSet1.isEmpty());
        assertFalse(intNaturalSearchSet1.isEmpty());
        assertFalse(doubleNaturalSeachSet.isEmpty());
    }

    @Test
    void iterator() {
        // no idea how to test this
    }

    @Test
    void remove() {
        assertFalse(intEmptySearchSet.remove(1));
        assertFalse(intNaturalSearchSet1.remove(12345));
        assertFalse(intReversedSearchSet1.remove(12345));
        assertFalse(doubleNaturalSeachSet.remove(12345));
        assertTrue(intNaturalSearchSet1.remove(4));
        assertTrue(intReversedSearchSet1.remove(5));
        assertTrue(doubleNaturalSeachSet.remove(1.1));

        assertFalse(intNaturalSearchSet1.contains(4));
        assertFalse(intReversedSearchSet1.contains(5));
        assertFalse(doubleNaturalSeachSet.contains(1.1));
    }

    @Test
    void removeAll() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add( 0 );
        ints.add( 3 );
        ints.add( 5 );

        assertTrue(intNaturalSearchSet1.removeAll( ints ));
        assertTrue(intReversedSearchSet1.removeAll( ints ));
        assertFalse(intEmptySearchSet.removeAll( ints ));

        assertFalse(intNaturalSearchSet1.contains(5));
        assertFalse(intReversedSearchSet1.contains(5));
        assertEquals(7, intNaturalSearchSet1.size());
        assertEquals(3, intReversedSearchSet1.size());
    }

    @Test
    void size() {
        assertEquals(0, intEmptySearchSet.size());
        assertEquals(10, intNaturalSearchSet1.size());
        assertEquals(5, intReversedSearchSet1.size());
        assertEquals(6, doubleNaturalSeachSet.size());
    }

    @Test
    void toArray() {
//        Integer[] naturalEqualArray = new Integer[]{-8, 0, 1, 2, 3, 4, 5, 12, 14, 19, null, null, null,
//                                                    null, null, null, null, null, null, null};
//        Integer[] reversedEqualArray = new Integer[]{9, 6, 5, 3, -12, null, null, null, null, null};
//
//
//        for( int i = 0; i < 20; i++) {
//            assertEquals(naturalEqualArray[i], intNaturalSearchSet1.toArray()[i]);
//        }
//        for( int i = 0; i < 10; i++) {
//            assertEquals(reversedEqualArray[i], intReversedSearchSet1.toArray()[i]);
//            assertNull(intEmptySearchSet.toArray()[i]);
//        }
    }
}